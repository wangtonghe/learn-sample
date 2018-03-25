package com.wthfeng.learn.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangtonghe
 * @date 2017/11/7 18:34
 */
public class OioServer {

    public void server(int port) throws IOException{
        final ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            final Socket client = serverSocket.accept();
            System.out.println("accepted from:"+client.getChannel());
            new Thread(()->{
                try {
                  OutputStream out =  client.getOutputStream();
                  out.write("Hi, client\n".getBytes());
                  out.flush();
                  client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args)throws Exception {
        new OioServer().server(8010);

    }

}
