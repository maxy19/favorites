package com.mxy.module.lock;

import lombok.SneakyThrows;

/**
 *  如果某个synchronized方法是static的，那么当线程访问该方法时，它锁的并不是synchronized方法所在的对象，
 *  而是synchronized方法所在的类所对应的Class对象。Java中，无论一个类有多少个对象，这些对象会对应唯一一个Class对象，
 *  因此当线程分别访问同一个类的两个对象的两个static，synchronized方法时，
 *  它们的执行顺序也是顺序的，也就是说一个线程先去执行方法，执行完毕后另一个线程才开始。
 *  class 级别最大
 */
public class SynchronizeStaticClass {

    @SneakyThrows
    public static void main(String[] args) {
        //例子1
        sameObj();
        Thread.sleep(7001);
        //例子2
        staticObj();
        Thread.sleep(7001);
        //例子3
        diffObj();
    }


    private static void staticObj() {
        System.out.println("==持有静态类==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeStaticClass.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeStaticClass.receiveMsg(i);
            }
        }).start();
        System.out.println();
    }


    private static void sameObj() {
        SynchronizeStaticClass synObj = new SynchronizeStaticClass();
        System.out.println("==持有对象锁==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synObj.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synObj.receiveMsg(i);
            }
        }).start();
        System.out.println();
    }

    private static void diffObj() {
        System.out.println("==持有不同对象锁==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeStaticClass synObj = new SynchronizeStaticClass();
                synObj.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeStaticClass synObj = new SynchronizeStaticClass();
                synObj.receiveMsg(i);
            }
        }).start();
        System.out.println();
    }

    @SneakyThrows
    public static synchronized void sendMsg(int i) {
        Thread.sleep(1000);
        System.out.println("发送第" + i + "条");
    }

    @SneakyThrows
    public static synchronized void receiveMsg(int i) {
        Thread.sleep(1000);
        System.out.println("接受第" + i + "条");
    }

}
