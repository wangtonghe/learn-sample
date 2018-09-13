package com.wthfeng.learn.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangtonghe
 * @since 2018/8/7 15:34
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {

        // 创建服务器端
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            // 在这阻塞，直到下一个请求到来
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String body = "";
            // 读取一行数据
            body = reader.readLine();
            System.out.println(body);
            reader.close();
        }
    }

}
