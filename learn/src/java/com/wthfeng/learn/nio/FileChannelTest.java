package com.wthfeng.learn.nio;

import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wangtonghe
 * @date 2017/11/13 21:25
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception {
        File file = new File("/Users/wangtonghe/Desktop/stream.md");
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(80);

        RandomAccessFile outFile = new RandomAccessFile(new File("/Users/wangtonghe/Desktop/stream3.md"), "rw");
        FileChannel outChannel = outFile.getChannel();

        inChannel.transferTo(0,inChannel.size(),outChannel);

        inChannel.close();
        outChannel.close();




    }
}
