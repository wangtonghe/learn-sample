package com.wthfeng.learn.net.chat;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 处理请求
 *
 * @author wangtonghe
 * @date 2017/11/12 17:58
 */
public class RequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    private static File file;

    static {
        URL location = RequestHandler.class.getProtectionDomain().getCodeSource().getLocation();

        try {
            String path = location.toURI() + "index.html";

            path = path.contains("file:") ? path.substring(5) : path;
            file = new File(path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        if (msg.uri().equals("/ws")) {
            ctx.fireChannelRead(msg.retain());
            return;
        }
//        msg.
        RandomAccessFile index = new RandomAccessFile(file, "r");
        HttpResponse response = new DefaultHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        boolean isAlive = HttpUtil.isKeepAlive(response);
        if (isAlive) {
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, index.length());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        ctx.write(response); //将响应头写入
        ctx.write(new DefaultFileRegion(index.getChannel(), 0, index.length())); //写入内容
        ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!isAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
