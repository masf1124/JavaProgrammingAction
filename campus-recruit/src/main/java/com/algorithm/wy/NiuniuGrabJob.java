package com.algorithm.wy;

import java.util.*;

/**
 * Created by seu627 on 2019/6/26
 * 每个输入包含一个测试用例。
 * 每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
 * 接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
 * 接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
 * 保证不存在两项工作的报酬相同。
 */
public class NiuniuGrabJob {

    private static class Job{
        public int jobLevel;
        public int jobPay;

        public Job(int jobLevel, int jobPay) {
            this.jobLevel = jobLevel;
            this.jobPay = jobPay;
        }
    }

    private static class MyComparator implements Comparator<Job>{

        @Override
        public int compare(Job o1, Job o2) {
            if(o1.jobLevel>o2.jobLevel){
                return 1;
            }else if(o1.jobLevel<o2.jobLevel){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public static int[] getMaxPayAllPerson(Job[] jobs,int[] fellowsCapcity){
        int fellowCount = fellowsCapcity.length;
        int[] fellowPay = new int[fellowCount];
        for(int i=0;i<fellowCount;i++){
            fellowPay[i] = getMaxPayOnePerson(jobs,fellowsCapcity[i]);
        }

        return fellowPay;
    }

    public static int getMaxPayOnePerson(Job[] jobs,int fellowCapcity){
        Arrays.sort(jobs,new MyComparator());
        List<Integer> jobLevelsLessThanFellowCapOfPay = new ArrayList<>();
        for(int i=0;i<jobs.length;i++){
            if(jobs[i].jobLevel<=fellowCapcity){
                jobLevelsLessThanFellowCapOfPay.add(jobs[i].jobPay);
            }
        }

        jobLevelsLessThanFellowCapOfPay.sort(null);

        int len = jobLevelsLessThanFellowCapOfPay.size();
        int result = jobLevelsLessThanFellowCapOfPay.get(len-1);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String[] nums = sc.nextLine().split(" ");
            int jobCount = Integer.valueOf(nums[0]);

            Job[] jobs = new Job[jobCount];
            for(int i = 0;i < jobCount;i++){
                String[] jobProps = sc.nextLine().split(" ");
                jobs[i] = new Job(Integer.valueOf(jobProps[0]),Integer.valueOf(jobProps[1]));
            }

            String[] fellowsCapcityStrs = sc.nextLine().split(" ");
            int fellowCount = fellowsCapcityStrs.length;
            int[] fellowCpacity = new int[fellowCount];
            for(int j=0;j<fellowCount;j++){
                fellowCpacity[j] = Integer.valueOf(fellowsCapcityStrs[j]);
            }

            int[] fellowPay = getMaxPayAllPerson(jobs,fellowCpacity);
            for(int i=0;i<fellowCount;i++){
                System.out.println(fellowPay[i]);
            }
        }
    }
}
