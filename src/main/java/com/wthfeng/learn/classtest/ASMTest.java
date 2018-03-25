package com.wthfeng.learn.classtest;


import org.junit.Test;
import org.objectweb.asm.*;

import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/5/14 20:59
 */
public class ASMTest {

    @Test
    public void test() {
        try {

            // 读取HelloTest的字节码信息到ClassReader中
            ClassReader reader = new ClassReader(HelloTest.class.getName());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            //accept接收了一个ClassAdapter的子类，想要操作什么，就在子类实现什么
            reader.accept(new ClassAdapter(cw) {

                /**
                 * 会遍历该类的所有方法，你可以对不需要操作的方法直接返回
                 */
                @Override
                public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                                 final String signature, final String[] exceptions) {
                    //不需要操作的方法，直接返回，注意不要返回null,会把该方法删掉
                    if (!name.equals("test1")) {
                        return super.visitMethod(access, name, desc, signature, exceptions);
                    }
                    MethodVisitor v = super.visitMethod(access, name, desc,
                            signature, exceptions);
                    /**
                     *  遍历该方法信息，比如参数、注解等，这里我们要操作参数，所以实现了参数方法
                     */
                    return new MethodAdapter(v) {
                        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                            //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
                            System.out.println(name + "," + index);

                            super.visitLocalVariable(name, desc, signature, start, end, index);
                        }
                    };
                }
            }, 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class HelloTest {
    public void test1(String userName) {
        System.out.println(userName);
    }

    public void test2(String email) {
        System.out.println(email);
    }
}
