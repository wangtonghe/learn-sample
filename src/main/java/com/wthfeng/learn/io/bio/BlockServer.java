package com.wthfeng.learn.io.bio;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangtonghe
 * @since 2018/9/12 12:29
 */
public class BlockServer {

    public static void main(String[] args) throws Exception {

        // 创建服务器端
        ServerSocket server = new ServerSocket(8000);
        while (true) {
            // 在这阻塞，直到下一个请求到来
            try (Socket socket = server.accept()) {
                // 没有分线程处理，即请求串行执行，当前请求必须处理完毕才能处理下一个，
                // 若某个请求处理很慢，将直接影响后续所有请求
                Reader reader = new InputStreamReader(socket.getInputStream());
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
