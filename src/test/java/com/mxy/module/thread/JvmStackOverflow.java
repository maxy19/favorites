package com.mxy.module.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 讨论：多个子线程运行，其中一个线程StackOverFlow 其他线程是否停止
 * 答案：是会继续运行。（StackOverFlow情况下） 启动参数 -Xms10m -Xmx20m
 */
public class JvmStackOverflow {

    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]> list = new ArrayList();
            while (true) {
                System.out.println(Thread.currentThread().getName() + "==running.");
                invoke(1000L);
                byte[] b = new byte[1024 * 1024 * 1];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "==running.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void invoke(long param){
        invoke(param);
    }
}