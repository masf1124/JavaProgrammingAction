package com.algorithm;

import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-15-21
 */
public class MatrixMutilply {

    public static int[][] matrixMutilply(int[][] fstMatrix,int[][] secMatrix,
                                         int firstRow,int secondCol){
        int common = fstMatrix[0].length;
        int[][] resMatrix = new int[firstRow][secondCol];
        for(int i=0;i<firstRow;i++){
            for(int j=0;j<secondCol;j++){
                for(int k=0;k<common;k++){
                    resMatrix[i][j] +=fstMatrix[i][k]*secMatrix[k][j];
                }
            }
        }
        return resMatrix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int firstRow = Integer.valueOf(sc.nextLine());
            int common = Integer.valueOf(sc.nextLine());
            int secondCol = Integer.valueOf(sc.nextLine());

            int[][] fstMatrix = new int[firstRow][common];
            int[][] secMatrix = new int[common][secondCol];

            for(int i=0;i<firstRow;i++){
                String[] numArrStr = sc.nextLine().split(" ");
                for(int j=0;j<common;j++){
                    fstMatrix[i][j] = Integer.valueOf(numArrStr[j]);
                }
            }

            for(int i=0;i<common;i++){
                String[] numArrStr = sc.nextLine().split(" ");
                for(int j=0;j<secondCol;j++){
                    secMatrix[i][j] = Integer.valueOf(numArrStr[j]);
                }
            }

            int[][] resMatrix = matrixMutilply(fstMatrix,secMatrix,firstRow,secondCol);

            for(int i=0;i<firstRow;i++){
                for(int j=0;j<secondCol;j++){
                    System.out.print(resMatrix[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
}
