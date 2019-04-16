package com.wthfeng.learn.io.bio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author wangtonghe
 * @since 2019/2/26 10:11
 */
public class FileTest {

    public static void main(String[] args) {

        File file = new File("/Users/wangtonghe/Desktop/java基础知识.md");
        File newFile = new File("/Users/wangtonghe/Desktop/java2.md");
        FileReader fd = null;
        FileWriter fw = null;

        try {
            char[] bytes = new char[1024];
            fd = new FileReader(file);

            fw = new FileWriter(newFile);
            int len = 0;
            while ((len = fd.read(bytes, 0, bytes.length)) != -1) {
                fw.write(bytes,0,len);
            }

        } catch (IOException ex) {


        } finally {
            if (fd != null) {
                try {
                    fd.close();
                } catch (IOException e) {

                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {

                }
            }

        }


    }
}
