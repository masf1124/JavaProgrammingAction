package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by seu627 on 2019/6/24
 */
public class SplitStringPerEightChar {

    public static List<String> getResult(String[] target){
        if(target==null || target.length==0){
            return null;
        }


        List<String> resList = new ArrayList<>();
        for(int i = 0;i<target.length;i++){
            resList.addAll(getOneString(target[i]));
        }
        return resList;
    }

    private static List<String> getOneString(String str){
        List<String> strList = new ArrayList<>();
        int len = str.length();
        for(int i=0;i+8<len;i+=8){
            strList.add(str.substring(i,i+8));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(len-len%8));
        for(int i=0;i<8-len%8;i++){
            sb.append("0");
        }

        String lastStr = new String(sb);
        if(!lastStr.equals("00000000")){
            strList.add(lastStr);
        }

        return strList;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            int numOfStr = Integer.valueOf(sc.nextLine());
            String[] targetStrArr = new String[numOfStr];

            for(int i=0;i<numOfStr;i++){
                targetStrArr[i] = sc.nextLine().trim();
            }

            List<String> resList = getResult(targetStrArr);
            for(String s:resList){
                if(!s.equals("00000000")){
                    System.out.println(s);
                }
            }
        }
    }
}
