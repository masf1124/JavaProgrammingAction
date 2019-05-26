package com.algorithm.redis.chapter2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Chapter2 {

    private String host;
    private int port;

    public Chapter2(String host,int port){
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        new Chapter2("192.168.73.136",6379).run();
    }

    public void run(){

        Jedis conn = new Jedis(host,port);
        conn.select(15);

        testLoginCookies(conn);
        testShoppingCartCookie(conn);
        testCacheRows(conn);
        testCacheRequest(conn);
    }

    public void testLoginCookies(Jedis conn){

        System.out.println("\n--------testLoginCookie----------");
        String token = UUID.randomUUID().toString();

        updateToken(conn,token,"username","itemX");
        System.out.println("We just logged-in/updated token: "+token);
        System.out.println("For user :'username'");
        System.out.println();

        System.out.println("What username do we get when we look-up that token?");
        String r = checkToken(conn,token);
        System.out.println(r);
        System.out.println();
        assert r!=null;

        System.out.println("Let's drop the maximun number of cookie to 0 to clean them out ");
        System.out.println("We will start a thread to do the cleaning,while we stop it later");

        CleanSessionsThread thread = new CleanSessionsThread(host,port,0);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.quit();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(thread.isAlive()){
            throw new RuntimeException("The clean sessions thread is still alive?");
        }
        long s = conn.hlen("login:");
        System.out.println("The current number of sessions still avaiable is "+s);
        assert s==0;
    }

    public void testShoppingCartCookie(Jedis conn){
        System.out.println("\n---------------testShoppingCartCookie-------------------");
        String token = UUID.randomUUID().toString();

        System.out.println("We will refresh our session ...");
        updateToken(conn,token,"username","itemX");
        System.out.println();

        System.out.println("And add an item to the shopping cart");
        addToCart(conn,token,"itemY",3);
        Map<String,String> r = conn.hgetAll("cart:"+token);
        System.out.println("Our shopping cart currently has:");

        for(Map.Entry<String,String> entry:r.entrySet()){
            System.out.println(" "+entry.getKey()+" : "+entry.getValue());
        }
        System.out.println();

        assert r.size()>=1;
        System.out.println("Let's clean out sessions and carts");
        CleanFullSessionsThread thread = new CleanFullSessionsThread(host,port,0);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.quit();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(thread.isAlive()){
            throw new RuntimeException("The clean session is still alive?");
        }

        r = conn.hgetAll("cart:"+token);
        System.out.println("Our shopping cart now contains:");
        for(Map.Entry<String,String> entry:r.entrySet()){
            System.out.println(" "+entry.getKey()+" : "+entry.getValue());
        }
        assert r.size()==0;
    }

    public void testCacheRows(Jedis conn){
        System.out.println("\n---------testCacheRows-----------");
        System.out.println("First,let's schedule caching of itemX every 5 seconds.");

        scheduleRowCache(conn,"itemX",5);
        System.out.println("Our schedule looks like:");
        Set<Tuple> s = conn.zrangeWithScores("schedule:",0,-1);
        for(Tuple tuple:s){
            System.out.println(" "+tuple.getElement()+" ,"+tuple.getScore());
        }
        assert s.size()!=0;


        System.out.println("We will start a caching thread that will cache the data ....");

        CacheRowsThread thread = new CacheRowsThread(host,port);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Our cached data looks like:");
        String r = conn.get("inv:itemX");
        System.out.println(r);
        assert r!=null;
        System.out.println();


        System.out.println("We will check again in 5 seconds...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Notice that the data has changed ...");
        String r2 = conn.get("inv:itemX");
        System.out.println(r2);
        System.out.println();
        assert r2!=null;
        assert !r2.equals(r);


        System.out.println("Let'e force un-caching");
        scheduleRowCache(conn,"itemX",-1);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r = conn.get("inv:itemX");
        System.out.println("The cache was cleared? "+r==null);
        assert r==null;

        thread.quit();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(thread.isAlive()){
            throw new RuntimeException("The database caching thread is still alive?");
        }


    }

    public void testCacheRequest(Jedis conn){
        System.out.println("\n---------------testCacheRequest----------------");
        String token = UUID.randomUUID().toString();

        Callback callback = new Callback() {
            @Override
            public String call(String request) {
                return "Content for "+request;
            }
        };

        updateToken(conn,token,"username","itemX");
        String url = "http://test.com/?item=itemX";
        System.out.println("We are going to cache a simple request against "+url);
        String result = cacheRequest(conn,url,callback);
        System.out.println("We got initial content :"+result);
        System.out.println();

        assert result!=null;

        System.out.println("To test that We cached request,we'll pass a bad callback");
        String result2 = cacheRequest(conn,url,null);
        System.out.println("We ended up getting the same response!"+result2);

        assert result.equals(result2);
        assert !canCache(conn,"http://test.com/");
        assert !canCache(conn,"http://test.com/?item=itemX&_=123456");


    }


    public String checkToken(Jedis conn,String token){
        return conn.hget("login:",token);
    }

    public void updateToken(Jedis conn,String token,String user,String item){
        long timestamp = System.currentTimeMillis()/1000;
        conn.hset("login:",token,user);
        conn.zadd("recent:",timestamp,token);

        if(item!=null){
            conn.zadd("viewed:"+token,timestamp,item);
            conn.zremrangeByRank("viewed:"+token,0,-26);
            conn.zincrby("viewed:",-1,item);
        }
    }

    public void addToCart(Jedis conn,String session,String item,int count){
        if(count<0){
            conn.hdel("cart:"+session,item);
        }else{
            conn.hset("cart:"+session,item,String.valueOf(count));
        }
    }

    public void scheduleRowCache(Jedis conn,String rowId,int delay){
        conn.zadd("delay:",delay,rowId);
        conn.zadd("schedule:",System.currentTimeMillis()/1000,rowId);
    }

    public String cacheRequest(Jedis conn,String request,Callback callback){
        if(!canCache(conn,request)){
            return callback!=null?callback.call(request):null;
        }

        String pageKey = "cache:"+hashRequest(request);
        String content = conn.get(pageKey);

        if(content!=null && callback!=null){
            content = callback.call(request);
            conn.setex(pageKey,300,content);
        }

        return content;
    }


    public boolean canCache(Jedis conn,String request){
        try{
            URL url = new URL(request);
            HashMap<String,String> params = new HashMap<String,String>();

            if(url.getQuery()!=null){
                for(String param:url.getQuery().split("&")){
                    String[] pair = param.split("=",2);
                    params.put(pair[0],pair.length==2?pair[1]:null);
                }
            }

            String itemId = extractItemId(params);
            if(itemId==null || isDynamic(params)){
                return false;
            }

            Long rank = conn.zrank("viewed:",itemId);
            return rank!=null && rank<10000;
        }catch (MalformedURLException mue){
            mue.printStackTrace();
            return false;
        }
    }

    public boolean isDynamic(Map<String,String> params){
        return params.containsKey("_");
    }

    public String extractItemId(Map<String,String> params){
        return params.get("item");
    }

    public String hashRequest(String request){
        return String.valueOf(request.hashCode());
    }

    static class Inventory{
        private String id;
        private String data;
        private long time;

        private Inventory(String id){
            this.id = id;
            this.data = "data to cache ...";
            this.time = System.currentTimeMillis()/1000;
        }

        public static Inventory getInventory(String id){
            return new Inventory(id);
        }
    }
}
