package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-06-21
 */
public class A32_LongestValidParentheses {

    /**
     * 思路有问题，需要再想想
     * StringBuilder sb = new StringBuilder();
     *             for(int i=0;i<regex.length();i++){
     *                 sb.append("\\"+regex.charAt(i));
     *             }
     *             regex = new String(sb);
     *             s = s.replaceFirst(regex,"");
     *             count+=2;
     *             regex.replace("\\","");
     *             regex.concat("()");
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int count= 0;
        String regex = "()";
        while(s.contains(regex)){
            regex = regex.concat("()");
            count+=2;
        }
        return count;
    }

    private List<String> concatList(String regex){
        List<String> resList = new ArrayList<>();
        if(!resList.contains(regex.concat("()"))){
            resList.add(regex.concat("()"));
        }
        if(!resList.contains("(")){
            resList.add(regex.concat("()"));
        }
        if(!resList.contains("()".concat(regex))){
            resList.add("()".concat(regex));
        }
        return null;
    }

    public static void main(String[] args) {
        String s =")()())";
        System.out.println(longestValidParentheses(s));
    }
}
