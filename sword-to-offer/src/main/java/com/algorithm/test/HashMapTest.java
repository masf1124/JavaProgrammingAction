package com.algorithm.test;

/**
 * @program: com.algorithm.test
 * @author: mashifei
 * @create: 2019-05-29-22
 */
public class HashMapTest {

    private static int tableSizeFor(int cap){
        int n = cap-1;
        n|=n>>>1;
        System.out.println("1    "+n);
        System.out.println(n>>>2);
        n|=n>>>2;
        System.out.println("2    "+n);
        n|=n>>>4;
        System.out.println("4    "+n);
        n|=n>>>8;
        System.out.println("8    "+n);
        n|=n>>>16;
        System.out.println("16    "+n);
        return (n<0)?1:(n>Integer.MAX_VALUE?Integer.MAX_VALUE:n+1);
    }

    public static void main(String[] args) {
        for(int i=10;i<=20;i++){
            System.out.println("tableSize= "+tableSizeFor(i)+"   "+i);
        }
    }
}
