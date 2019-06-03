package com.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-02-16
 *
 */
public class A12_IntToRoman {
    private static Map<Integer,String> intToRomanMap = new HashMap<Integer,String>();
    static{
        intToRomanMap.put(1000,"M");
        intToRomanMap.put(900,"CM");
        intToRomanMap.put(800,"DCCC");
        intToRomanMap.put(700,"DCC");
        intToRomanMap.put(600,"DC");
        intToRomanMap.put(500,"D");
        intToRomanMap.put(400,"CD");
        intToRomanMap.put(300,"CCC");
        intToRomanMap.put(200,"CC");
        intToRomanMap.put(100,"C");
        intToRomanMap.put(90,"XC");
        intToRomanMap.put(80,"LXXX");
        intToRomanMap.put(70,"LXX");
        intToRomanMap.put(60,"LX");
        intToRomanMap.put(50,"L");
        intToRomanMap.put(40,"XL");
        intToRomanMap.put(30,"XXX");
        intToRomanMap.put(20,"XX");
        intToRomanMap.put(10,"X");
        intToRomanMap.put(9,"IX");
        intToRomanMap.put(8,"VIII");
        intToRomanMap.put(7,"VII");
        intToRomanMap.put(6,"VI");
        intToRomanMap.put(5,"V");
        intToRomanMap.put(4,"IV");
        intToRomanMap.put(3,"III");
        intToRomanMap.put(2,"II");
        intToRomanMap.put(1,"I");
    }

    public static String intToRoman(int num){
        StringBuilder romanStr = new StringBuilder();
        while(num>0){
            if(num>=1000){
                int count = 0;
                count = num/1000;
                for(int i=0;i<count;i++){
                    romanStr.append(intToRomanMap.get(1000));
                }
                num = num%1000;
            }else if(num>=100){
                int tmp = num/100;
                romanStr.append(intToRomanMap.get(tmp*100));
                num = num-tmp*100;
            }else if(num>=10){
                int tmp = num/10;
                romanStr.append(intToRomanMap.get(tmp*10));
                num = num-tmp*10;
            }else{
                romanStr.append(intToRomanMap.get(num));
                num=0;
            }
        }

        return new String(romanStr);
    }

    public static void main(String[] args) {
        String result = intToRoman(1994);
        System.out.println(result);
    }
}
