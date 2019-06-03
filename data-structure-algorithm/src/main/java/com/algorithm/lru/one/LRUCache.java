package com.algorithm.lru.one;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU Cache 通过继承LinkedHashMap的方式实现
 * @program: com.algorithm.lru.one
 * @author: mashifei
 * @create: 2019-06-03-13
 */
public class LRUCache extends LinkedHashMap<Integer,Integer> {

    private int initialCapacity;

    public LRUCache(int initialCapacity){
        super(initialCapacity,0.75f);
        this.initialCapacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size()>initialCapacity;
    }

    @Override
    public String toString(){
        StringBuilder cacheStr = new StringBuilder();

        cacheStr.append("{");
        for(Map.Entry<Integer,Integer> entry:this.entrySet()){
            cacheStr.append("[" + entry.getKey()+","+entry.getValue()+"]");
        }
        cacheStr.append("}");

        return new String(cacheStr);
    }
}
