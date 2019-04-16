package com.wthfeng.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * NIO简单服务器
 *
 * @author wangtonghe
 * @since 2018/9/13 14:47
 */
public class NioHttpServer {


    public static void main(String[] args) throws Exception {
        int port = 8080;

        String encoding = "UTF-8";

        AtomicInteger totalNum = new AtomicInteger(0);

        Path path = Paths.get("/Users/wangtonghe/local/tmp/baidu.html");
        byte[] data = Files.readAllBytes(path);
        String contentType = "text/html";
        // http的响应header
        String header =
                // 状态行
                "HTTP/1.1 200 OK\n" +
                        "Server: OneFile 2.0\n" +
                        "Content-length: " + data.length + "\r\n" +
                        "Content-type: " + contentType + ",charset=" + encoding
                        // 空行
                        + "\r\n\r\n";
        // 创建 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定到本地端口
        serverSocketChannel.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        // 注册事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer readBuf = ByteBuffer.allocate(50);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 删除，避免处理多次
                iterator.remove();
                //接收通道已准备好（即服务端有数据过来）
                if (selectionKey.isAcceptable()) {
                    System.out.println("接受请求数：" + totalNum.incrementAndGet());
                    // 获取关联的ServerSocketChannel（因为是服务器channel）
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    // 这里是不阻塞的，获取客户端的
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);

                    // 客户端有可读事件
                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    readBuf.clear();
                    StringBuilder strBuilder = new StringBuilder();
                    while (socketChannel.read(readBuf) > 0) {
                        readBuf.flip();
                        while (readBuf.hasRemaining()) {
                            strBuilder.append((char) readBuf.get());
                        }
                        readBuf.clear();
                    }
                    System.out.println(strBuilder.toString());
                } else if (selectionKey.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer dataBuf = ByteBuffer.wrap(data);
                    ByteBuffer headerBuf = ByteBuffer.wrap(header.getBytes());
                    //
                    dataBuf.rewind();
                    headerBuf.rewind();
                    // 向客户端写http的header
                    socketChannel.write(headerBuf);
                    // 向客户端写http的响应正文
                    socketChannel.write(dataBuf);
                    // 处理完后关闭连接
                    socketChannel.close();
                }

            }
        }
    }
}
