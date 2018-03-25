package com.wthfeng.learn.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangtonghe
 * @date 2017/8/25 16:34
 */
public class Server1 {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(20000);
        Socket socket = null;
        boolean flag = true;
        while (flag){
            socket = serverSocket.accept();
            System.out.println("服务器连接成功！");
            new Thread(new ServerThread(socket)).start();

        }

    }



}
