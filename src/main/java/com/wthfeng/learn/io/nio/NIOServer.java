package com.wthfeng.learn.io.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/11/14 10:35
 */
public class NIOServer {


    public void start() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        Selector selector = Selector.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8080));
        // ①将服务器的channel注册到选择器
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                //阻塞，至少一个连接到来时才会继续
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    //  连接进入
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        // ② 服务器接受连接,创建客户端的channel，然后注册到选择器（Selector）
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        // ③ 客户端的channel
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int count = sc.read(byteBuffer);
                        if (count < 0) {
                            key.cancel();
                            sc.close();
                        } else {
                            byteBuffer.flip();  //切换到读模式
                            String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
                            System.out.println("received from: " + msg);
                            sc.write(ByteBuffer.wrap(msg.getBytes(CharsetUtil.UTF_8)));
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private void dispatch(SelectionKey key) throws IOException {
//        if (key.isAcceptable()) {  // 连接进入
//            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
//            SocketChannel socketChannel = serverSocketChannel.accept();  //服务器接受连接
//
//            socketChannel.configureBlocking(false);
//            socketChannel.register(selector, SelectionKey.OP_READ);
//        } else if (key.isReadable()) {
//            SocketChannel sc = (SocketChannel) key.channel();
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//
//            int count = sc.read(byteBuffer);
//            if (count < 0) {
//                key.cancel();
//                sc.close();
//            } else {
//                byteBuffer.flip();  //切换到读模式
//                String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
//                System.out.println("received from: " + msg);
//                sc.write(ByteBuffer.wrap(msg.getBytes(CharsetUtil.UTF_8)));
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.start();
    }
}
