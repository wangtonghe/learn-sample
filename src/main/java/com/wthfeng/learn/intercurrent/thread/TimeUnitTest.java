package com.wthfeng.learn.intercurrent.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author wangtonghe
 * @since 2018/11/5 18:11
 */
public class TimeUnitTest {

    @Test
    public void testUnit() {

        long num = TimeUnit.SECONDS.toMillis(1);
        System.out.println(num);

    }
}
