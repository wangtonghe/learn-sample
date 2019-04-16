package com.wthfeng.learn.distributed.zookeeper.origin;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @author wangtonghe
 * @since 2019/3/26 15:36
 */
public class ClientTest implements Watcher {

    private static CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) throws Exception {

        // zk的连接
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ClientTest());
        System.out.println(zooKeeper.getState());
        try {
            latch.await();
            System.out.println("连接成功！");
        } catch (InterruptedException e) {
            System.out.println("中断异常");
        }


        // 创建节点
//        String path1 = zooKeeper.create("/zk-test-10", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.EPHEMERAL);
//        System.out.println(path1);
//
//        String path2 = zooKeeper.create("/zk-test-2", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.EPHEMERAL_SEQUENTIAL);
//        System.out.println(path2);
//
//        zooKeeper.create("/zk-test-3", "test3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.EPHEMERAL_SEQUENTIAL, (rc, path, ctx, name) -> {
//                    if (rc == 0) {
//                        System.out.println("path:" + path + ";ctx:" + ctx + ",name:" + name);
//                    }
//                }, "I am ok");


        // 子节点变化
//        String path = "/zoo2";
//        zooKeeper.getChildren(path, new NodeChangeNotify(zooKeeper, path, (list) -> {
//            System.out.println("list:" + list);
//
//        }));

        String path2 = "/zoo3";
        zooKeeper.getData(path2, new NodeChangeNotify(zooKeeper, path2, (bytes) -> {
            System.out.println(bytes);

        }), null);


        Thread.sleep(Integer.MAX_VALUE);
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive event:" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {

            latch.countDown();

        }


    }

    static class NodeChangeNotify implements Watcher {

        private ZooKeeper zooKeeper;

        private String path;

        private Consumer<Object> consumer;

        public NodeChangeNotify(ZooKeeper zooKeeper, String path, Consumer<Object> consumer) {
            this.zooKeeper = zooKeeper;
            this.path = path;
            this.consumer = consumer;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    List<String> list = zooKeeper.getChildren(path, new NodeChangeNotify(zooKeeper, path, consumer));
                    consumer.accept(list);

                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    byte[] data = zooKeeper.getData(path, new NodeChangeNotify(zooKeeper, path, consumer), null);
                    consumer.accept(data);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
