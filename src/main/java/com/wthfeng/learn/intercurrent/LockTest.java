package com.wthfeng.learn.intercurrent;

/**
 * @author wangtonghe
 * @since 2019/2/21 16:39
 */
public class LockTest {


    public static void main(String[] args) throws InterruptedException {
        FifoMutex mutex = new FifoMutex();
        MyThread a1 = new MyThread("a1", mutex);
        MyThread a2 = new MyThread("a2", mutex);
        MyThread a3 = new MyThread("a3", mutex);
        a1.start();
        a2.start();
        a3.start();

        a1.join();
        a2.join();
        a3.join();
        System.out.println(MyThread.count);
        System.out.print("Finished");
    }
}

class MyThread extends Thread {
    private String name;
    private FifoMutex mutex;
    public static int count;


    public MyThread(String name, FifoMutex mutex) {
        this.name = name;
        this.mutex = mutex;
    }


    @Override
    public void run() {
        mutex.lock();
        for (int i = 0; i < 1000; i++) {
            count++;
            System.out.println("name:" + name + "  count:" + count);

        }
        mutex.unlock();
    }

}
