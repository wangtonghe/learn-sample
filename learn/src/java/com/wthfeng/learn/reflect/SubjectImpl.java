package com.wthfeng.learn.reflect;

/**
 * @author wangtonghe
 * @date 2017/10/12 15:28
 */
public class SubjectImpl implements Subject {
    @Override
    public void load() {
        System.out.println("实际代理对象");
    }
}

