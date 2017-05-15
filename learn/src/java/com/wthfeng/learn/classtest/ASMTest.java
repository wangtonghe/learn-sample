package com.wthfeng.learn.classtest;



import jdk.internal.org.objectweb.asm.*;
import org.junit.Test;
import org.objectweb.asm.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/5/14 20:59
 */
public class ASMTest {

    @Test
    public void test(){
        try {

            ClassReader reader = new ClassReader(HelloTest.class.getName());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            reader.accept(new ClassAdapter(cw) {

                @Override
                public MethodVisitor visitMethod(final int access, final String name, final String desc,
                                                                                final String signature, final String[] exceptions) {
                    System.out.println(access+":"+name+":"+desc+","+signature+" "+exceptions);


                    MethodVisitor v = super.visitMethod(access, name, desc,
                            signature, exceptions);
                    return new MethodAdapter(v) {
                        public void visitLocalVariable(String name, String desc, String signature, Label start,Label end, int index) {
                            //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
                            System.out.println(name+","+desc+" ,"+signature+","+start+","+end+","+index);

                            super.visitLocalVariable(name, desc, signature, start, end, index);
                        }
                    };
                }
            },0);



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class HelloTest{
    public void test1(String userName){
        System.out.println(userName);
    }
}
