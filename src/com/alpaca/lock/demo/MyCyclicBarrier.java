package com.alpaca.lock.demo;

import java.util.concurrent.CyclicBarrier;

public class MyCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("-----召唤神龙-----");
        });
        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"------找到了 一个"+temp+"号龙珠");

                try {
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {

                }
            }).start();
        }
    }
}
