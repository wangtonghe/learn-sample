package com.wthfeng.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * @author wangtonghe
 * @since 2018/9/12 11:59
 */
public class SelectorClient extends Thread {

    private final static int THREAD_NUM = 1000;

    @Override
    public void run() {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(8000));
            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            String str = "hello,world! " + Thread.currentThread().getName();
            byteBuffer.put(str.getBytes());
            byteBuffer.flip();
            System.out.println(str);
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("执行开始 " + LocalDateTime.now());
        for (int i = 0; i < THREAD_NUM; i++) {
            SelectorClient selectorClient = new SelectorClient();
            selectorClient.start();
            try {
                selectorClient.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("执行结束 " + LocalDateTime.now());


    }
}
