package com.mxy.module.io;

import java.io.IOException;
import java.net.Socket;

/**
 * @Author maxuyang
 **/
public class Main {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        socket.getOutputStream().write("maxuyang测试".getBytes());
    }

}
