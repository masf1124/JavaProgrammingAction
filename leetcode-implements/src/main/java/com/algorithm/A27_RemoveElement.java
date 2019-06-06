package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-05-22
 */
public class A27_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        if(nums==null && nums.length==0){
            return 0;
        }
        int len ;
        int count = 0;
        for(int i=0;i<nums.length-count;){
            if(nums[i]==val){
                for(int j=i;j<nums.length-1;j++){
                    nums[j] = nums[j+1];
                }
                count++;
            }
            if(nums[i]!=val){
                i++;
            }
        }
        len = nums.length-count;
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int len = removeElement(nums,val);
        System.out.println(len);
        for(int i=0;i<len;i++){
            System.out.print(nums[i]+" ");
        }
    }
}
