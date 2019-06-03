package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-02-20
 */
public class A14_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs){
        if(strs==null ||strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        int minLength = Integer.MAX_VALUE;
        int strIndex = 0;
        boolean isEndFlag = false;
        for(int i=0;i<strs.length;i++){
            if(strs[i].length()<minLength){
                minLength = strs[i].length();
                strIndex = i;
            }
        }

        for(int j=0;j<strs[strIndex].length();j++){
            for(int k=0;k<strs.length;k++){
                if(strs[k].charAt(j)!=strs[strIndex].charAt(j)){
                    isEndFlag = true;
                    break;
                }
            }
            if(isEndFlag){
                break;
            }
            sb.append(strs[strIndex].charAt(j));
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String resStr = longestCommonPrefix(strs);
        System.out.println(resStr);
    }
}
