package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-10-21
 */
public class A39_CombinationSum {


    /**
     * 没做出来
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> candidateList = new ArrayList<>();
        for(int i=0;i<candidates.length;i++){
            candidateList.add(candidates[i]);
        }


        for(int i=0;i<candidates.length;i++){
            int tmp = target;
            while(tmp>=candidates[0]){
                if(candidateList.contains(tmp-candidates[i])){

                }
            }
        }
        return resList;
    }


    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates,target));
    }
}
