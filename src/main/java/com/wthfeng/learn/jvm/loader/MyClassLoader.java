package com.wthfeng.learn.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author wangtonghe
 * @since 2019/4/14 19:03
 */
public class MyClassLoader extends ClassLoader {


    private String classpath;

    public MyClassLoader(ClassLoader parent, String classpath) {
        super(parent);
        this.classpath = classpath;
    }

    private byte[] loadByte(String name) {
        byte[] bytes = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File(classpath + "/" + name + ".class"));
            int len = inputStream.available();
            bytes = new byte[len];
            inputStream.read(bytes);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (ClassFormatError classFormatError) {
            classFormatError.printStackTrace();
            throw classFormatError;
        }

    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader(null, "/Users/wangtonghe/Desktop");
        Class clazz = classLoader.loadClass("Hello");
        System.out.println(clazz.getClassLoader());


    }
}
