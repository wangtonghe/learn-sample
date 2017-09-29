package com.wthfeng.learn.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/6/29 08:19
 */
public class TestAddress {

    @Test
    public void test(){
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            InetAddress[] arr = InetAddress.getAllByName("www.baidu.com");
            Arrays.stream(arr).forEach(System.out::println);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
