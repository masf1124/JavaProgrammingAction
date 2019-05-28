package com.algorithm.util;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.*;

/**
 * @program: com.algorithm.util
 * @author: mashifei
 * @create: 2019-05-28-21
 */
public class RedisClient {

    private JedisPool jedisPool;

    public RedisClient(JedisPool jedisPool){
        this.jedisPool = jedisPool;
    }

    public RedisClient(){

    }

    public void setJedisPool(JedisPool pool){
        this.jedisPool = pool;
    }

    /**
     * 根据key来获取对应的value
     */

    public Object getByKey(String key){
        Jedis client = jedisPool.getResource();
        return client.get(key);
    }

    /**
     * 判断String类型key是否存在
     */

    public boolean isKeyExist(String key){
        Jedis client = jedisPool.getResource();
        return client.exists(key);
    }

    /**
     * String类型的键值写入Redis
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        Jedis client = jedisPool.getResource();
        String isSuccess = "";
        isSuccess = client.set(key,value);
        if("OK".equals(isSuccess)){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public long setnx(String key,String value){
        Jedis client = jedisPool.getResource();
        Long result  = client.setnx(key,value);
        System.out.println("setnx key="+key+"  value="+value+"  result="+result);
        return result;
    }

    /**
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean setKeyWithExpireTime(String key, String value, int time) {
        if (time == 0) {

        }
        Jedis client = jedisPool.getResource();
        String issuccess = "";
        issuccess = client.setex(key, time, value);
        if ("OK".equals(issuccess)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lpush(String key, List<String> value) {
        Jedis client = jedisPool.getResource();
        Transaction tx = client.multi();
        for (String one : value) {
            tx.lpush(key, one);
        }
        tx.exec();
        return true;
    }

    public List<String> lrange(String key) {
        Jedis client = jedisPool.getResource();
        List<String> returnList = null;
        returnList = client.lrange(key, 0, -1);
        return returnList;
    }

    public List<String> lrange(String key, int start, int length){
        Jedis client = jedisPool.getResource();
        return client.lrange(key, start, length);
    }

    /**
     *
     * @param key
     * @param o
     * @return
     */
    public boolean setAnObject(String key, Object o) {
        Jedis client = jedisPool.getResource();
        String afterSerialize = JSON.toJSONString(o);
        o = client.set(key, afterSerialize);
        return true;
    }

    @SuppressWarnings("unchecked")
    public <T> T getSetT(String key, T newValue) {
        Jedis client = jedisPool.getResource();
        String afterSerialize = JsonUtil.beanToJson(newValue);
        String value = client.getSet(key, afterSerialize);
        return (T) JsonUtil.jsonToBean(value, newValue.getClass());
    }

    public <T> T getAnObject(String key, Class<T> cls) {
        Jedis client = jedisPool.getResource();
        String s = client.get(key);
        if (s == null || s.length() == 0) {
            return null;
        }
        return JsonUtil.jsonToBean(s, cls);

    }

    public List<String> getKeys(String pattern) {
        Jedis client = jedisPool.getResource();
        List<String> result = new ArrayList<String>();
        Set<String> set = client.keys(pattern);
        result.addAll(set);
        return result;
    }

    public boolean delKey(String key) {
        Jedis client = jedisPool.getResource();
        System.out.println("del key=" + key);
        client.del(key);
        return true;
    }

    public <T> boolean hset(String key, String field, T t) {
        Jedis client = jedisPool.getResource();
        String afterSerialize = JsonUtil.beanToJson(t);
        client.hset(key, field, afterSerialize);
        return true;
    }

    /**
     * 存入的时hash结构的数据
     *
     * @param key
     * key
     * @param map
     * map的key实质为field。
     * @return
     */
    public <T, S> boolean hmset(String key, Map<T, S> map) {
        Jedis client = jedisPool.getResource();
        Iterator<Map.Entry<T, S>> iterator = map.entrySet().iterator();
        Map<String, String> stringMap = new HashMap<String, String>();
        String filed;
        String value;
        while (iterator.hasNext()) {
            Map.Entry<T, S> entry = iterator.next();
            filed = String.valueOf(entry.getKey());
            value = JsonUtil.beanToJson(entry.getValue());
            stringMap.put(filed, value);
        }
        client.hmset(key, stringMap);
        return true;
    }

    public <T> T hgetObject(String key, String field, Class<T> cls) {
        Jedis client = jedisPool.getResource();
        String value = client.hget(key, field);
        return (T) JsonUtil.jsonToBean(value, cls);
    }

    public String hgetString(String key, String field) {
        Jedis client = jedisPool.getResource();
        return client.hget(key, field);
    }

