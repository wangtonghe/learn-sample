package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wangtonghe
 * @date 2017/9/24 12:06
 */
public class Algo {


    /**
     * 【来做题】
     * 统计一个字符串abcdefghijklmnopqrstuvwxyz…abcdefghijklmnopqrstuvwxyz（1千万个a-z，不可直接a=1千万……）
     * 中每个字母的个数。要求除了更好的方式（如更加Pythonic的方式），还要计算越快越好，并打印出代码执行时间
     */
    @Test
    public void AZCount() {
        long start = System.currentTimeMillis();
        String str = IntStream.rangeClosed('a', 'z').mapToObj(c -> "" +(char) c).collect(Collectors.joining());
        String repeatStr = Collections.nCopies(1000_0000, str).parallelStream().collect(Collectors.joining());
        Map<String, Long> result = repeatStr.chars().parallel()
                .mapToObj(c->""+(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println((endTime -start)/1000+"s");


    }






    /**
     * CopyOnWrite
     */
    @Test
    public void test2() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("" + i);
        }
        Set<Thread> set = new CopyOnWriteArraySet<>();
        list.parallelStream().forEach(e -> {
            set.add(Thread.currentThread());
        });
        System.out.println("共有" + set.size() + "个线程");
        System.out.println("共有" + Runtime.getRuntime().availableProcessors() + "个cpu");

    }

    /**
     * 分组统计
     */
    @Test
    public void test3() {

//        List<Integer> list = List.of(1,2,1,3,5,6,3);
//        list.stream().collect()

//        List<User> list = List.of(new User("xiaoming", 23), new User("ha", 21), new User("rt", 21));
//        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(User::getAge, Collectors.counting()));
//        System.out.println(map);

    }

    class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("User{");
            sb.append("name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }


}
