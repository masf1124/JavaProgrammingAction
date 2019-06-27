package com.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @program: com.algorithm
 * @author: mashifei
 * @create: 2019-06-15-21
 */
public class OrderByScore {


    static class User implements Comparable {
        private String username;
        private Integer score;

        public User(String username, Integer score) {
            this.username = username;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof User){
                User u = (User)o;
                if(this.score>u.score){
                    return 1;
                }else if(this.score<u.score){
                    return -1;
                }else{
                    return 0;
                }
            }else{
                throw new RuntimeException("比较对象不是User的实例");
            }
        }
    }

    static class MyComparatorAsc implements Comparator<User>{

        @Override
        public int compare(User o1, User o2) {
            if(o1.score>o2.score){
                return 1;
            }else if(o1.score<o2.score){
                return -1;
            }else{
                return 0;
            }
        }
    }

    static class MyComparatorDesc implements Comparator<User>{

        @Override
        public int compare(User o1, User o2) {
            if(o1.score>o2.score){
                return -1;
            }else if(o1.score<o2.score){
                return 1;
            }else{
                return 0;
            }
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int totalCount = Integer.valueOf(sc.nextLine());
            int asc = Integer.valueOf(sc.nextLine());
            User[] users = new User[totalCount];
            for(int i=0;i<totalCount;i++){
                String[] userStr = sc.nextLine().split(" ");
                users[i] = new User(userStr[0],Integer.valueOf(userStr[1]));
            }

            if(asc==0){
                Arrays.sort(users,new MyComparatorDesc());
            }else{
                Arrays.sort(users,new MyComparatorAsc());
            }


            for(int j=0;j<totalCount;j++){
                System.out.println(users[j].username+" "+users[j].score);
            }
        }
    }
}
