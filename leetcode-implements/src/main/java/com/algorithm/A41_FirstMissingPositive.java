package com.algorithm;

import java.util.PriorityQueue;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-10-21
 */
public class A41_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0 && !queue.contains(nums[i])){
                queue.add(nums[i]);
            }
        }

        int index = 0;
        int len = queue.size()+1;
        for(int j=1;j<len;j++){
            if(queue.remove()!=j){
                index = j;
                break;
            }
        }

        return index==0?len:index;
    }

    public static void main(String[] args) {
        //int[] nums = {1,2,0};
        //int[] nums = {3,4,-1,1};
        //int[] nums = {7,8,9,11,12};
        int[] nums = {1,1000};
        System.out.println(firstMissingPositive(nums));
    }
}