    public Map<String, String> hGetAll(String key) {
        Jedis client = jedisPool.getResource();
        return client.hgetAll(key);
    }

    public List<String> hkeys(String key) {
        Jedis client = jedisPool.getResource();
        List<String> fields = new ArrayList<String>();
        Set<String> set = client.hkeys(key);
        fields.addAll(set);
        return fields;
    }

    public List<String> hvals(String key) {
        Jedis client = jedisPool.getResource();
        List<String> values = client.hvals(key);
        return values;
    }

    public boolean hexists(String key, String field) {
        Jedis client = jedisPool.getResource();
        return client.hexists(key, field);
    }

    public long incr(String key) {
        Jedis client = jedisPool.getResource();
        return client.incr(key);
    }

    public void hdel(String key, String... fields) {
        Jedis client = jedisPool.getResource();
        client.hdel(key, fields);
    }

    /**
     *
     *
     * @param key
     * @param field
     */
    public void lpush(String key, String field) {
        Jedis client = jedisPool.getResource();
        client.lpush(key, field);
    }

    public void lpush(String key, Object obj) {
        Jedis client = jedisPool.getResource();
        String field = JsonUtil.beanToJson(obj);
        client.lpush(key, field);
    }

    /**
     * 该方法不适用于普通的调用，该方法只针对于错误日志记录
     *
     * @param key
     * @param field
     */
    public void lpushForErrorMsg(String key, String field) {
        Jedis client = jedisPool.getResource();
        if (client.llen(key) > 1000) {
            return;
        }
        client.lpush(key, field);
    }

    public long llen(String key){
        Jedis client = jedisPool.getResource();
        return client.llen(key);
    }

    public List<String> blPop(String key, int timeoutSeconds) {
        Jedis client = jedisPool.getResource();
        return client.blpop(timeoutSeconds, key);
    }

    public <T> long sadd(String key, String... values) {
        Jedis client = jedisPool.getResource();
        return client.sadd(key, values);
    }

    public <T> long sadd(String key, List<T> ts) {
        Jedis client = jedisPool.getResource();
        if (ts == null || ts.size() ==0) {
            return 0l;
        }
        String[] values = new String[ts.size()];
        for (int i = 0; i < ts.size(); i++) {
            values[i] = ts.get(i).toString();
        }
        return client.sadd(key, values);
    }

    public long srem(String key, String... values) {
        Jedis client = jedisPool.getResource();
        return client.srem(key, values);
    }

    public <T> long srem(String key, List<T> ts) {
        Jedis client = jedisPool.getResource();
        if (ts == null || ts.size() ==0) {
            return 0l;
        }
        String[] values = new String[ts.size()];
        for (int i = 0; i < ts.size(); i++) {
            values[i] = ts.get(i).toString();
        }
        return client.srem(key, values);
    }

    public Set<String> getByRange(String key, double min, double max) {
        Jedis client = jedisPool.getResource();
        return client.zrangeByScore(key, min, max);
    }

    public Long decr(String key) {
        Jedis client = jedisPool.getResource();
        return client.decr(key);
    }

    public Long hlen(String key) {
        Jedis client = jedisPool.getResource();
        return client.hlen(key);
    }

    public List<String> hmget(String key, String... fields) {
        Jedis client = jedisPool.getResource();
        return client.hmget(key, fields);
    }

    /**
     * 从redis里面得到以 某字符串开头的所有key
     *
     * @param str
     * */
    public Set<String> getKeyByStr(String str) {
        Jedis client = jedisPool.getResource();
        return client.keys(str);
    }

    public void ltrim(String key, int start, int stop){
        Jedis client = jedisPool.getResource();
        client.ltrim(key, start, stop);
    }
    /**
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key,Integer seconds){
        Jedis client = jedisPool.getResource();
        Long success = 1l;
        success = client.expire(key, seconds);
        return success;
    }

    /**
     * 存入的时hash结构的数据,并且去掉value中的引号
     *
     * @param key
     * key
     * @param map
     * map的key实质为field。
     * @return
     */
    public <T, S> boolean hmsetWithoutQuotationMarks(String key, Map<T, S> map) {
        Jedis client = jedisPool.getResource();
        Iterator<Map.Entry<T, S>> iterator = map.entrySet().iterator();
        Map<String, String> stringMap = new HashMap<String, String>();
        String filed;
        String value;
        while (iterator.hasNext()) {
            Map.Entry<T, S> entry = iterator.next();
            filed = String.valueOf(entry.getKey());
            value = JSON.toJSONString(entry.getValue()).replace("\"", "");
            stringMap.put(filed, value);
        }
        client.hmset(key, stringMap);
        return true;
    }
}
