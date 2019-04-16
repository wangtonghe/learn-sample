package com.wthfeng.learn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2019/3/7 11:41
 */
public class OOMTest {


    public static void main(String[] args) {


        List<Byte[]> list = new ArrayList<>();

        for (int i = 0; i < 120; i++) {
            list.add(new Byte[1024 * 1024]);
        }

    }
}
