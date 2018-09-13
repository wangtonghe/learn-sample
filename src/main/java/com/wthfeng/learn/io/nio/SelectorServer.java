package com.wthfeng.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author wangtonghe
 * @since 2018/9/12 10:45
 */
public class SelectorServer {

    public static void main(String[] args) throws Exception {

        // 打开一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置监听地址
        serverSocketChannel.bind(new InetSocketAddress(8000));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //选择器
        Selector selector = Selector.open();
        // 服务器注册接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            // 阻塞,直到连接到来
            selector.select();
            // 就绪通道的集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey curKey = iterator.next();
                iterator.remove();
                if (curKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) curKey.channel();
                    // 与某个客户端已连接上
                    SocketChannel clientChannel = ssc.accept();
                    clientChannel.configureBlocking(false);
                    // 将该Channel注册到注册器上
                    clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                } else if (curKey.isReadable()) {
                    // 某一通道可读，
                    SocketChannel sc = (SocketChannel) curKey.channel();
                    byteBuffer.clear();
                    while (sc.read(byteBuffer) > 0) {
                        byteBuffer.flip();
                        String msg = Charset.forName("UTF-8").decode(byteBuffer).toString();
                        System.out.println("received from: " + msg);
                    }
                } else if (curKey.isWritable()) {
                    SocketChannel sc = (SocketChannel) curKey.channel();
                    String body = "<html><head>百度</head><body>hello baidu!</body></html>";
                    ByteBuffer buffer = ByteBuffer.wrap(body.getBytes());
                    sc.write(buffer);
                }
            }
        }


    }

}
