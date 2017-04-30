package common;

import org.junit.Test;
import util.Observer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/3/11 16:03
 */
public class TestClass {

    @Test
    public void test() {
        try {

          Class c = Sub.class;
            Constructor constructor = c.getConstructor(String.class);
            System.out.println(constructor.newInstance(String.class));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class Sub {
        public Sub(String name) {}

        void hello() {
            System.out.println("hello");
        }
    }
}
