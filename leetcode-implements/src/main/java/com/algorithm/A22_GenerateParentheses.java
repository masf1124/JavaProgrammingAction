package com.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-04-21
 */
public class A22_GenerateParentheses {

    /**
     * 递归实现 运行时间长，占用内存少
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        return generateParenthesisCore(n);
    }

    private static List<String> generateParenthesisCore(int n){
        List<String> resList = new ArrayList<String>();
        if(n==1){
            resList.add("()");
            return resList;
        }
        List<String> tempList = generateParenthesisCore(n-1);
        Set<String> resSet = new HashSet<>();
        for(String s:tempList){
            for(int i=0;i<s.length();i++){
                resSet.add(s.substring(0,i)+"()"+s.substring(i));
            }
        }
        for(String s:resSet){
            resList.add(s);
        }
        return resList;
    }



    public static void main(String[] args) {
        List<String> resList = generateParenthesis(3);
        System.out.println(resList);
    }
}
