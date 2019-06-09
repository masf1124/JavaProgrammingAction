package com.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-09-21
 */
public class A36_ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        System.out.println(isValidEachRow(board));
        System.out.println(isValidEachColumn(board));
        System.out.println(isValidEachSubBoxes(board));
        return isValidEachRow(board)&& isValidEachColumn(board)&& isValidEachSubBoxes(board);
    }

    private static boolean isValidEachRow(char[][] board){
        boolean isValid = true;
        for(int i = 0;i<board.length;i++){
            Set<Character> noRepeatRow = new HashSet<>();
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]!='.'){
                    if(noRepeatRow.contains(board[i][j])){
                        isValid = false;
                        break;
                    }else{
                        noRepeatRow.add(board[i][j]);
                    }
                }
            }

            if(!isValid){
                break;
            }
        }
        return isValid;
    }

    private static boolean isValidEachColumn(char[][] board){
        boolean isValidCol = true;
        for(int i = 0;i<board.length;i++){
            Set<Character> noRepeatCol = new HashSet<>();
            for(int j=0;j<board[i].length;j++){
                if(board[j][i]!='.'){
                    if(noRepeatCol.contains(board[j][i])){
                        isValidCol = false;
                        break;
                    }else{
                        noRepeatCol.add(board[j][i]);
                    }
                }
            }

            if(!isValidCol){
                break;
            }
        }
        return isValidCol;
    }

    private static boolean isValidEachSubBoxes(char[][] board){
        boolean isValid = true;
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++){
                if(!isValidSubBoxes(board,i*3,j*3)){
                    isValid = false;
                    break;
                }
            }
            if(!isValid){
                break;
            }
        }
        return isValid;
    }

    private static boolean isValidSubBoxes(char[][] board,int startX,int startY){
        boolean isValid = true;
        Set<Character> noRepeat = new HashSet<>();
        for(int i=startX;i<startX+3;i++){
            for(int j=startY;j<startY+3;j++){
                if(board[i][j]!='.'){
                    if(noRepeat.contains(board[i][j])){
                        isValid = false;
                        break;
                    }else{
                        noRepeat.add(board[i][j]);
                    }
                }
            }
            if(!isValid){
                break;
            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println(isValidSudoku(board));
    }
}
