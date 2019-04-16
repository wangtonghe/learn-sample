package com.wthfeng.learn.spring;

import java.util.List;
import java.util.Map;

/**
 * @author wangtonghe
 * @since 2019/4/3 14:26
 */
public class CollectionBean {

//    public CollectionBean(List<String> list, Map<String, Comparable> map) {
//        this.list = list;
//        this.map = map;
//    }

    private List<String> list;

    private Map<String, Comparable> map;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Comparable> getMap() {
        return map;
    }

    public void setMap(Map<String, Comparable> map) {
        this.map = map;
    }
}
