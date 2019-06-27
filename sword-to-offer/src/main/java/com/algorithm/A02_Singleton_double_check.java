package com.algorithm;

import java.net.Socket;
import java.sql.Connection;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-05-25-23
 */
//使用final修饰，防止该类被继承
public final class A02_Singleton_double_check {

    //实例变量
    private byte[] data = new byte[1024];

    private static A02_Singleton_double_check singleton = null;

    private Connection connection;
    private Socket socket;

    private A02_Singleton_double_check(){
       //执行初始化操作
        // this.connection
       // this.socket
    }

    public static A02_Singleton_double_check getInstance(){
        //当singleton为null时，进入同步代码块，同时该判断避免了每次判断都需要进入代码块
        if(null==singleton){
            //同一时刻只有一个线程能进入同步块
            synchronized (A02_Singleton_double_check.class){
                if(null==singleton){
                    singleton = new A02_Singleton_double_check();
                }
            }
        }
        return singleton;
    }
}
