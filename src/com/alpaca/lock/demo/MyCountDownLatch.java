package com.alpaca.lock.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 *倒计时
 * */
public class MyCountDownLatch {
    public static final  int NUM = 4;

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 1; i <= NUM; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"-----走了");
                countDownLatch.countDown();
            },CountryEnum.foreach(i).getName()).start();
        }

        try {
            /* 等待其他线程完成操作 。*/
            /*countDownLatch 当里面的值 为0 时 才会不等待 */
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        System.out.println(Thread.currentThread().getName()+"关门走人 ！");
    }

}
