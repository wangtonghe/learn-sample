package com.wthfeng.learn.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author wangtonghe
 * @since 2018/11/17 14:54
 */
public class TestToken {

    public static void main(String[] args) {
        long time = System.currentTimeMillis() / 1000;
        String appId = "shyueke";
        String appKey = "e2f8ac4c67fb43b8b8f758a4674b8a9d";
        String token = appId + "|" + time + "|" + DigestUtils.sha1Hex(appKey + "|" + time);
        System.out.println(token);
    }

}
