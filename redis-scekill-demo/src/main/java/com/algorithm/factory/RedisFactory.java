package com.algorithm.factory;

import com.algorithm.util.RedisClient;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: com.algorithm.factory
 * @author: mashifei
 * @create: 2019-05-28-21
 */
public class RedisFactory {

    public static JedisPoolConfig getPoolConfig(){
        Properties props = new Properties();
        //加载Redis配置文件
        InputStream in = RedisFactory.class.getClassLoader().getResourceAsStream("redis.properties");

        try{
            try {
                props.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JedisPoolConfig config = new JedisPoolConfig();

            config.setMaxIdle(Integer.valueOf(props.getProperty("maxIdle","100")));
            config.setMinIdle(Integer.valueOf(props.getProperty("minIdle","1")));
            config.setMaxTotal(Integer.valueOf(props.getProperty("maxTotal","1000")));
            return config;
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static RedisClient getDefaultClient(){
        JedisPool pool = new JedisPool("127.0.0.1");
        RedisClient client = new RedisClient(pool);
        return client;
    }

    public static RedisClient getClient(String host,int port){
        JedisPool pool = new JedisPool(host,port);
        RedisClient client = new RedisClient(pool);
        return client;
    }
}
