package com.algorithm;

import edu.princeton.cs.algs4.Quick;
import util.sort.QuickSort;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-05-26-22
 * 面试题3（一）：找出数组中重复的数字
 * 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
 * 那么对应的输出是重复的数字2或者3。
 */
public class A03_DuplicationInArray {

    public static boolean  duplicate(int[] array){
        if(null==array || array.length<=0){
            return false;
        }
        int length = array.length;
        for(int i=0;i<length;i++){
            if(array[i]<0 ||array[i]>=length){
                return false;
            }
        }

        for(int j=0;j<length;j++){
            while(j!=array[j]){//j位置上的元素不等于j
                if(array[j]==array[array[j]]){//判断j位置的元素array[j]，与array[j]位置的元素是否相等，如果相等，则找到一个重复元素
                    System.out.println(array[j]);
                    return true;
                }else{//交换两个位置上的元素，把array[j]放置在array[j]位置上
                    int temp = array[j];
                    array[j] = array[temp];
                    array[temp] = temp;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,0,2,5,3};
        duplicate(arr);
        QuickSort.sort(arr);
        //Quick.sort();

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
