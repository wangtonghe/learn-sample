package test.pattern;

import java.io.Serializable;

/**
 * @author wangtonghe
 * @since 2018/9/18 14:34
 */
class Man implements Serializable {
    private int age;

    private int sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void display() {
        System.out.println("this is a man");
    }
}
