package com.algorithm.floyd;

import java.io.IOException;
import java.util.Scanner;

/**
 * @program: com.algorithm.floyd
 * @author: mashifei
 * @create: 2019-05-30-18
 * java: Floyd算法获取最短路径（邻接矩阵）
 */
public class MatrixUDG {

    private int mEgdeNum;//边的数量
    private char[] mVexs;//顶点的集合
    private int[][] mMatrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;


    /**
     * 创建图，（以自己输入数据的方式）
     */
    public MatrixUDG(){
        //输入顶点树和边数
        System.out.println("input vertex number: ");
        int vlen = readInt();
        System.out.println("input edge number: ");
        int elen = readInt();

        if(vlen<1 || elen<1 ||(elen>(vlen*(vlen-1)))){
            System.out.println("input error: invalid paramters");
            return ;
        }

        //初始化顶点
        mVexs = new char[vlen];
        for(int i=0;i<mVexs.length;i++){
            System.out.printf(String.format("vertex(%d)",i));
            mVexs[i] = readChar();
        }


    }

    /**
     * 返回ch的index
     * @param ch
     * @return
     */
    private int getPosition(char ch){
        for(int i=0;i<mVexs.length;i++){
            if(mVexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 读取一个字符串
     * @return
     */
    private char readChar(){
        char ch = '0';

        do{
            try{
                ch = (char)System.in.read();
            }catch(IOException e){
                e.printStackTrace();
            }
        }while(!((ch>='a' && ch<='z')||(ch>='A'&&ch<='Z')));
        return ch;
    }

    /**
     * 读取一个整型数
     * @return
     */
    private int readInt(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }


    /**
     * 返回顶点v的第一个邻接点的索引，失败则返回-1
     * @param v
     * @return
     */
    private int firstVertex(int v){
        if(v<0 || v>mVexs.length-1){
            return -1;
        }
        for(int i=0;i<mVexs.length;i++){
            if(mMatrix[v][i]!=0 && mMatrix[v][i]!=INF){
                return i;
            }
        }

        return -1;
    }

    /**
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     * @param v
     * @param w
     * @return
     */
    private int nextVertex(int v,int w){
        if(v<0 || v>mVexs.length-1 || w<0||w>mVexs.length-1){
            return -1;
        }

        for(int i=w+1;i<mVexs.length;i++){
            if(mMatrix[v][i]!=0 && mMatrix[v][i]!=INF){
                return i;
            }
        }
        return -1;
    }


}
