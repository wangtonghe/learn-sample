package com.wthfeng.learn.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/11/11 11:12
 */
public class NettyTest {

    @Test
    public void testByteBuf(){
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        for (int i=0;i<byteBuf.readableBytes();i++){
            System.out.println(i);
            System.out.println(byteBuf.getByte(i));
        }
//        byteBuf.discardReadBytes();
//        System.out.println(byteBuf.readableBytes());
        System.out.println(byteBuf.getBoolean(10));

        Unpooled.buffer();


    }
}
