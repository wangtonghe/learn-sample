package com.wthfeng.learn.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/11/24 17:00
 */
public class IteratorTest {


    public static void main(String[] args) {

        List<Integer> list =new ArrayList<>(Arrays.asList(3,4,5));

        Iterator<Integer> it = list.iterator();

        while (it.hasNext()){
            if(it.next().compareTo(4)==0){
                it.remove();
            }
        }
        System.out.println(list);
        List<Integer> list2 = List.of(45,67);

        list.addAll(0,list2);
        System.out.println(list);






    }
}
