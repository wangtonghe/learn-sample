package com.wthfeng.learn.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author wangtonghe
 * @since 2019/3/7 16:25
 */
public class TraceStudent {

    static List<WebPage> webPageList = new Vector<>();

    public static void createPages() {


        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            WebPage webPage = new WebPage();
            webPage.setContent("http://www." + i + ".com");
            webPage.setUrl(String.valueOf(i));
            webPageList.add(webPage);
        }
    }

    public static void main(String[] args) {
        createPages();

        Student student1 = new Student(1, "aa");

        Student student2 = new Student(2, "bb");

        Student student3 = new Student(3, "cc");


        for (int i = 0; i < webPageList.size(); i++) {
            WebPage webPage = webPageList.get(i);
            if (i % 3 == 0) {
                student1.visit(webPage);
            } else if (i % 3 == 1) {
                student2.visit(webPage);
            } else {
                student3.visit(webPage);
            }
        }

        List<Byte[]> list =new ArrayList<>();

        for (int i = 0; i <100 ; i++) {
            list.add(new Byte[1024*500]);


        }


    }
}
