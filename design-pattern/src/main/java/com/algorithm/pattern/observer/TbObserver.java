package com.algorithm.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @program: com.algorithm.pattern.observer
 * @author: mashifei
 * @create: 2019-06-04-14
 */
public class TbObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        String newProduct = (String)arg;
        System.out.println("发送新产品["+newProduct+"],同步到淘宝商城");
    }
}
