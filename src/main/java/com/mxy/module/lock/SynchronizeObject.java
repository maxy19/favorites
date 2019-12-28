package com.mxy.module.lock;

import lombok.SneakyThrows;

/**
 *  相同对象才可以互斥
 */
public class SynchronizeObject {


    @SneakyThrows
    public static void main(String[] args) {
        //错误的例子
        diffObj();
        Thread.sleep(3000);
        System.out.println("-----------");
        //正确的例子
        sameObj();
    }

    /**
     * 持有同一把锁 当前类锁
     */
    private static void sameObj() {
        System.out.println("==正确的顺序==");
        SynchronizeObject synObj = new SynchronizeObject();
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
    }

    /**
     * 持有不同的锁 会导致乱序
     */
    private static void diffObj() {
        System.out.println("==错误的顺序==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeObject synObj = new SynchronizeObject();
                synObj.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeObject synObj = new SynchronizeObject();
                synObj.receiveMsg(i);
            }
        }).start();
    }

    @SneakyThrows
    public synchronized void sendMsg(int i) {
        Thread.sleep(100);
        System.out.println("发送第" + i + "条");
    }

    @SneakyThrows
    public synchronized void receiveMsg(int i) {
        Thread.sleep(100);
        System.out.println("接受第" + i + "条");
    }

}
