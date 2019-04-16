package com.wthfeng.learn.util.guava;

import com.google.common.collect.*;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.*;

/**
 * @author wangtonghe
 * @since 2018/9/26 14:38
 */
public class TestGuava {

    @Test
    public void testCollection() {
        List<String> list = Lists.newArrayList("123", "abc");
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        ImmutableList<String> immutableList = ImmutableList.of("abc", "bcf");

        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("abc", 1);
        multimap.put("abc", 2);
        multimap.put("v", 3);

        System.out.println(multimap.get("abc"));
    }

    @Test
    public void testLimit() {
        final RateLimiter limiter = RateLimiter.create(100);
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 1000; i++) {
            double waitTime = limiter.acquire(new Random().nextInt(10)+1);
            System.out.println(waitTime);

        }
        System.out.println(System.currentTimeMillis() - start);

    }

}
