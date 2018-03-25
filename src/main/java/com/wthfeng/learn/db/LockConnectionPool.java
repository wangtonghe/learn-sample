package com.wthfeng.learn.db;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/12/20 09:03
 */
public class LockConnectionPool {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    private final LinkedList<Connection> pool = new LinkedList<>();

    public LockConnectionPool(int num){
        for (int i = 0; i <num ; i++) {
            pool.addLast(ConnectionUtil.getConnection());
        }
    }

    public Connection getConnection(int timeout)throws Exception{
        Connection connection = null;
        lock.lock();
        try {
            long start = System.currentTimeMillis();
            long end = start+timeout;
            while (pool.isEmpty()&&System.currentTimeMillis()<end){
                condition.await(timeout, TimeUnit.MILLISECONDS);
            }
            if(!pool.isEmpty()){
                connection = pool.removeFirst();
            }
        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void release(Connection connection){
        lock.lock();
        try {
            pool.addLast(connection);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
