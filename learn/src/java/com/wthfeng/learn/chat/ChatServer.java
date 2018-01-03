package com.wthfeng.learn.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * @author wangtonghe
 * @date 2017/11/12 17:56
 */
public class ChatServer {

    private EventLoopGroup loopGroup = new NioEventLoopGroup();

    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    public ChannelFuture start(InetSocketAddress address) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(loopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new HttpServerCodec())
                                .addLast(new ChunkedWriteHandler())
                                .addLast(new HttpObjectAggregator(64 * 1024))
                                .addLast(new RequestHandler())
                                .addLast(new WebSocketServerProtocolHandler("/ws"))
                                .addLast(new WebSocketHandler(channelGroup));

                    }
                });
        ChannelFuture future = serverBootstrap.bind(address);
        future.syncUninterruptibly();
        return future;
    }

    public void destry(){
        loopGroup.shutdownGracefully();

    }

    public static void main(String[] args) {
        final ChatServer chatServer = new ChatServer();
        ChannelFuture future = chatServer.start(new InetSocketAddress(9000));
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            Channel chan = future.channel();
            if(chan!=null){
                chan.close();
            }
            chatServer.destry();
        }));
        future.channel().closeFuture().syncUninterruptibly();

    }
}
