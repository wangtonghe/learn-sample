package com.wthfeng.learn.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @author wangtonghe
 * @date 2017/11/12 22:25
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final ChannelGroup channels;

    public WebSocketHandler(ChannelGroup channels) {
        this.channels = channels;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        channels.writeAndFlush(msg.retain());

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
       if(evt.equals(WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE)){
           ctx.pipeline().remove(RequestHandler.class);
           channels.writeAndFlush(new TextWebSocketFrame("client"+ctx.channel()+" joined！"));
           channels.add(ctx.channel());
       }else{
           super.userEventTriggered(ctx,evt);
       }

    }
}
