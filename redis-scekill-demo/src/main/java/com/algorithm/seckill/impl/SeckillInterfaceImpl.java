package com.algorithm.seckill.impl;

import com.algorithm.seckill.SeckillInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.algorithm.seckill.impl
 * @author: mashifei
 * @create: 2019-05-28-20
 */
public class SeckillInterfaceImpl implements SeckillInterface {

    static Map<Long,Long> inventory;

    static {
        inventory = new HashMap<>();
        inventory.put(1000001L,100001L);
        inventory.put(1000002L,100001L);
    }

    @Override
    public void seckill(String userId, long commidityId) {
        reduceInventory(commidityId);
    }

    //模拟秒杀，暂时认为一个秒杀就是讲库存减一
    public Long reduceInventory(Long commidityId){
        inventory.put(commidityId,inventory.get(commidityId)-1);
        return inventory.get(commidityId);
    }
}
