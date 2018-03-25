package com.wthfeng.learn.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * @author wangtonghe
 * @date 2017/10/1 19:50
 */
public class StreamTest {

    @Test
    public void test() {
        int min = new Random().ints(0, 100)
                .limit(10)
                .peek(e -> System.out.print(e + " "))
                .reduce(Integer.MAX_VALUE, (a, b) -> {
                    if (a > b) {
                        return b;
                    }
                    return a;
                });
        System.out.println("\n"+min);


    }

    @Test
    public void test2(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!");
            }
        });
        t.start();

        Thread t1 = new Thread(()->{
            System.out.println("hello world!");
        });
    }

    @Test
    public void forEach(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.forEach(e->System.out.print(e+" "));
    }


}
