package com.wthfeng.learn.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtonghe
 * @date 2017/12/19 11:59
 */
public class ConnectionPool {

    private final LinkedList<Connection> connections = new LinkedList<>();

    BasicDataSource source = new BasicDataSource();




    private final BlockingQueue<Connection> queue = new LinkedBlockingQueue<>();

    public ConnectionPool(int num){
        for (int i = 0; i < num; i++) {
            Connection connection = ConnectionUtil.getConnection();
            connections.add(connection);
            queue.add(connection);
        }
        String url = "jdbc:mysql://localhost:3306/people";
        String user = "root";
        String pwd = "wangtonghe";
        source.setUrl(url);
        source.setUsername(user);
        source.setPassword(pwd);
        source.setMaxTotal(20);
        source.setInitialSize(10);
    }


    //从池中获取资源
    public Connection getConnFromPool(long timeout) throws Exception {
        Connection conn = null;
        synchronized (connections) {  //锁住资源池
            long start = System.currentTimeMillis();
            long end = start + timeout;
            while (connections.isEmpty() && System.currentTimeMillis() < end) {
                connections.wait(timeout);  //等待超时时间
            }
            if (!connections.isEmpty()) {
                conn = connections.removeFirst();
            }
            return conn;
        }
    }


    //将资源放回池中
    public void release(Connection connection) {
        if (connection != null) {
            synchronized (connections) {  //锁住资源池
                connections.addLast(connection);  //资源回归资源池
                connections.notify();  //唤醒等待线程
            }
        }
    }


    public Connection getConnByBlockingQueue(int timeout)throws InterruptedException{

        return queue.poll(timeout, TimeUnit.MILLISECONDS);

    }

    public void returnConn2BlockingQueue(Connection connection){
        queue.offer(connection);
    }




    public Connection getConnFromDbcp() throws Exception{

        return source.getConnection();
    }


    public void releaseDbcp(Connection connection){


    }


}
