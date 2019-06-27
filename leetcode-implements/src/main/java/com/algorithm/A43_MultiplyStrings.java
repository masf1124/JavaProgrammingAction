package com.algorithm;

import java.util.Stack;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-11-21
 */
public class A43_MultiplyStrings {

    /**
     * 没做出来？乘法的原理
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if(num1==null ||num2==null||num1.equals("") || num2.equals("")){
            return null;
        }
        int carry = 0;
        if((num1.length()==1 && num1.equals("0")) ||(num2.length()==1 && num2.equals("0"))){
            return new String("0");
        }

        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        if(num1.length()>=num2.length()){
            for(int i=num2.length()-1;i>=0;i--){
                if(carry==0){
                    stack.push(((Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1)))%10)+"");
                    carry = (Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1)))/10;
                }else{
                    stack.push(((Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1))+carry)%10)+"");
                    carry = (Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1))+carry)/10;
                }
            }

            for(int j =num1.length()-num2.length()-1;j>=0;j--){
                if(carry==0){
                    stack.push(((Integer.valueOf(num1.substring(j,j+1)))%10)+"");
                }else{
                    stack.push(((Integer.valueOf(num1.substring(j,j+1))+carry)%10)+"");
                    carry = (Integer.valueOf(num1.substring(j,j+1))+carry)/10;
                }
            }
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            return new String(sb);
        }else{
            for(int i=num1.length()-1;i>=0;i--){
                if(carry==0){
                    stack.push(((Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1)))%10)+"");
                    carry = (Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1)))/10;
                }else{
                    stack.push(((Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1))+carry)%10)+"");
                    carry = (Integer.valueOf(num1.substring(i,i+1))*Integer.valueOf(num2.substring(i,i+1))+carry)/10;
                }
            }

            for(int j =num2.length()-num1.length()-1;j>=0;j--){
                if(carry==0){
                    stack.push(((Integer.valueOf(num2.substring(j,j+1)))%10)+"");
                }else{
                    stack.push(((Integer.valueOf(num2.substring(j,j+1))+carry)%10)+"");
                    carry = (Integer.valueOf(num2.substring(j,j+1))+carry)/10;
                }
            }
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            return new String(sb);
        }
    }

    public static void main(String[] args) {
        String num1 = "123";

        String num2 = "456";
        //System.out.println(num2.substring(1,2));
        System.out.println(multiply(num1,num2));
    }
}
