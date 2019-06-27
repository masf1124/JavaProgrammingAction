package com.algorithm;

import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-19-19
 */
public class TestString {

    public static int getResult(String original){
        if(original.length()<2){
            return -1;
        }
        original = original.substring(0,original.length()-2);
        while(!isCommonString(original) && original.length()>=2){
            original = original.substring(0,original.length()-2);
        }
        return original.length();
    }

    private static boolean isCommonString(String str){
        if(str.length()%2!=0){
            return false;
        }

        int len = str.length()/2;
        for(int i=0;i<len;i++){
            if(str.charAt(i)!=str.charAt(len+i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String original = sc.nextLine();
            System.out.println(getResult(original));
        }
    }
}
