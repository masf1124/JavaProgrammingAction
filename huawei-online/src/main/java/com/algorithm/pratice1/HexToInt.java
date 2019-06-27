package com.algorithm.pratice1;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: com.algorithm.pratice1
 * @author: mashifei
 * @create: 2019-06-17-20
 * 写出一个程序，接受一个十六进制的数值字符串，
 * 输出该数值的十进制字符串。（多组同时输入 ）
 */
public class HexToInt {

    /**
     * 有问题 需要继续改
     * @param hex
     * @return
     */
    public static String hexToD(String hex){

        if(hex.startsWith("0x")){
            StringBuilder sb = new StringBuilder();
            Stack<String> stack = new Stack<>();
            String tmpString = hex.substring(2);

            int c = 0;
            int result = 0;
            int tmp = 16;
            for(int i=tmpString.length()-1;i>=0;i--){
                result += parseHexToD(tmpString.charAt(i))*Math.pow(16,tmpString.length()-i-1);
                stack.push(result%10+"");
                result=result/10;
            }
            while(result!=0){
                stack.push(result%10+"");
                result=result/10;
            }

            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            return new String(sb);
        }else{
            return null;
        }
    }

    private static int parseHexToD(char cha){
        int res = 0;
        switch (cha){
            case 'F' :
                res = 15;
                break;
            case 'E' :
                res = 14;
                break;
            case 'D' :
                res = 13;
                break;
            case 'C' :
                res = 12;
                break;
            case 'B' :
                res = 11;
                break;
            case 'A' :
                res = 10;
                break;
            case '9' :
                res = 9;
                break;
            case '8' :
                res = 8;
                break;
            case '7' :
                res = 7;
                break;
            case '6' :
                res = 6;
                break;
            case '5' :
                res = 5;
                break;
            case '4' :
                res = 4;
                break;
            case '3' :
                res = 3;
                break;
            case '2' :
                res = 2;
                break;
            case '1' :
                res = 1;
                break;
            case '0' :
                res = 0;
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println(hexToD(sc.nextLine()));
        }
    }
}
