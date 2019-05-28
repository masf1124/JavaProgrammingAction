package com.algorithm.Exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: com.algorithm.Exception
 * @author: mashifei
 * @create: 2019-05-28-20
 */
@Getter
@Setter
public class CacheLockException extends Throwable{

    private String msg;

    public CacheLockException(){
        super();
    }

    public CacheLockException(String msg){
        System.out.println(msg);
    }
}
