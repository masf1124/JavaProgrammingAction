package com.algorithm.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @program: com.algorithm.pattern.observer
 * @author: mashifei
 * @create: 2019-06-04-14
 */
public class ProductList extends Observable {
    private List<String> productList = null;//产品列表

    private static ProductList instance;//类的唯一实例

    private ProductList(){}//构造方法私有化

    /**
     * 取得类的唯一实例
     * @return
     */
    public static ProductList getInstance(){
        if(instance==null){
            instance = new ProductList();
            instance.productList = new ArrayList<>();
        }
        return instance;
    }

    /**
     * 增加观察者（电商接口）
     * @param observer
     */
    public void addProductListObserver(Observer observer){
        this.addObserver(observer);
    }

    /**
     * 新增产品
     * @param newProduct
     */
    public void addProduct(String newProduct){
        productList.add(newProduct);
        System.out.println("产品列表新增了产品： "+newProduct);
        this.setChanged();//设置被观察者对象发生变化
        this.notifyObservers(newProduct);//通知观察者，并传递新产品
    }
}
