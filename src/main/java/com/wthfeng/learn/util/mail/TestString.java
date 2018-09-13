package com.wthfeng.learn.util.mail;

import org.junit.Test;

import java.net.InetAddress;

/**
 * @author wangtonghe
 * @since 2018/6/3 12:20
 */
public class TestString {

    @Test
    public void testStr() {
        String str = "";
        if (str != "") {
            System.out.println(str + "已在线程池中");
        }
        System.out.println(str);

    }

    @Test
    public void testIp() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        //获得本机名称;
        String host = addr.getHostName();
        System.out.println(host);
    }
}
