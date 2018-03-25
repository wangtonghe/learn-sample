package com.wthfeng.learn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author wangtonghe
 * @date 2017/8/29 15:18
 */
public class EchoTcpProtocol implements TcpProtocol {

    private int bufSize;

    public EchoTcpProtocol(int bufSize) {
        this.bufSize = bufSize;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
        channel.configureBlocking(false);
        channel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {

        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        long byteRead = channel.read(buf);

        if(byteRead==-1){
            channel.close();
        }else if(byteRead>0){
            key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {

        ByteBuffer buf = (ByteBuffer)key.attachment();

        buf.flip();

        SocketChannel channel = (SocketChannel)key.channel();

        channel.write(buf);
        if(!buf.hasRemaining()){
            key.interestOps(SelectionKey.OP_READ);
        }
        buf.compact();
    }
}
