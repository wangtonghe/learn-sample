package com.wthfeng.learn.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author wangtonghe
 * @date 2017/5/31 12:29
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8081;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("port:"+port);
            Socket socket = null;
            while (true){
                socket = server.accept();
                new Thread(()->{
                    BufferedReader in = null;
                    PrintWriter out  = null;


                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server!=null){
                System.out.println("server is closed");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }

    }

    class TimeServerHandler implements Runnable{

        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(),true);
                String body = null;
                while(true){
                    body = reader.readLine();
                    if(body==null){
                        break;
                    }
                    if("QUERY TIME".equals(body)){
                        writer.println(new Date());
                    }else{
                        writer.println("not ");
                    }

                }

            } catch (IOException e) {
               if(reader!=null){
                   try {
                       reader.close();
                   } catch (IOException e1) {
                       e1.printStackTrace();
                   }

               }

                e.printStackTrace();
            }


        }
    }
}









