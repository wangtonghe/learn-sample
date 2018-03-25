package com.wthfeng.learn.io.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtonghe
 * @date 2017/11/14 11:58
 */
public class NIOClient {

    private Selector selector;

    private AtomicInteger sendMsg = new AtomicInteger(0);

    public void strat() throws IOException {

        selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(9999));
        socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        while (true) {
            try {
                selector.select();  //阻塞，直到服务器可用
                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> iterators = keys.iterator();
                while (iterators.hasNext()) {
                    SelectionKey key = iterators.next();
                    iterators.remove();
                    if (key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (sc.isConnectionPending()) {
                            sc.finishConnect();
                        }
                        String hello = "Hello NIO !";
                        ByteBuffer byteBuffer = ByteBuffer.wrap(hello.getBytes(CharsetUtil.UTF_8));
                        sc.write(byteBuffer);
                    } else if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(80);

                        sc.read(byteBuffer); //从流中读取数据

                        byteBuffer.flip();
                        String msg = CharsetUtil.UTF_8.decode(byteBuffer).toString();
                        System.out.println("从服务器接受消息为："+msg);

                        Thread.sleep(100);
                        int count = sendMsg.getAndIncrement();
                        if(count>10){
                            byteBuffer.clear();
                            sc.close();
                            return;
                        }
                        String message = "send to "+String.valueOf(count);
                        sc.write(ByteBuffer.wrap((message.getBytes())));
                        byteBuffer.clear();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.strat();

    }
}
