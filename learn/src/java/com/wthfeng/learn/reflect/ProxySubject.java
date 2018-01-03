package com.wthfeng.learn.reflect;

/**
 * @author wangtonghe
 * @date 2017/10/12 15:28
 */
public class ProxySubject implements Subject {

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void load() {
        System.out.println("调用实际对象");
        subject.load();
        System.out.println("代理完成");
    }

    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        ProxySubject proxy = new ProxySubject(subject);
        proxy.load();
    }
}
