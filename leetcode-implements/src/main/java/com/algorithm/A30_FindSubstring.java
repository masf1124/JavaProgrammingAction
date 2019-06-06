package com.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-06-20
 */
public class A30_FindSubstring {

    /**
     * 方法可行，会使得栈溢出，改动态规划
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<String> tempList;
        List<Integer> resList = new ArrayList<>();
        if(s==null || words==null){
            return null;
        }
        tempList = concatString(words);
        System.out.println(tempList);
        Set<String> stringSet = new HashSet<>();
        for(String str:tempList){
            stringSet.add(str);
        }
        for(String str:stringSet){
            int index = s.indexOf(str);
            if(index!=-1){
                resList.add(index);
            }
        }
        return resList;
    }

    private static List<String> concatString(String[] words){
        int n = words.length-1;
        List<String> resList = concatStringCore(words,n);
        return resList;
    }

    private static List<String> concatStringCore(String[] words,int n){
        StringBuilder sb = new StringBuilder();
        List<String> resList = new ArrayList<>();
        if(n==0){
            resList.add(words[0]);
            return resList;
        }
        List<String> tempList = concatStringCore(words,n-2);
        for(String s:tempList){
            resList.add(words[n].concat(words[n-1]).concat(s));
            resList.add(words[n].concat(s).concat(words[n-1]));
            resList.add(words[n-1].concat(words[n]).concat(s));
            resList.add(words[n-1].concat(s).concat(words[n]));
            resList.add(s.concat(words[n]).concat(words[n-1]));
            resList.add(s.concat(words[n-1]).concat(words[n]));
        }
        return resList;
    }

    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"foo","bar","the"};
        System.out.println(findSubstring(s,words));
    }
}
