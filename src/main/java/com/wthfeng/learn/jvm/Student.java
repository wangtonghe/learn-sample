package com.wthfeng.learn.jvm;

import java.util.List;
import java.util.Vector;

/**
 * @author wangtonghe
 * @since 2019/3/7 16:26
 */
public class Student {

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;

    private String name;


    private List<WebPage> pages = new Vector<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void visit(WebPage webPage) {
        pages.add(webPage);
    }
}
