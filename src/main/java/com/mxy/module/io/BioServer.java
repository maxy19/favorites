package com.mxy.module.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author maxuyang
 **/
public class BioServer {

    public static void main(String[] args) throws Exception {
        try {
            byte[] bt = new byte[1024];
            ServerSocket serverSocket = new ServerSocket(8080);
            //等待连接:阻塞状态
            System.out.println("server start...");
            Socket accept = serverSocket.accept();
            System.out.println("connect success");
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    //等待返回通知：阻塞状态
                    int read = 0;
                    try {
                        read = accept.getInputStream().read(bt);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (read != -1) {
                        System.out.println("result:" + new String(bt));
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
