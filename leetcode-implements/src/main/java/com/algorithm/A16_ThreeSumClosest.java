package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-03-20
 */
public class A16_ThreeSumClosest {

    public static int threeSum(int[] nums,int target){
        int res = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<=nums.length-3;i++){
            twoSum(nums,i,res,target);
        }
        return 0;
    }

    private static void twoSum(int[] nums,int startIndex,int result ,int  target){
        for(int j=startIndex+1,k=nums.length-1;j<k;){
            if(Math.abs(nums[startIndex]+nums[j]+nums[k]-target)<result){
                result = nums[startIndex]+nums[j]+nums[k];
                k--;
                j++;
            }else if(Math.abs(nums[startIndex]+nums[j]+nums[k]-target)>result){
                k--;
            }else{
                j++;
            }
        }
    }

    public static void main(String[] args) {

    }
}
