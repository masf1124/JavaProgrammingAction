package com.algorithm.redis.chapter1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

public class Chapter1 {

    private static final int ONE_WEEK_IN_SECONDS = 7*86400;
    private static final int VOTE_SCORE = 432;
    private static final int ARTICLES_PER_PAGE = 25;

    private String host;
    private int port;

    public static void main(String[] args) {
        new Chapter1("192.168.73.136",6379).run();
    }

    public Chapter1(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run(){
        Jedis conn = new Jedis(host,port);
        //Redis默认情况下，有16个数据库，选择第15号数据库
        conn.select(15);
        postArticle(conn,"masf","hahhahah","www.baidu.com");
    }


    /**
     * article:
     * title
     * user
     * title
     * link
     *
     * @param conn
     * @param user
     * @param title
     * @param link
     * @return
     */
    //发布文章
    public String postArticle(Jedis conn,String user,String title,String link){

        //创建文章ID
        String articleId = String.valueOf(conn.incr("article:"));

        String voted = "voted:"+articleId;
        conn.sadd(voted,user);
        //设置过期时间
        conn.expire(voted,ONE_WEEK_IN_SECONDS);

        long currentTime = System.currentTimeMillis()/1000;
        String article = "article:"+articleId;

        //一篇文章包含的信息
        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title",title);
        articleData.put("link",link);
        articleData.put("user",user);
        articleData.put("currentTime",String.valueOf(currentTime));
        articleData.put("votes",String.valueOf(1));
        //将多个键值对添加到article
        conn.hmset(article,articleData);
        //article的初始评分
        conn.zadd("score:",currentTime+VOTE_SCORE,article);
        //article的发布时间
        conn.zadd("time:",currentTime,article);
        return articleId;
    }


    //为文章投票
    public void articleVote(Jedis conn,String user,String article){
        //只能为一周内的文章投票
        long cutoff = (System.currentTimeMillis()/1000)-ONE_WEEK_IN_SECONDS;
        if(conn.zscore("time:",article)<cutoff){
            return;
        }

        String articleId = article.substring(article.indexOf(":")+1);
        //sadd命令将用户添加到记录用户名单的集合里面，如果添加成功返回1
        if(conn.sadd("voted:"+articleId,user)==1){
            conn.zincrby("score:",VOTE_SCORE,article);
            conn.hincrBy(article,"votes",1L);
        }
    }

    public List<Map<String,String>> getArticles(Jedis conn,int page){
        return getArticles(conn,page,"score:");
    }

    private List<Map<String,String>> getArticles(Jedis conn,int page,String order){
        int start = (page-1)*ARTICLES_PER_PAGE;
        int end = start+ARTICLES_PER_PAGE - 1;

        //按键值的分数从大到小获取对应的键值
        Set<String> ids = conn.zrevrange(order,start,end);
        List<Map<String,String>> articles = new ArrayList<Map<String,String>>();

        for(String id:ids){
            //hgetAll命令获取文章的所有信息
            Map<String,String> articleData = conn.hgetAll(id);
            articleData.put("id",id);
            articles.add(articleData);
        }
        return articles;
    }


    public void addGroups(Jedis conn,String articleId,String[] toAdd){
        String article = "article:"+articleId;
        for(String group:toAdd){
            conn.sadd("group:"+group,article);
        }
    }

    public List<Map<String,String>> getGroupArticles(Jedis conn,String group,int page){
        return getGroupArticles(conn,group,page,"score:");
    }

    private List<Map<String,String>> getGroupArticles(Jedis conn,String group,int page,String order){
        String key = order+group;
        if(!conn.exists(key)){
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key,params,"group"+group,order);
            conn.expire(key,60);
        }

        return getArticles(conn,page,key);
    }


    private  void printArticles(List<Map<String,String>> articles){
        for(Map<String,String> article:articles){
            System.out.println("  id: "+article.get("id"));
            for(Map.Entry<String,String> entry:article.entrySet()){
                if(entry.getKey().equals("id")){
                    continue;
                }
                System.out.println("   "+entry.getKey()+" : "+entry.getValue());
            }
        }
    }
}
