package com.wthfeng.learn.distributed.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;

import java.io.IOException;

/**
 * @author wangtonghe
 * @since 2018/9/26 17:40
 */
public class TestZoo {

    private ZooKeeper zooKeeper;

    @Before
    public void before() {
        String host = "127.0.0.1:2181";

        try {
            zooKeeper = new ZooKeeper(host, 5000, (event) -> {
                Watcher.Event.KeeperState keeperState = event.getState();

                switch (keeperState) {
                    case SyncConnected:
                        System.out.println("zookeeper 连接上了");
                        break;
                    case Disconnected:
                        System.out.println();
                        break;
                    case Expired:
                        System.out.println("过期了");
                        break;
                    default:
                        System.out.println("其他情况");


                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
