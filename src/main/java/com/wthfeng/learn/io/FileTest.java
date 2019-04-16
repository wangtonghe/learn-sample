package com.wthfeng.learn.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangtonghe
 * @since 2019/3/8 10:03
 */
public class FileTest {

    public static class IOBusyTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    FileOutputStream outputStream = new FileOutputStream("data.txt", true);
                    for (int i = 65; i < 98; i++) {
                        outputStream.write(i);
                    }
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public static class LasyTask implements Runnable {

        @Override
        public void run() {


            while (true) {
                try {
                    int c;
                    FileInputStream inputStream = new FileInputStream("data2.txt");
                    while ((c = inputStream.read()) != -1) {
                        System.out.println((char) c);

                    }
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new IOBusyTask());

        Thread t2 = new Thread(new LasyTask());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
