package com.wthfeng.learn.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/11/13 22:28
 */
public class SelectorTest {

    @Test
    public void test() throws IOException {
        Selector selector = Selector.open();

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(3000));


        SelectionKey key = channel.register(selector, SelectionKey.OP_ACCEPT);

        try {
            int ready = selector.select();
            if (ready > 0) {
                Set<SelectionKey> readySet = selector.selectedKeys();
                for (SelectionKey selectKey : readySet) {
                    if (selectKey.isReadable()) {
                        SocketChannel socket = (SocketChannel) selectKey.channel();
                        readySet.remove(selectKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        channel.close();


    }

    public void server()throws IOException{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(3000));
        serverChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = serverChannel.accept();
            if(serverChannel!=null){

            }
        }

    }
}
