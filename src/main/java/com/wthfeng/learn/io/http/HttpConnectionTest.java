package com.wthfeng.learn.io.http;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author wangtonghe
 * @date 2017/9/25 09:50
 */
public class HttpConnectionTest {


    @Test
    public void test() {
        try {
            URL url = new URL("http://www.baidu.com");  //构建一个URL资源对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//打开连接
            connection.setRequestMethod("GET");  //设置请求方法

            /**
             * 建立连接并获取资源（指向百度首页的html内容）
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
