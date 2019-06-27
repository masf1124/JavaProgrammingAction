package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-05-25-22
 * 单例模式：懒汉式
 */
//使用final防止类被继承
public final class A02_Singleton_lazy {
    /**
     * 懒汉式单例模式优缺点
     * 在多线程环境下不保证实例的唯一性
     * 在需要获取实例的时候初始化，即懒加载
     */
    //实例变量
    private byte[] data = new byte[1024];

    //定义实例，但不直接初始化
    private static A02_Singleton_lazy singleton_lazy = null;

    //私有构造方法，防止类外部使用new
    private A02_Singleton_lazy(){}

    public static A02_Singleton_lazy getInstance(){
        if(null==singleton_lazy){
            singleton_lazy = new A02_Singleton_lazy();
        }
        return singleton_lazy;
    }
}
