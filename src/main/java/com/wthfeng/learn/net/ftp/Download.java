package com.wthfeng.learn.net.ftp;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author wangtonghe
 * @date 2017/9/24 21:50
 */
public class Download {

    public static void main(String[] args) {

        try {
            URL url = new URL("ftp://ygdy8:ygdy8@yg45.dydytt.net:6022/[阳光电影www.ygdy8.com].闪光少女.HD.720p.国语中字.mkv");
            URLConnection connection =  url.openConnection();
            connection.setConnectTimeout(10*60*1000);
            InputStream inputStream = connection.getInputStream();
            byte[] bs = new byte[1024];
            File file =  new File("/Users/wangtonghe/Desktop/test.mp4");
            FileOutputStream outputStream = new FileOutputStream(file);
            int len = 0;
            long total = inputStream.available();
            long readed = 0;
            System.out.println(total);
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
                readed+=len;
                System.out.println((readed/total)+"%");
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
