package com.algorithm.lru.two;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU cache 通过组合的方式使用LinkedHashMap
 * @program: com.algorithm.lru.two
 * @author: mashifei
 * @create: 2019-06-03-14
 */
public class LRUCache {

    private final int initialCapacity;

    private Map<Integer,Integer> cache;

    public LRUCache(final int initialCapacity){
        this.initialCapacity = initialCapacity;
        cache = new LinkedHashMap<Integer,Integer>(initialCapacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size()>initialCapacity;
            }
        };
    }


    public void put(int key,int value){
        cache.put(key,value);
    }

    public int get(int key){
        return cache.get(key);
    }

    public void remove(int key){
        cache.remove(key);
    }

    @Override
    public String toString(){
        StringBuilder cacheStr = new StringBuilder();

        cacheStr.append("{");
        for(Map.Entry<Integer,Integer> entry:cache.entrySet()){
            cacheStr.append("[" + entry.getKey()+","+entry.getValue()+"]");
        }
        cacheStr.append("}");

        return new String(cacheStr);
    }
}
