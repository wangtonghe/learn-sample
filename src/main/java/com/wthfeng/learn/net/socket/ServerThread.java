package com.wthfeng.learn.net.socket;


import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author wangtonghe
 * @date 2017/8/25 16:29
 */
public class ServerThread implements Runnable {

    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintStream out = new PrintStream(client.getOutputStream());  //输出流，向客户端发送数据
            //接收客户端数据
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;

            while (flag) {
                String line = input.readLine();
                if (StringUtils.isEmpty(line)) {
                    break;
                }else {
                    if(line.equals("bye")) {
                        flag = false;
                    }else {
                        out.println("echo:"+line);
                    }
                }
            }
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
