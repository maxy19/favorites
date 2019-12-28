package com.mxy.module.lock;

import lombok.SneakyThrows;

/**
 * 锁住This块
 */
public class SynchronizeThisBlock {

    static SynchronizeThisBlock synch1 = new SynchronizeThisBlock();
    static SynchronizeThisBlock synch2 = new SynchronizeThisBlock();

    @SneakyThrows
    public static void main(String[] args) {
        sameObj();
        Thread.sleep(3000);
        diffObj();
    }

    private static void sameObj() {
        System.out.println("==正确的顺序==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synch1.sendMsg(i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synch1.receiveMsg(i);
            }
        }).start();
    }

    /**
     * 锁住当前调用该方法对象的引用，不同的引用无法互斥
     */
    private static void diffObj() {
        System.out.println("==错误的顺序==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synch1.sendMsg(i);
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synch2.receiveMsg(i);
            }
        }).start();
    }

    @SneakyThrows
    public void sendMsg(int i) {
        synchronized (this) {
            Thread.sleep(100);
            System.out.println("发送第" + i + "条");
        }
    }

    @SneakyThrows
    public void receiveMsg(int i) {
        synchronized (this) {
            Thread.sleep(100);
            System.out.println("接受第" + i + "条");
        }
    }

}
