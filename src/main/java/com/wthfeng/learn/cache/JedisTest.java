package com.wthfeng.learn.cache;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangtonghe
 * @since 2018/11/2 10:40
 */
public class JedisTest {

    @Test
    public void testJedis() {


        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        CountDownLatch countDownLatch = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    jedis.set("a" + finalI, String.valueOf(finalI));
                    System.out.println("a" + finalI + " = " + jedis.get("a" + finalI));
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
