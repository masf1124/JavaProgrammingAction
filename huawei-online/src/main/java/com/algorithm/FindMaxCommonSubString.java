package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-13-21
 * 题目描述
 * 查找两个字符串a,b中的最长公共子串。若有多个，输出在较短串中最先出现的那个。
 */
public class FindMaxCommonSubString {

    public static String findMaxCommonSubString(String str1,String str2){
        if(str1==null || str1.length()==0 ||str2==null || str2.length()==0){
            return "";
        }
        int maxLen = 0;
        int index = 0;

        boolean isValidSub = true;
        String resSub ="";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str1.length();){
            for(int j=0;j<str2.length();j++){
                if(str1.charAt(i)==str2.charAt(j)){
                    sb.append(str1.charAt(i));
                    i++;
                    continue;
                }else{
                    String tempSub = new String(sb);
                    if(tempSub.length()>resSub.length()){
                        resSub = tempSub;
                        maxLen = resSub.length();
                    }
                    break;
                }
            }
        }
        return resSub;
    }

    public static void main(String[] args) {
        System.out.println(findMaxCommonSubString("abcdefghijklmnop","abcsafjklmnopqrstuvw"));
    }
}
