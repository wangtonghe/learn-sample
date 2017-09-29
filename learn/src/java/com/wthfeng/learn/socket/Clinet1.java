package com.wthfeng.learn.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author wangtonghe
 * @date 2017/8/25 15:47
 */
public class Clinet1 {
    public static void main(String[] args) throws Exception{
        Socket clinet1 = new Socket("127.0.0.1",20000);
        clinet1.setSoTimeout(10000);

        BufferedReader keyin  = new BufferedReader(new InputStreamReader(System.in));

        PrintStream output = new PrintStream(clinet1.getOutputStream());   //输出流,发送给服务器

        BufferedReader input = new BufferedReader(new InputStreamReader(clinet1.getInputStream()));  //输入流，从服务器接收消息

        boolean flag = true;

        while (flag){
            System.out.println("输入信息:");
            String str = keyin.readLine();
            output.println(str);
            if("bye".equals(str)){
                flag = false;
            }else{
                String echo = input.readLine();
                System.out.println(echo);
            }
        }
        input.close();
        clinet1.close();
    }
}
