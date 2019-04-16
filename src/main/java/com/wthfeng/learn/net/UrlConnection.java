package com.wthfeng.learn.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author wangtonghe
 * @since 2019/3/18 15:47
 */
public class UrlConnection {

    public static void main(String[] args) throws Exception {

        String text = null;


        for (int i = 0; i < 100; i++) {

            URL url = new URL("https://blog.wthfeng.com/2018/09/17/2018-09-17-netty概要讲解/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((text = reader.readLine()) != null) {
                System.out.println(text);
            }
            System.out.println(i);
            reader.close();
        }


    }
}
