package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-05-21
 */
public class A26_RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        if(nums==null ||nums.length ==0){
            return 0;
        }
        if(nums.length==1){
            return 1;
        }
        int len = 0;
        boolean flag = false;
        boolean isOneTypeEle = true;
        for(int i=0;i<nums.length-1;){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]!=nums[j]){
                    nums[i+1]=nums[j];
                    i++;
                    len =i+1;
                    isOneTypeEle = false;
                }
                if(j==nums.length-1){
                    flag=true;
                }
            }
            if(flag){
                break;
            }
        }
        if(isOneTypeEle){
            len=1;
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int len = removeDuplicates(nums);
        System.out.println(len);
        System.out.println();
        for(int i=0;i<len;i++){
            System.out.print(nums[i]+" ");
        }
    }
}
