package com.algorithm;

import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-15-22
 */
public class CalculateCountOfMatrix {

    /**
     * 可能不止三个矩阵，注意计算
     * @param model
     * @return
     */
    public static int calculateCount(String[] firstMatrix,String[] secondMatrix,
                                     String[] thirdMatrix,String model){
        int resInt ;
        int[] middle = new int[2];
        if(model.equals("((AB)C)")){
            int[] firstRowAndCol = new int[2];
            firstRowAndCol[0] = Integer.valueOf(firstMatrix[0]);
            firstRowAndCol[1] = Integer.valueOf(firstMatrix[1]);

            int[] secondRowAndCol = new int[2];
            secondRowAndCol[0] = Integer.valueOf(secondMatrix[0]);
            secondRowAndCol[1] = Integer.valueOf(secondMatrix[1]);
            resInt = firstRowAndCol[0]*firstRowAndCol[1]*secondRowAndCol[1];

            middle[0] = firstRowAndCol[0];
            middle[1] = secondRowAndCol[1];

            int[] thridRowAndCol = new int[2];
            thridRowAndCol[0] = Integer.valueOf(thirdMatrix[0]);
            thridRowAndCol[1] = Integer.valueOf(thirdMatrix[1]);

            resInt+=middle[0]*middle[1]*thridRowAndCol[1];
        }else{

            int[] thridRowAndCol = new int[2];
            thridRowAndCol[0] = Integer.valueOf(thirdMatrix[0]);
            thridRowAndCol[1] = Integer.valueOf(thirdMatrix[1]);

            int[] secondRowAndCol = new int[2];
            secondRowAndCol[0] = Integer.valueOf(secondMatrix[0]);
            secondRowAndCol[1] = Integer.valueOf(secondMatrix[1]);
            resInt = thridRowAndCol[0]*thridRowAndCol[1]*secondRowAndCol[0];

            middle[0] = secondRowAndCol[0];
            middle[1] = thridRowAndCol[1];



            int[] firstRowAndCol = new int[2];
            firstRowAndCol[0] = Integer.valueOf(firstMatrix[0]);
            firstRowAndCol[1] = Integer.valueOf(firstMatrix[1]);

            resInt+=firstRowAndCol[0]*firstRowAndCol[1]*middle[1];
        }

        return resInt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int NumOfMatrix = Integer.valueOf(sc.nextLine());
            String[] firstMatrix = sc.nextLine().split(" ");
            String[] secondMatrix = sc.nextLine().split(" ");
            String[] thirdMatrix = sc.nextLine().split(" ");

            String model = sc.nextLine();
            System.out.println(calculateCount(firstMatrix,secondMatrix,thirdMatrix,model));
        }
    }
}
