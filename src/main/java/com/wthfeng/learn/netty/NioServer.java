package com.wthfeng.learn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/11/9 18:12
 */
public class NioServer  {

    public void server(int port)throws IOException{

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();

        InetSocketAddress address = new InetSocketAddress(port);

        serverSocket.bind(address);

        Selector selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi Client".getBytes());


        for(;;){
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            for (SelectionKey readyKey : readyKeys) {
                if(readyKey.isAcceptable()){
                    ServerSocketChannel server =(ServerSocketChannel) readyKey.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,msg.duplicate());
                    System.out.println("accept from "+client);

                    if(readyKey.isWritable()){
                        SocketChannel channel =(SocketChannel) readyKey.channel();
                        ByteBuffer byteBuffer =(ByteBuffer) readyKey.attachment();
                        while (byteBuffer.hasRemaining()){
                            if(channel.write(byteBuffer)==0){
                                break;
                            }
                        }
                        channel.close();
                    }
                }
            }
        }
    }

    public static void main(String[] args)throws Exception {
        new NioServer().server(8010);
    }
}
