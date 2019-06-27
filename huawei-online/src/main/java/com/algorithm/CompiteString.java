package com.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-19-19
 */
public class CompiteString {

    public static int getResult(String target){
        int len = target.length();
        char[] chas = target.toCharArray();
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i=0;i<chas.length;i++){
            if(!charMap.containsKey(chas[i])){
                charMap.put(chas[i],1);
            }else{
                charMap.put(chas[i],charMap.get(chas[i])+1);
            }
        }

        int count = 0;
        for(Character cha:charMap.keySet()){
            if(charMap.get(cha)%2!=0){
                count ++;
            }
        }

        if(count ==0){
            return 1;
        }else{
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        while(sc.hasNextLine()){
            String target = sc.nextLine();
            System.out.println(getResult(target));
        }
    }
}
