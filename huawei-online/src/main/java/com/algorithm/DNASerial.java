package com.algorithm;

import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-12-22
 */
public class DNASerial {

    public static String findMaxRatio(String dnaSerial,int subLen){
        if(dnaSerial==null || dnaSerial.length()==0 ||dnaSerial.length()<subLen){
            return null;
        }
        Double maxRatio = 0D;
        String resSub = null;
        for(int i=0;i<=dnaSerial.length()-subLen;i++){
            String subDnaSerial = dnaSerial.substring(i,i+subLen);
            int count = 0;
            for(int j = 0;j<subDnaSerial.length();j++){
                if(subDnaSerial.charAt(j)=='G'||subDnaSerial.charAt(j)=='C'){
                    count++;
                }
            }
            if(maxRatio<1.0*count/subDnaSerial.length()){
                maxRatio = 1.0*count/subDnaSerial.length();
                resSub = subDnaSerial;
            }
        }
        return resSub;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String dnaSerial = sc.nextLine();
            int subLen = Integer.valueOf(sc.nextLine());
            System.out.println(findMaxRatio(dnaSerial,subLen));
            //System.out.println(findMaxRatio("AACTGTGCACGACCTGA",5));
        }
    }
}
