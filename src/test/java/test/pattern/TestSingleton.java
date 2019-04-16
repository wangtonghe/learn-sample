package test.pattern;

import com.wthfeng.learn.pattern.singleton.EnumSingleton;
import com.wthfeng.learn.pattern.singleton.Singleton;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @author wangtonghe
 * @since 2018/9/18 10:27
 */
public class TestSingleton {

    @Test
    public void testEnum() {
        EnumSingleton enumSingleton = EnumSingleton.getInstance();
        EnumSingleton enumSingleton1 = EnumSingleton.getInstance();
        System.out.println(enumSingleton == enumSingleton1);

    }

    @Test
    public void testSingleton() throws Exception {

        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton singleton1 = constructor.newInstance();

        Singleton singleton = Singleton.getInstance();


        System.out.println(singleton == singleton1);


    }

    @Test
    public void testSerialSingleton() throws Exception {
        Singleton singleton = Singleton.getInstance();
        ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("tmp.java"));
        objOut.writeObject(singleton);
        objOut.close();
        ObjectInputStream objIn = new ObjectInputStream(new FileInputStream("tmp.java"));
        Singleton serialObj = (Singleton) objIn.readObject();
        serialObj.dispaly();
        objIn.close();
        System.out.println(singleton == serialObj);

    }

    @Test
    public void testSerialCase() throws Exception {

        Man man = new Man();
        man.setAge(30);
        man.setSex(0);
        Man retObj = (Man) getSerialObj(man);
        retObj.display();

        Chinese chinese = new Chinese();
        chinese.setAge(40);
        chinese.setSex(2);
        Chinese chinese1 = (Chinese) getSerialObj(chinese);
        chinese1.display();
    }


    private Object getSerialObj(Man obj) throws Exception {

        ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream("test"));
        objout.writeObject(obj);
        ObjectInputStream objin = new ObjectInputStream(new FileInputStream("test"));

        Object retObj = objin.readObject();
        objout.close();
        objin.close();
        return retObj;
    }


}
