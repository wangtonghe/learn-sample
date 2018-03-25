package com.wthfeng.learn.io.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * @author wangtonghe
 * @date 2017/5/30 15:35
 */
public class TestChannel {

    @Test
    public void test() throws Exception {
        Selector selector = Selector.open();
        RandomAccessFile file = new RandomAccessFile("test.json","rw");
        FileChannel channel = file.getChannel();
//        channel
//        SelectionKey key = channel







    }

}
