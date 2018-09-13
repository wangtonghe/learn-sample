package com.wthfeng.learn.io.bio;

import org.junit.internal.runners.statements.RunAfters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单http服务器
 *
 * @author wangtonghe
 * @since 2018/9/12 18:45
 */
public class SimpleHttpServer {

    private final byte[] header;

    private final byte[] content;

    private final int port;

    private final String encoding;

    private volatile AtomicInteger handleNum = new AtomicInteger(0);

    private volatile AtomicInteger receiveNum = new AtomicInteger(0);


    public SimpleHttpServer(byte[] header, byte[] content, int port, String encoding) {
        this.header = header;
        this.content = content;
        this.port = port;
        this.encoding = encoding;
    }

    public void start() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            while (true) {
                try {
                    System.out.println("等待接收新连接...");
                    Socket socket = serverSocket.accept();
                    System.out.println("新连接到来...,接收连接数：" + receiveNum.incrementAndGet());
                    executorService.submit(new HttpHandler(socket));


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class HttpHandler implements Runnable {

        private Socket connection;

        HttpHandler(Socket socket) {
            this.connection = socket;
        }

        @Override
        public void run() {
            try {
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                InputStream in = new BufferedInputStream(connection.getInputStream());
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }
                if (request.toString().contains("HTTP")) {
                    out.write(header);
                }
                out.write(content);
                out.flush();
                System.out.println("处理连接数:" + handleNum.incrementAndGet());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        int port = 8080;

        String encoding = "UTF-8";

        try {
            Path path = Paths.get("/Users/wangtonghe/Desktop/baidu.html");
            byte[] data = Files.readAllBytes(path);
            String contentType = "text/html";
            String header = "HTTP/1.1 200 OK\n" +
                    "Server: OneFile 2.0\n" +
                    "Content-length: " + data.length + "\r\n" +
                    "Content-type: " + contentType + ",charset=" + encoding + "\r\n\r\n";
            SimpleHttpServer simpleHttpServer = new SimpleHttpServer(header.getBytes(), data, port, encoding);
            simpleHttpServer.start();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}

