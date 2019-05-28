package com.algorithm.spi;

import com.algorithm.Hello;

/**
 * @program: com.algorithm.spi
 * @author: mashifei
 * @create: 2019-05-28-15
 */
public class TextHello implements Hello {
    @Override
    public void sayHello(String msg) {
        System.out.println("Text hello :"+msg);
    }
}
