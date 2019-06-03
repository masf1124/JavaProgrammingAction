package com.algorithm.lru;

import com.algorithm.lru.two.LRUCache;

/**
 * @program: com.algorithm.lru
 * @author: mashifei
 * @create: 2019-06-03-14
 */
public class Test {

    public static void cacheTwoTest(){
        LRUCache cache2 = new LRUCache(5);

        cache2.put(5,5);
        cache2.put(4,4);
        cache2.put(3,3);
        cache2.put(2,2);
        cache2.put(1,1);
        System.out.println(cache2.toString());

        cache2.put(0,0);
        System.out.println(cache2.toString());
    }

    public static void cacheOneTest(){
        com.algorithm.lru.one.LRUCache cache = new com.algorithm.lru.one.LRUCache(5);
        cache.put(5,5);
        cache.put(4,4);
        cache.put(3,3);
        cache.put(2,2);
        cache.put(1,1);
        System.out.println(cache.toString());

        cache.put(0,0);
        cache.put(2,2);
        System.out.println(cache.toString());
    }

    public static void main(String[] args) {
        cacheOneTest();
        System.out.println("+++++++++++++++++++++++");
        cacheTwoTest();
    }
}
