package com.wthfeng.learn.intercurrent.thread;

/**
 * @author wangtonghe
 * @since 2019/3/7 10:03
 */
public class HoldLockMain {

    private final static Object[] LOCK = new Object[10];

    static {
        for (int i = 0; i < 10; i++) {
            LOCK[i] = new Object();

        }
    }

    static class HoldTask implements Runnable {

        private int i;

        public HoldTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {


            while (true) {

                synchronized (LOCK[i]) {
                    if (i % 2 == 0) {
                        try {
                            LOCK[i].wait(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        LOCK[i].notifyAll();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            new Thread(new HoldTask(i)).start();


        }

    }


}
