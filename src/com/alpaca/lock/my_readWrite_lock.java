package com.alpaca.lock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
* 小缓存
* */
class MyCache{

    /*
    有 问题 的

   public void put (String key,String value) {
        System.out.println(Thread.currentThread().getName()+"--开始添加:" +key );
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"--添加完成:" +key );
    }

    public void get (String key) {

        System.out.println(Thread.currentThread().getName()+"--开始读:" +key );
        Object result  = map.get(key);
        System.out.println(Thread.currentThread().getName()+"--读完成:" +result );

    }

    */

    public volatile Map<String,String> map = new HashMap<>();


    /*读写锁*/
    private ReentrantReadWriteLock rrwlock = new ReentrantReadWriteLock();


    public void put (String key,String value) {
        /*写锁*/
        rrwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"--开始添加:" +key );
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"--添加完成:" +key );

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rrwlock.writeLock().unlock();
        }
    }

    public void get (String key) {

        /*读锁*/
        rrwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"--开始读:" +key );
            Object result  = map.get(key);
            System.out.println(Thread.currentThread().getName()+"--读完成:" +result );

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rrwlock.readLock().unlock();
        }

    }


}
/**
 *
 * 读写锁测试
 * */
public class my_readWrite_lock {


    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int  item  = i;
            new Thread(() ->{
                myCache.put(item+"",item+"");

            },i+"").start();
        }


        for (int i = 0; i < 5; i++) {
            final int  item  = i;
            new Thread(() ->{
                myCache.get(item+"");

            },i+"").start();
        }
    }

}
