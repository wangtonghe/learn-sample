package com.wthfeng.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author wangtonghe
 * @date 2017/8/29 12:44
 */
public class TCPServer {


    private static final int BUF_SIZE = 256;

    private static  final  int TIME_OUT = 3000;

    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();

        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        //将该信道绑定到指定端口
        serverChannel.socket().bind(new InetSocketAddress(8000));

        serverChannel.configureBlocking(false);

        //将选择器注册到信道
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        TcpProtocol tcpProtocol = new EchoTcpProtocol(BUF_SIZE);

        while (true){
            if(selector.select(TIME_OUT)==0){
                //做一些其他操作，表示异步操作
                System.out.println("正在等待...");
                continue;
            }
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()){
                SelectionKey key = keyIter.next();
                if(key.isAcceptable()){
                    tcpProtocol.handleAccept(key);
                }
                if(key.isReadable()){
                    tcpProtocol.handleRead(key);
                }
                if(key.isValid()&&key.isWritable()){
                    tcpProtocol.handleWrite(key);
                }
//                key.c
                keyIter.remove();
            }
        }
    }
}
