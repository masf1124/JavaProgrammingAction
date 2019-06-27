package com.algorithm;

import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-12-21
 */
public class FindNumberOf1 {

    public static int findNumberOf1(int num){
        int count = 0;
        while((num&(num-1))!=0){
            num = num&(num-1);
            count++;
        }
        return count+1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int num = sc.nextInt();
            System.out.println(findNumberOf1(num));
        }
    }
}
