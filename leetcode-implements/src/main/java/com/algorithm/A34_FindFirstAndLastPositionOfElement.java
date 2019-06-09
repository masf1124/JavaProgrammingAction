package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-09-20
 */
public class A34_FindFirstAndLastPositionOfElement {

    public static int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length ==0){
            return new int[]{-1,-1};
        }
        if(nums.length==1){
            if(nums[0]==target){
                return new int[]{0,0};
            }else{
                return new int[]{-1,-1};
            }
        }
        int indexFirst = searchRangeFirst(nums,target);
        int indexLast = searchRangeLast(nums,target);
        return new int[]{indexFirst,indexLast};
    }

    public static int searchRangeFirst(int[] nums,int target){
        return searchRangeFirstCore(nums,target,0,nums.length-1);
    }

    private static int searchRangeFirstCore(int[] nums,int target,int left,int right){
        boolean findFlag = false;
        int index = 0;
        while(left<=right){
            int mid = left +(right-left)/2;
            if(nums[mid]==target){
                findFlag = true;
                while(mid>=0){
                    mid--;
                    if(mid>=0 &&nums[mid]!=target){
                        index = mid +1;
                        break;
                    }else{
                        index = mid;
                    }
                }
            }else if(nums[mid]<target ){
                left = mid+1;
            }else{
                right = mid-1;
            }
            if(findFlag){
                return index>=0?index:0;
            }
        }
        return -1;
    }

    public static int searchRangeLast(int[] nums,int target){
        return searchRangeLastCore(nums,target,0,nums.length-1);
    }

    private static int searchRangeLastCore(int[] nums,int target,int left,int right){
        boolean findFlag = false;
        int index = 0;
        while(left<=right){
            int mid = left +(right-left)/2;
            if(nums[mid]==target){
                findFlag = true;
                while(mid<nums.length){
                    mid++;
                    if(mid<nums.length&& nums[mid]!=target ){
                        index = mid -1;
                        break;
                    }else{
                        index = mid;
                    }
                }
            }else if(nums[mid]<target ){
                left = mid+1;
            }else{
                right = mid-1;
            }
            if(findFlag){
                return index<nums.length?index:nums.length-1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[] nums = {5,7,7,8,8,10};
        //int target = 6;
        int[] nums = {1,4};
        int target = 4;
        int[] output = searchRange(nums,target);
        System.out.println(output[0]+" "+output[1]);
    }
}
