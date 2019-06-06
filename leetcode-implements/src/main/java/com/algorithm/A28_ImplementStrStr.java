package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-06-19
 */
public class A28_ImplementStrStr {

    public static int strStr(String haystack, String needle) {
        if((haystack==null && needle!=null) ||haystack.length()<needle.length()){
            return -1;
        }
        if(haystack.equals(needle)){
            return 0;
        }

        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack,needle));
    }
}
