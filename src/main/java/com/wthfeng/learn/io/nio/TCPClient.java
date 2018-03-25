package com.wthfeng.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wangtonghe
 * @date 2017/8/28 18:19
 */
public class TCPClient {

    public static void main(String[] args) throws Exception{
        String server = "localhost";
        int port = 8000;
        String str = "hello world !";
        byte[] buf = str.getBytes();
        int size = buf.length;

        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false); //非阻塞
        boolean isSuccess = channel.connect(new InetSocketAddress(server,port));
        if(!isSuccess){
            while (!channel.finishConnect()){
                System.out.println("正在连接...");
            }
        }
        ByteBuffer writebuf = ByteBuffer.wrap(buf);
        ByteBuffer readBuf = ByteBuffer.allocate(size);

        int total = 0;
        int revd = 0;

        while (total<size){
            if(writebuf.hasRemaining()){
                channel.write(writebuf);
            }
            if((revd=channel.read(readBuf))==-1){
                System.out.println("服务器关闭");
            }
            total+=revd;
            System.out.println(".");
        }
        System.out.println("received :"+new String(readBuf.array(),0,total));
        channel.close();
    }
}
