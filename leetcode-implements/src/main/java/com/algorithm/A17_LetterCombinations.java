package com.algorithm;

import java.util.ArrayList;
import java.util.List;
/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-03-21
 */
public class A17_LetterCombinations {

    private static String[] digitsToString = new String[10];

    static{
        digitsToString[0] = "";
        digitsToString[1] = " ";
        digitsToString[2] = "abc";
        digitsToString[3] = "def";
        digitsToString[4] = "ghi";
        digitsToString[5] = "jkl";
        digitsToString[6] = "mno";
        digitsToString[7] = "pqrs";
        digitsToString[8] = "tuv";
        digitsToString[9] = "wxyz";

    }

    /**
     * 递归实现会出现Memory Limit Exceeded 修改成动态规划
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits){
        char[] chas = digits.toCharArray();
        int[] nums = new int[chas.length];
        for(int i=0;i<chas.length;i++){
            nums[i] = Integer.valueOf(chas[i])-48;
        }
        String[] digitsToStr = new String[nums.length];
        for(int j=0;j<digitsToStr.length;j++){
            digitsToStr[j] = digitsToString[nums[j]];
        }

       return letterCore(digitsToStr,0);
    }

    private static List<String> letterCore(String[] strArr,int index){
        List<String> strList = new ArrayList<>();
        if(index==strArr.length-1){
            for(int i=0;i<strArr[index].length();i++){
                strList.add(strArr[index].substring(i,i+1));
            }
            return strList;
        }
        List<String> resList = letterCore(strArr,index+1);
        for(int i=0;i<strArr[index].length();i++){
            for(String ele:resList){
                strList.add(strArr[index].substring(i,i+1)+ele);
            }
        }
        return strList;
    }

    public static void main(String[] args) {
        String digits = "234";
        List<String> resList = letterCombinations(digits);
        for(String ele:resList){
            System.out.println(ele);
        }
    }
}
