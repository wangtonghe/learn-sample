package com.wthfeng.learn.net.netty;

import java.io.IOException;
import java.net.Socket;

/**
 * @author wangtonghe
 * @date 2017/11/9 17:25
 */
public class OioClient {

    public void client(String host, int port) {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 8010);
            socket.setSoTimeout(3 * 1000);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
