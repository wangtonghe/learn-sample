package com.wthfeng.learn.stream;

/**
 * @author wangtonghe
 * @date 2017/10/10 10:51
 */
@FunctionalInterface
public interface Filter<T> {

    boolean apply(T from);
}
