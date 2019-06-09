package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-09-21
 */
public class A35_SearchInsertPosition {

    /**
     * 遍历
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if(nums==null ||nums.length==0){
            return -1;
        }
        int index = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                return i;
            }else if(nums[i]>target){
                index = i;
                break;
            }
        }
        return index==-1?nums.length:index;
    }

    /**
     * 二分查找,待完成?
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsertBinary(int[] nums, int target) {


        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 0;
        System.out.println(searchInsert(nums,target));
    }
}
