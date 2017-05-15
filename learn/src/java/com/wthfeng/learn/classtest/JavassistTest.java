package com.wthfeng.learn.classtest;

import com.wthfeng.learn.sample.SampleTest;
import javassist.*;
import javassist.bytecode.AttributeInfo;
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
            CtClass ctClass = pool.getCtClass("com.wthfeng.learn.classtest.Sample");
            //使用当前线程的上下文加载器加载该类
            Class clazz = ctClass.toClass();
            System.out.println(Arrays.asList(clazz.getMethods()));

            CtClass Sample2Clazz = pool.makeClass("com.wthfeng.learn.classtest.Sample2");

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
            SampleTest sampleTest = (SampleTest) clazz.newInstance();
            sampleTest.hello();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            ClassPool pool = ClassPool.getDefault();
            Method method = Sample.class.getMethod("start", String.class);
            CtClass ctClass = pool.get("com.wthfeng.learn.classtest.Sample");
            int count = method.getParameterCount();
            Class<?>[] paramTypes = method.getParameterTypes();
            CtClass[] ctParams = new CtClass[count];
            for (int i = 0; i < count; i++) {
                ctParams[i] = pool.getCtClass(paramTypes[i].getName());
            }
            CtMethod ctMethod = ctClass.getDeclaredMethod("start", ctParams);
            MethodInfo methodInfo = ctMethod.getMethodInfo();

            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();

            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);

            System.out.println(attr.variableName(0));
//            System.out.println(attr.variableName(1));


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }


}

class Sample {
    private String name;

    public static void start(String tag) {
        System.out.println(tag);
    }
}
