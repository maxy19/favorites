package com.mxy.module.lock.synch;

import lombok.SneakyThrows;

/**
 * 锁住代码块
 */
public class SynchronizeBlock {


    private Object synch1 = new Object();
    private Object synch2 = new Object();

    @SneakyThrows
    public static void main(String[] args) {
        SynchronizeBlock synchronizeBlock = new SynchronizeBlock();
        synchronizeBlock.sameObj();
        Thread.sleep(3000);
        synchronizeBlock.diffObj();
    }

    private void sameObj() {
        System.out.println("==正确的顺序==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                sendMsg(i, synch1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                receiveMsg(i, synch1);
            }
        }).start();
    }

    /**
     * 持有了不同全局的引用 所以无法互斥
     */
    private void diffObj() {
        System.out.println("==错误的顺序==");
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                sendMsg(i, synch1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                receiveMsg(i, synch2);
            }
        }).start();
    }

    @SneakyThrows
    public void sendMsg(int i, Object synch) {
        synchronized (synch) {
            Thread.sleep(100);
            System.out.println("发送第" + i + "条");
        }
    }

    @SneakyThrows
    public void receiveMsg(int i, Object synch) {
        synchronized (synch) {
            Thread.sleep(100);
            System.out.println("接受第" + i + "条");
        }
    }

}
