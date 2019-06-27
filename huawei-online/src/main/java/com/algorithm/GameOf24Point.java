package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-15-20
 */
public class GameOf24Point {



    public static boolean getResult(List<Integer> numList){

        return false;
    }

    private static boolean getResultCore(List<Integer> numList,int temp){

        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while((line=br.readLine())!=null){
            String[] strs = line.split(" ");
            List<Integer> numList = new ArrayList<>();
            for(int i=0;i<strs.length;i++){
                numList.add(Integer.valueOf(strs[i]));
            }


        }
    }
}
