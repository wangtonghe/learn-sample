package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序；练习
 * @author wangtonghe
 * @date 2017/8/16 09:22
 */
public class SortTest {

    class People implements Comparable<People>{

        private int id;

        private String name;

        @Override
        public int compareTo(People o) {
            return id-o.id;
        }

        public People(int id) {
            this.id = id;
        }
    }

    @Test
   public void test1(){
        People people1 = new People(2);
        People people2 = new People(34);
        people1.compareTo(people2);
        List<People> list = new ArrayList<>();

   }

   @Test
   public void test2(){
       double a = 0.1;
       double sum = 0;
       for(int i=0;i<3;i++){
           sum+=a;
       }
       System.out.println(0.1+0.1+0.1);
   }
}
