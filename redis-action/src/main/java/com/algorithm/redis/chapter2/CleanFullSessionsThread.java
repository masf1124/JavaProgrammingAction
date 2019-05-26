package com.algorithm.redis.chapter2;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CleanFullSessionsThread extends Thread {

    private Jedis conn;
    private int limit;
    private boolean quit = false;

    public CleanFullSessionsThread(String host,int port, int limit){
        this.conn = new Jedis(host,port);
        this.conn.select(15);
        this.limit = limit;
    }

    public void quit(){
        this.quit=true;
        while(!quit){
            long size = conn.zcard("recent:");
            if(size<=limit){
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
                continue;
            }

            long endIndex = Math.min(size-limit,100);
            Set<String> sessionSet = conn.zrange("recent:",0,endIndex-1);
            String[] sessions = sessionSet.toArray(new String[sessionSet.size()]);


            ArrayList<String> sessionKeys = new ArrayList<String>();
            for(String session:sessions){
                sessionKeys.add("viewed:"+session);
                sessionKeys.add("cart:"+session);
            }

            conn.del(sessionKeys.toArray(new String[sessionKeys.size()]));
            conn.hdel("login:"+sessions);
            conn.zrem("recent:",sessions);
        }
    }
}
