package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-04-21
 */
public class A20_ValidParenthese {

    /**
     * 使用字符串替换
     * @param s
     * @return
     */
    public static boolean isValid(String s){
        while(s.contains("()")||s.contains("[]") || s.contains("{}")){
            s = s.replace("()","").replace("[]","").replace("{}","");
        }
        return "".equals(s);
    }

    public static void main(String[] args) {
        String s = "{}{}{}()";
        System.out.println(isValid(s));
    }
}
