package com.algorithm.wy;

import java.util.Scanner;

/**
 * Created by seu627 on 2019/6/26
 * 题目描述
 * 小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。
 * 并且小Q对于能否被3整除这个性质很感兴趣。
 * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除
 */
public class DivideThree {

    public static int getDivideByThree(int left,int right){
        int count = 0;
        for(int i=left;i<=right;i++){
            if(i%3%2==0){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String[] nums = sc.nextLine().split(" ");
            int left = Integer.valueOf(nums[0]);
            int right = Integer.valueOf(nums[1]);

            System.out.println(getDivideByThree(left,right));
        }
    }
}
