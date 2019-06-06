package com.algorithm;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-06-20
 */
public class A29_DivideTwoIntegers {


    /**
     * 遍历加减方法不好，时间复杂度较高
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        if(dividend==0){
            return 0;
        }
        int count = 0;
        if((dividend>0 && divisor>0) ||(dividend<0 && divisor<0)){
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);

            if(dividend==Integer.MIN_VALUE){
                while(dividend<=0){
                    count++;
                    dividend+=divisor;
                }
            }else{
                for(int i=0;i<dividend &&dividend>=divisor;i++){
                    count++;
                    dividend-=divisor;
                }
            }
        }

        if(((dividend>0 && divisor<0) ||(dividend<0 && divisor>0)) &&dividend!=Integer.MIN_VALUE){
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
            for(int i=0;i<dividend &&dividend>=divisor;i++){
                count++;
                dividend-=divisor;
            }
            count = -count;
        }
        return count;
    }


    public static void main(String[] args) {
        int dividend = -2147483648;
        int divisor = -1;
        System.out.println(Integer.MIN_VALUE);
        System.out.println(divide(dividend,divisor));
    }
}
