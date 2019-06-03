package com.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-02-20
 */
public class A13_RomanToInt {

    private static Map<Character,Integer> romanToIntMap = new HashMap<Character, Integer>();

    static{
        romanToIntMap.put('M',1000);
        romanToIntMap.put('D',500);
        romanToIntMap.put('C',100);
        romanToIntMap.put('L',50);
        romanToIntMap.put('X',10);
        romanToIntMap.put('V',5);
        romanToIntMap.put('I',1);
    }

    public static int romanToInt(String s){
        char[] chars = s.toCharArray();
        int result = 0;
        for(int i=0;i<chars.length-1;i++){
            if(romanToIntMap.get(chars[i])<romanToIntMap.get(chars[i+1])){
                result-=romanToIntMap.get(chars[i]);
            }else{
                result+=romanToIntMap.get(chars[i]);
            }
        }
        result+=romanToIntMap.get(chars[chars.length-1]);
        return result;
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";
        int res = romanToInt(s);
        System.out.println(res);
    }
}
