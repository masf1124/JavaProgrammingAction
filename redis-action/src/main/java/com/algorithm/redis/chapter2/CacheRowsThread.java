package com.algorithm.redis.chapter2;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CacheRowsThread extends Thread {

    private Jedis conn;
    private boolean quit;

    public CacheRowsThread(String host,int port){
        this.conn = new Jedis(host,port);
        this.conn.select(15);
    }

    public void quit(){
        quit = true;
    }

    public void run(){

        Gson gson = new Gson();
        while(!quit){
            Set<Tuple> range = conn.zrangeWithScores("schedule:",0,0);//????为什么是0
            Tuple next = range.size()>0?range.iterator().next():null;

            long currentTime = System.currentTimeMillis()/1000;
            if(next==null || next.getScore()>currentTime){
                try{
                    TimeUnit.SECONDS.sleep(50);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }

                continue;
            }

            String rowId = next.getElement();
            double delay = conn.zscore("delay:",rowId);
            if(delay<=0){
                conn.zrem("delay:",rowId);
                conn.zrem("schedule:",rowId);
                conn.del("inv:"+rowId);
                continue;
            }

            Chapter2.Inventory row = Chapter2.Inventory.getInventory(rowId);
            conn.zadd("schedule:",currentTime+delay,rowId);
            conn.set("inv:"+rowId,gson.toJson(row));
        }
    }
}
