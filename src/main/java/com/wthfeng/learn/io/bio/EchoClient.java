package com.wthfeng.learn.io.bio;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author wangtonghe
 * @since 2018/8/7 15:45
 */
public class EchoClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 8000);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("hello\n");
        Thread.sleep(1000);
        writer.write("EXIT\n");
        writer.flush();
        writer.close();
    }
}
