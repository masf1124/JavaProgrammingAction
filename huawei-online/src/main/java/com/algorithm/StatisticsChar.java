package com.algorithm;

import java.util.Scanner;

/**
 * Created by seu627 on 2019/6/24
 */
public class StatisticsChar {


    public static String getResult(String target){
        char[] chas = target.toCharArray();
        StringBuilder sb = new StringBuilder();

        int[] countPerChar = new int[256];
        for(int j=0;j<256;j++){
            countPerChar[j] = 0;
        }

        for(int i=0;i<chas.length;i++){
            if((chas[i]>='a' && chas[i]<='z') || (chas[i]>='A' && chas[i]<='Z')
                    || (chas[i]>='0' && chas[i]<='9') || chas[i]==' '){
                countPerChar[chas[i]]++;
            }
        }

        //统计字符，由字符对应字数多到少
        for(int k=0;k<256;k++){
            int maxIndexOfCount = 0;
            int maxOfCount = 0;
            for(int i=0;i<256;i++){
                if(countPerChar[i]>maxOfCount){
                    maxOfCount = countPerChar[i];
                    maxIndexOfCount = i;
                }
            }
            sb.append((char)(maxIndexOfCount));
            countPerChar[maxIndexOfCount] = 0;
        }

        return new String(sb).trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String target = sc.nextLine();

            System.out.println(getResult(target));
        }
    }
}
