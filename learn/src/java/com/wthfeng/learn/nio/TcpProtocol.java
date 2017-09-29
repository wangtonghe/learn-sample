package com.wthfeng.learn.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author wangtonghe
 * @date 2017/8/29 15:16
 */
public interface TcpProtocol {

    /**
     * 接受客户端请求
     * @param key
     * @throws IOException
     */
    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;

}
