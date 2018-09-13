package com.wthfeng.learn.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 文件输入输出流测试
 *
 * @author wangtonghe
 * @since 2018/9/9 13:01
 */
public class FileStreamTest {

    /**
     * 输入流测试
     * 将磁盘中的文件读取到内存中，以字节的形式
     * 需保证要读取的文件一定存在
     */
    @Test
    public void testInputStream() throws Exception {
        InputStream inputStream = null;
        try {
            File file = new File("/Users/wangtonghe/local/tmp/hello.txt");
            inputStream = new FileInputStream(file);
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                System.out.println(b);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    @Test
    public void testFile() throws Exception {

        File aFile = new File("/Users/wangtonghe/local/tmp/a.txt");
        File bFile = new File("/Users/wangtonghe/local/tmp/b.txt");

        FileReader reader = null;
        FileWriter writer = null;

        try {
            reader = new FileReader(aFile);
            writer = new FileWriter(bFile);
            char[] chars = new char[1024];
            int len = 0;
            if ((len = reader.read(chars)) != -1) {
                writer.write(chars, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }


    @Test
    public void testStream() throws Exception {
        File aFile = new File("/Users/wangtonghe/local/tmp/a.txt");
        FileInputStream fileInputStream = new FileInputStream(aFile);
        Reader reader = new InputStreamReader(fileInputStream);
        int c;
        while ((c = reader.read()) != -1) {
            System.out.println((char) c);
        }
        reader.close();

    }

    @Test
    public void testTryResource() {

        File file = new File("/Users/wangtonghe/local/tmp/hello.txt");
        try (InputStream inputStream = new FileInputStream(file)) {
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
