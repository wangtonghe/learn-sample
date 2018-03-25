package com.wthfeng.learn.lang;

/**
 * @author wangtonghe
 * @date 2017/12/27 13:28
 */
public class TestMap {
    public static void main(String[] args) {
        Apple apple = new Apple();
        System.out.println(Fruit.a);
        System.out.println(Apple.b);
        System.out.println(apple.f);

    }


}

class Fruit {



    static {
        System.out.println("Fruit的静态代码块");
        a = 5;
    }

    static int a = 2;

    {
        f = 2;
        System.out.println("Fruit代码块");
    }
    int f = 1;


    Fruit() {
        System.out.println("Fruit构造器方法");

    }


}

class Apple extends Fruit {

    static {
        System.out.println("子类的静态代码块");
        b = 2;
    }

    static int b = 1;

    {
        System.out.println("apple代码块");
    }


    Apple() {
        System.out.println("apple构造器方法");
    }
}


