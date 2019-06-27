package com.algorithm;

import java.util.Scanner;

/**
 * Created by seu627 on 2019/6/23
 *  面试题16：数值的整数次方
 *  题目：实现函数double Power(double base, int exponent)，求base的exponent
 *  次方。不得使用库函数，同时不需要考虑大数问题。
 */
public class A16_DoublePower {

    private static boolean globalInputInvalid = false;

    /**
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double power(double base,int exponent){
        if(equalZero(base) && (exponent<0)){
            globalInputInvalid = true;
            return 0.0;
        }

        if(exponent == 0){
            return 1.0;
        }

        int absExponent = exponent;
        if(absExponent<0){
            absExponent = -exponent;
        }

        double result = powerWithExponent(base,absExponent);

        if(exponent<0){
            result = 1.0/result;
        }

        return result;
    }


    private static double powerWithExponent(double base, int exponent){
        double result = 1.0;
        for(int i=0;i<exponent;i++){
            result *= base;
        }
        return result;
    }

    private static boolean equalZero(double base){
        return Math.abs(base-0.0)<1.0e-6;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            double base = Double.valueOf(sc.nextLine());
            int exponent = Integer.valueOf(sc.nextLine());

            System.out.println(power(base,exponent));
        }
    }
}
