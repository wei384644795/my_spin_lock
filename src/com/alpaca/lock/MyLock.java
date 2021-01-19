package com.alpaca.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 模拟 自旋锁
 */
public class MyLock {

    /**
     * 原子引用对象   原子引用线程
     * <p>
     * 使用 原子引用对象的 CAS 方法  判断 Thread  是否 上锁 。
     *
     */
//    空参构造函数  里面的值是null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 上锁
     */
    public void lock() {

        // 获得 当前 线程
        Thread thread = Thread.currentThread();
        // 输出 验证
        System.out.println(thread.getName() + " -- locking ");
//        自旋 判断 是否上锁  实际值 和 预期值 为 null  就 说明 还未上锁 那就上锁
//        如果 不为null  那就说明 已经上锁  循环等待  直到  null 时 才 结束 并上锁。
        while (!atomicReference.compareAndSet(null, thread)) {

        }

    }

    /**
     * 释放锁
     */
    public void unlock() {
        /*获取当前线程 */
        Thread thread = Thread.currentThread();
        /*
            释放锁
        *  把 atomicReference 中 thread 替换为 null.。
        * */
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + " -- unlocking ");
    }

    public static void main(String[] args) {


        MyLock myLock = new MyLock();

        /*开启一个线程  上锁 等待一下 再 释放锁*/
        new Thread(() -> {
            myLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unlock();
        }, "Thread-A").start();

        /*保证上一个线程先上锁 */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*再开启一个线程  上锁 再 释放锁*/
        new Thread(() -> {
            myLock.lock();
            myLock.unlock();
        }, "Thread-B").start();

    }
}
