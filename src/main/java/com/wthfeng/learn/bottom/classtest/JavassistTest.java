package com.wthfeng.learn.bottom.classtest;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/5/14 17:58
 */
public class JavassistTest {

    @Test
    public void test() {
        //使用默认的类搜索路径
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.getCtClass("com.wthfeng.learn.bottom.classtest.Sample");
            //使用当前线程的上下文加载器加载该类
            Class clazz = ctClass.toClass();
            System.out.println(Arrays.asList(clazz.getMethods()));

            CtClass Sample2Clazz = pool.makeClass("com.wthfeng.learn.bottom.classtest.Sample2");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test2() {

        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass ctClass = pool.get("com.wthfeng.learn.sample.SampleTest");
            CtMethod ctMethod = ctClass.getDeclaredMethod("hello");
            ctMethod.insertAfter("{System.out.println(\" world!\");}");
            Class clazz = ctClass.toClass();
//            SampleTest sampleTest = (SampleTest) clazz.newInstance();
//            sampleTest.hello();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            //获取要操作的类对象
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get("com.wthfeng.learn.bottom.classtest.Sample");

            //获取要操作的方法参数类型数组，为获取该方法代表的CtMethod做准备
            Method method = Sample.class.getMethod("start", String.class);
            int count = method.getParameterCount();
            Class<?>[] paramTypes = method.getParameterTypes();
            CtClass[] ctParams = new CtClass[count];
            for (int i = 0; i < count; i++) {
                ctParams[i] = pool.getCtClass(paramTypes[i].getName());
            }


            CtMethod ctMethod = ctClass.getDeclaredMethod("start", ctParams);
            //得到该方法信息类
            MethodInfo methodInfo = ctMethod.getMethodInfo();

            //获取属性变量相关
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();

            //获取方法本地变量信息，包括方法声明和方法体内的变量
            //需注意，若方法为非静态方法，则第一个变量名为this
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            int pos = Modifier.isStatic(method.getModifiers()) ? 0 : 1;

            for (int i = 0; i < 3; i++) {
                System.out.println(attr.variableName(i + pos));

            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Sample {
    private String name;

    public void start(String tag) {
        int i = 0;
        String abc = "abc";
        System.out.println(tag);
        System.out.println(abc);
        System.out.println(i);
    }
}
