package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: com.algorithm.pratice1
 * @author: mashifei
 * @create: 2019-06-17-21
 * 题目：
 * 公元前五世纪，我国古代数学家张丘建在《算经》一书中提出了“百鸡问题”：
 * 鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一。百钱买百鸡，问鸡翁、鸡母、鸡雏各几何？
 */
public class HanderedMoneyBuyHandredChicken {

    public static List<List<Integer>> getResult(){
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i=0;i<=100;i++){
            List<Integer> tmpList = new ArrayList<>();
            if((3*i+300)%4==0 && ((100-7*i)>0 &&(100-7*i)%4==0)){
                tmpList.add(i);
                tmpList.add((100-7*i)/4);
                tmpList.add((3*i+300)/4);
            }
            if(tmpList.size()>0){
                resultList.add(tmpList);
            }
        }
        return resultList;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            sc.nextLine();
            List<List<Integer>> resultList = getResult();
            for(List<Integer> list:resultList){
                for(Integer e:list){
                    System.out.print(e+" ");
                }
                System.out.println();
            }
        }
    }
}
