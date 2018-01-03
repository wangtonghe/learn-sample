package com.wthfeng.learn.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;

/**
 * @author wangtonghe
 * @date 2017/11/19 11:51
 */
public class TimeClient {

    public void client() throws Exception{
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeDecoder(),new TimeClientHandler());
                        }
                    });
            ChannelFuture future =  bootstrap.connect("localhost",9999).sync();
            future.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();

        }

    }

    public static void main(String[] args) throws Exception{

        UserInfo userInfo = new UserInfo();
        userInfo.setName("wangtonghe");
        userInfo.setEmail("wthfeng@126.com");
        userInfo.setLocalDateTime(LocalDateTime.now());
        TimeClient client = new TimeClient();
        client.client();
    }

}
