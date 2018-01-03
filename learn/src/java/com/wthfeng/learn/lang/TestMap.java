package com.wthfeng.learn.lang;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @author wangtonghe
 * @date 2017/12/27 13:28
 */
public class TestMap {


    @Test
    public void test(){
        Map<String,String> map = new HashMap<>();
        map.put("asd","ccc");
        map.put("dff","vbb");
        Collection<String> values = map.values();
        System.out.println(values);

    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>();
        list.add("xxx");
        List<String> sub = new ArrayList<>();
        sub.add("fff");
        sub.add("rrr");
        list.addAll(sub);
        System.out.println(list);
    }
}
