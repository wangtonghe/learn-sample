package com.wthfeng.learn.lang.alg;

/**
 * 存储最小元素栈的栈结构
 * <p>
 * 添加了一个辅助栈，用于储存最小元素，每次做数据出栈操作时，均弹出一个辅助栈的元素。
 *
 * @author wangtonghe
 * @since 2018/9/23 20:25
 */
public class MyStack {

    private int[] elements;

    private int[] mins;

    private int index;

    private int minIndex;

    private int size;


    public MyStack(int size) {
        this.elements = new int[size];
        this.mins = new int[size];
        this.size = size;
    }

    private void add(int e) {
        if (index == size) {
            throw new ArrayIndexOutOfBoundsException("stack index " + index);
        }
        elements[index++] = e;
    }

    public void push(int e) {
        add(e);
        add2Assist(e);
    }

    private void add2Assist(int e) {
        if (minIndex == 0) {
            mins[minIndex++] = e;
        } else {
            int min = mins[minIndex - 1];
            if (e < min) {
                mins[minIndex++] = e;
            } else {
                mins[minIndex++] = min;
            }
        }
    }

    public int pop() {
        int value = pop0();
        removeAssist();
        return value;


    }

    private int pop0() {
        if (index == 0) {
            throw new ArrayIndexOutOfBoundsException("stack index " + index);
        }
        index--;
        return elements[index];
    }

    public void removeAssist() {
        minIndex--;
    }

    public int min() {
        return mins[minIndex];
    }


    public int get() {
        return elements[index - 1];
    }
}
