package com.mxy.module.lock.synch;

import lombok.SneakyThrows;

/**
 * 加载方法上，锁住当前实例对象的引用 与this 一样 相当于堆空间new出来一块区域 如果不同对象 则相当于两块区域 不相干的话就会锁不住
 * 所以必须相同实例引用才行
 */
public class SynchronizeMethod {


    @SneakyThrows
    public static void main(String[] args) {
             sameObj();
             Thread.sleep(3000);
             diffObj();
    }

    private static void sameObj() {
        System.out.println("==正确的顺序==");
        SynchronizeMethod synchronizeMethod = new SynchronizeMethod();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synchronizeMethod.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                synchronizeMethod.receiveMsg(i);
            }
        }).start();
    }

    /**
     * 与this 一样 锁住当前实例对象 （实例对象在堆）
     */
    private static void diffObj() {
        System.out.println("==错误的顺序==");

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeMethod synchronizeMethod = new SynchronizeMethod();
                synchronizeMethod.sendMsg(i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                SynchronizeMethod synchronizeMethod = new SynchronizeMethod();
                synchronizeMethod.receiveMsg(i);
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
