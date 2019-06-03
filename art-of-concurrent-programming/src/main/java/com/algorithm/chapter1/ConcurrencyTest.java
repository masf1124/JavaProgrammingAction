package com.algorithm.chapter1;

/**
 * @program: com.algorithm.chapter1
 * @author: mashifei
 * @create: 2019-05-30-15
 */
public class ConcurrencyTest {

    private static final long count = 100000001L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for(long i=0;i<count;i++){
                    a+=5;
                }
            }
        });
        thread.start();

        int b=0;
        for(int i=0;i<count;i++){
            b--;
        }
        thread.join();
        long end = System.currentTimeMillis()-start;
        System.out.println("concurrency:"+end);
    }



    private static void serial(){
        long start = System.currentTimeMillis();
        int a=0;
        for(long i=0;i<count;i++){
            a+=5;
        }
        int b=0;
        for(int i=0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-start;
        System.out.println("serial:"+time);
    }
}
