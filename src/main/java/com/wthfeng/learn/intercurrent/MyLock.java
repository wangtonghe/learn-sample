package com.wthfeng.learn.intercurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author wangtonghe
 * @since 2019/2/22 11:13
 */
public class MyLock {

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {


        @Override
        protected boolean tryAcquire(int arg) {

            final Thread current = Thread.currentThread();
            int s = getState();
            if (s == 0) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()) {
                setState(s + arg);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean flag = false;
            int s = getState() - arg;
            if (s == 0) {
                flag = true;
                setExclusiveOwnerThread(null);
            }
            setState(s);
            return flag;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() != null;
        }
    }

    static class Runner implements Runnable {

        private volatile static int num;

        private final static MyLock myLock = new MyLock();


        @Override
        public void run() {


            try {
                for (int i = 0; i < 10000; i++) {
                    myLock.lock();
//                    synchronized (myLock) {
                    if (i % 100 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                    num++;
//                    }
                    myLock.unlock();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {


        Thread t1 = new Thread(new Runner(), "t1");
        Thread t2 = new Thread(new Runner(), "t2");
        Thread t3 = new Thread(new Runner(), "t3");
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Runner.num);

    }


}
