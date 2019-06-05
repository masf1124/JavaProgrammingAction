package com.algorithm.pattern.observer;

/**
 * @program: com.algorithm.pattern.observer
 * @author: mashifei
 * @create: 2019-06-04-15
 */
public class ObserverTest {

    public static void main(String[] args) {
        ProductList observable = ProductList.getInstance();
        JdObserver jdObserver = new JdObserver();
        TbObserver tbObserver = new TbObserver();

        observable.addObserver(jdObserver);
        observable.addObserver(tbObserver);
        observable.addProduct("新增产品1");
    }
}
