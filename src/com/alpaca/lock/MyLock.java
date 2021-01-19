package com.alpaca.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MyLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public  void  lock(){
        Thread  thread = Thread.currentThread();
        System.out.println(thread.getName()+" -- locking ");


        while(!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+" -- unlocking ");
    }

    public static void main(String[] args) {


        MyLock myLock = new MyLock();



        new Thread(() ->{
            myLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unlock();
        },"Thread-A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() ->{
            myLock.lock();
            myLock.unlock();
        },"Thread-B").start();

    }
}
