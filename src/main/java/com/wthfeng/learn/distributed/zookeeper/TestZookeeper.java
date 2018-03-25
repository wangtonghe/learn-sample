package com.wthfeng.learn.distributed.zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangtonghe
 * @date 2017/5/25 12:56
 */
public class TestZookeeper implements Watcher {

    private static final int SESSION_TIMEOUT = 5000;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);


    public void process(WatchedEvent event) {
        System.out.println("event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();

        }

    }

    public  void cm(String[] args) throws Exception {



        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", SESSION_TIMEOUT, new TestZookeeper());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        System.out.println("链接成功！");
        String path1 = zooKeeper.create("/zk-book-2", "货币战争1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(path1 + "创建成功！");
        String path2 = zooKeeper.create("/zk-book-3", "货币战争2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(path2 + "创建成功！");


    }

    public  class  ZKCallback implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("rc:"+rc+",path:"+path+",ctx:"+ctx+",name:"+name);
        }
    }
}
