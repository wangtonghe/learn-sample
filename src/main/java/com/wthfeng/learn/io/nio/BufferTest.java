package com.wthfeng.learn.io.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wangtonghe
 * @since 2018/9/11 11:40
 */
public class BufferTest {

    @Test
    public void test1() {

        // 准备出10个大小的缓冲区
        IntBuffer buf = IntBuffer.allocate(10);
        System.out.print("1、写入数据之前的position、limit和capacity：");
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity());
        // 定义一个int数组
        int temp[] = {5, 7, 9};
        // 设置一个数据
        buf.put(3);
        // 此时已经存放了四个记录
        buf.put(temp);
        System.out.print("2、写入数据之后的position、limit和capacity：");
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity());
        //向缓冲去写入数据后，在读取缓冲区写入的数据之前，需要重设缓冲区
        buf.flip();
        // postion = 0 ,limit = 原本position
        System.out.print("3、准备输出数据时的position、limit和capacity：");
        System.out.println("position = " + buf.position() + "，limit = " + buf.limit() + "，capacty = " + buf.capacity());
        System.out.print("缓冲区中的内容：");
        while (buf.hasRemaining()) {
            int x = buf.get();
            System.out.print(x + "、");
        }
    }

    @Test
    public void testIntBuffer2() {
        // 准备出10个大小的缓冲区
        IntBuffer buf = IntBuffer.allocate(10);
        // 定义子缓冲区
        IntBuffer sub = null;
        for (int i = 0; i < 10; i++) {
            // 在主缓冲区中加入10个奇数
            buf.put(2 * i + 1);
        }
        //创建子缓冲区之前，需要先设置好position和limit
        // 需要通过slice() 创建子缓冲区
        buf.position(2);
        buf.limit(6);
        sub = buf.slice();
        for (int i = 0; i < sub.capacity(); i++) {
            int temp = sub.get(i);
            sub.put(temp - 1);
        }
        // 重设缓冲区
        buf.flip();
        buf.limit(buf.capacity());
        System.out.print("主缓冲区中的内容：");
        while (buf.hasRemaining()) {
            int x = buf.get();
            System.out.print(x + "、");
        }
    }

    /**
     * FileChannel 读取
     * @throws Exception
     */
    @Test
    public void testChannel() throws Exception {

        File file = new File("/Users/wangtonghe/local/tmp/hello.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        // 获取channel
        FileChannel fileChannel = fileInputStream.getChannel();
        // 分配Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(40);
        // 将文件内容读取出来
        fileChannel.read(byteBuffer);
        // 将channel设为可读状态
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }

    @Test
    public void testChannel2() throws Exception {
        File file = new File("/Users/wangtonghe/local/tmp/hello.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        String str = "asdfghjk\n";
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        byteBuffer.clear();
        fileOutputStream.close();
        fileChannel.close();
    }
}
