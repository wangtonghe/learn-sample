package com.wthfeng.learn.lang.exception;

import org.junit.Test;

/**
 * @author wangtonghe
 * @since 2019/2/26 09:48
 */
public class ExceptionTest {

    @Test
    public void testException() {

        try {
            int a = 10 / 0;
        } catch (Error e) {
            e.printStackTrace();
        }


    }
}
