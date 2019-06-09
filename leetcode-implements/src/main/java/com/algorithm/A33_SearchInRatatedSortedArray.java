package com.algorithm;

import java.util.Arrays;

/**
 *
 * 第一步：查找到分段位置
 * 第二步：对两部分分别进行二分查找
 *
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-08-20
 */
public class A33_SearchInRatatedSortedArray {

    public static int search(int[] nums, int target) {
        //Arrays.sort(nums);
        int pivot = searchPivot(nums,0,nums.length-1);
        int result = -1;
        if(pivot<0){
            result = searchCore(nums,target,0,nums.length-1);
        }else{
            result = searchCore(nums,target,0,pivot);
            result = result==-1?searchCore(nums,target,pivot,nums.length-1):result;
        }

        return result;
    }

    private static int searchPivot(int[] nums,int left,int right){
        if(nums[left]>nums[right]){
            while(left<right){
                int mid = left+(right-left)/2;
                if(nums[mid]>nums[mid+1] && nums[mid]>nums[mid-1]){
                    return mid;
                }else if(nums[mid]<nums[left]){
                    left = mid;
                }else if(nums[mid]>nums[right]){
                    right = mid;
                }
            }
        }else{
            return -1;
        }
        return 0;
    }

    private static int searchCore(int[] nums,int target,int left,int right){

        while(left<right){
            int mid = left +(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left = mid;
            }else{
                right = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums,target));
    }
}
