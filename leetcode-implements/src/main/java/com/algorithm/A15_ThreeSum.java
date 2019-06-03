package com.algorithm;

import java.util.*;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-02-21
 */
public class A15_ThreeSum {

    //暴力搜索方法，运行超时
    public static List<List<Integer>> threeSumRecur(int[] nums){
        List<List<Integer>> resList = new ArrayList<>() ;
        List<String> strList = new ArrayList<>();
        if(nums==null || nums.length<0){
            return null;
        }

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    if((nums[i]>0 && nums[j]>0 && nums[k]>0)
                            ||(nums[i]<0 && nums[j]<0 && nums[k]<0)){
                        continue;
                    }
                    List<Integer> list = new ArrayList<>();
                    String str ="";
                    if(nums[i]+nums[j]+nums[k]==0){
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        str = ""+nums[i]+nums[j]+nums[k];
                    }
                    if(list.size()>0 && !strList.contains(str)){
                        resList.add(list);
                        strList.add(str);
                    }
                }
            }
        }


        return resList;
    }


    public static List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> resList = new ArrayList<>();
        List<String> listStr = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<=nums.length-3;i++){
            twoSum(nums,i,-nums[i],resList,listStr);
        }
        return resList;
    }

    private static void twoSum(int[] nums,int startIndex,int desireSum,List<List<Integer>> resList,List<String> listStr){
        for(int j=startIndex+1,k=nums.length-1;j<k;){
            if(desireSum==nums[j]+nums[k]){
                List<Integer> list = new ArrayList<>();
                list.add(nums[startIndex]);
                list.add(nums[j]);
                list.add(nums[k]);
                if(!listStr.contains(""+nums[startIndex]+nums[j]+nums[k])){
                    resList.add(list);
                    listStr.add(""+nums[startIndex]+nums[j]+nums[k]);
                }
                k--;
                j++;
            }else if(desireSum<nums[j]+nums[k]){
                k--;
            }else{
                j++;
            }
        }
    }

    public static void main(String[] args) {
        //int[] nums = {-1,0,1,2,-1,-4};
        //int[] nums ={0,0,0,0};
        int[] nums = {3,0,-2,-1,1,2};
        List<List<Integer>> resList = threeSum(nums);
        for(List<Integer> list:resList){
            System.out.println(list);
        }
    }
}
