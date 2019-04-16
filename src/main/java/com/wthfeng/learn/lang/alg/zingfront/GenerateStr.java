package com.wthfeng.learn.lang.alg.zingfront;

/**
 * @author wangtonghe
 * @since 2019/4/12 23:22
 */
public class GenerateStr {

    public static void main(String[] args) {
        GenerateStr generateStr = new GenerateStr();

        char[][] letters = {
                {'A', 'R', 'E'},
                {'I', 'P', 'D'},
                {'E', 'L', 'P'}

        };

        String[] targets = {"ARE", "PENPIEAPPLE", "APPLEPEN", "APPLE", "LIPS", "RED", "AIR", "PLEASE"};

        for (int i = 0; i < targets.length; i++) {
            boolean flag = generateStr.canGenerate(letters, targets[i]);
            System.out.println(targets[i] + " : " + flag);

        }
    }

    /**
     * 给定字符二维数组letters，是否能生成target字符串
     * 设二维数组为m*n，target字符串长度为K
     * 对于时间复杂度，构造邻接表（辅助表）的复杂度为O(m*n),主体部分复杂度为O(8*k),即O(n)级别。
     * 对于空间复杂度，邻接表需要空间O(8*m*n),即O(m*n)级别，需要栈空间（最小不需要，最多需要O(m*n)）,以及记录历史轨迹的数组（O(k)）
     * 算法描述： 先将二维数组构造为长度为m*n的邻接表，每个数据元素用链表串联起其能到达的元素。(即数组+链表的形式)
     * 遍历目标字符串，先在邻接表数组中查找到该字符，然后再向这个字符的链表中查找其后的字符，循环进行直到遍历结束。
     * 为解决可能有多个相同字符的情况，使用栈记录多余的情况，以便在找不到时从栈中取出元素回溯。
     * 为解决不能选取已走过路径，使用一个历史数组记录位置，若走过则放弃此路径。
     *
     * @param letters 二维数组
     * @param target  目标数组
     * @return bool
     */
    public boolean canGenerate(char[][] letters, String target) {

        if (letters == null || letters.length == 0 || target == null || target.length() == 0) {
            return false;
        }
        boolean flag = true;
        Stack<Node> stack = new Stack<>();

        // 构造邻接表
        Node[] list = generateAdj(letters);

        // 存储历史元素
        int[] history = new int[target.length()];
        // 历史元素索引
        int his = 0;
        int i = 1, pos;

        //从邻接表查找第一个元素，用于初始化
        if ((pos = findInArray(target.charAt(0), list, stack, 0)) == -1) {
            return false;
        }
        history[his++] = pos;
        // 遍历目标字符串
        while (i < target.length()) {

            char c = target.charAt(i);
            // 在指定邻接表链表中查找该元素，
            pos = findInLinked(c, list[pos], stack, i);

            // 若没找到或已在选取路径中，试图从栈中回溯
            if (pos == -1 || isContainsHistory(history, his, pos)) {
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                }
                // 若栈不为空，表示之前路径选取时有多余一个的选择，现在要应用这个选择了
                else {
                    Node pre = stack.pop();
                    pos = pre.pos;
                    // 重置遍历索引
                    i = pre.index;
                    his = i;
                    // 重置历史元素索引
                    history[his++] = pos;
                }
            } else {
                history[his++] = pos;
            }
            i++;
        }
        return flag;
    }


    /**
     * 构造临接表
     *
     * @param letters 二维数组
     * @return 邻接表
     */
    private Node[] generateAdj(char[][] letters) {
        int row = letters.length;
        int col = letters[0].length;
        Node[] list = new Node[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int pos = i * col + j;
                Node node = new Node(pos, letters[i][j]);
                list[pos] = node;
                if (i - 1 >= 0) {
                    node.next = new Node((i - 1) * col + j, letters[i - 1][j]);
                    node = node.next;
                }
                if (i + 1 < row) {
                    node.next = new Node((i + 1) * col + j, letters[i + 1][j]);
                    node = node.next;
                }
                if (j - 1 >= 0) {
                    node.next = new Node(i * col + (j - 1), letters[i][j - 1]);
                    node = node.next;
                }
                if (j + 1 < col) {
                    node.next = new Node(i * col + j + 1, letters[i][j + 1]);
                    node = node.next;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    node.next = new Node((i - 1) * col + j - 1, letters[i - 1][j - 1]);
                    node = node.next;
                }
                if (i - 1 >= 0 && j + 1 < col) {
                    node.next = new Node((i - 1) * col + j + 1, letters[i - 1][j + 1]);
                    node = node.next;
                }
                if (i + 1 < row && j - 1 >= 0) {
                    node.next = new Node((i + 1) * col + j - 1, letters[i + 1][j - 1]);
                    node = node.next;
                }
                if (i + 1 < row && j + 1 < col) {
                    node.next = new Node((i + 1) * col + j + 1, letters[i + 1][j + 1]);
                }
            }
        }
        return list;

    }

    /**
     * 在邻接表中查找指定字符，返回在邻接表的索引
     * 若无返回-1，若多余一个将多余的存入栈中
     *
     * @param c           指定字符
     * @param list        邻接表
     * @param stack       栈
     * @param targetIndex 字符所在目标字符串索引
     * @return 索引
     */
    private int findInArray(char c, Node[] list, Stack<Node> stack, int targetIndex) {

        int index = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i].value == c) {
                if (index == -1) {
                    index = i;
                } else {
                    list[i].index = targetIndex;
                    stack.push(list[i]);
                }
            }
        }
        return index;
    }

    /**
     * 在邻接表指定位置的链表中查找指定字符，返回在邻接表的索引
     * 若无返回-1，若多余一个将多余的存入栈中
     *
     * @param c           指定字符
     * @param head        邻接表位置头指针
     * @param stack       栈
     * @param targetIndex 字符所在目标字符串索引
     * @return 索引
     */
    private int findInLinked(char c, Node head, Stack<Node> stack, int targetIndex) {
        Node p = head.next;
        int pos = -1;

        while (p != null) {
            if (p.value == c) {
                if (pos == -1) {
                    pos = p.pos;
                } else {
                    p.index = targetIndex;
                    stack.push(p);
                }
            }
            p = p.next;
        }
        return pos;
    }

    /**
     * 是否在历史轨迹中
     *
     * @param history 历史轨迹数组
     * @param his     历史轨迹索引
     * @param pos     位置
     * @return bool
     */
    private boolean isContainsHistory(int[] history, int his, int pos) {
        for (int i = 0; i < his; i++) {
            int e = history[i];
            if (e == pos) {
                return true;
            }
        }
        return false;
    }


    /**
     * 邻接表元素
     */
    private class Node {

        /**
         * 记录到哪个位置了，以便回滚坐标用
         */
        private int index;

        private int pos;

        private char value;

        private Node next;

        private Node(int pos, char value) {
            this.value = value;
            this.pos = pos;
        }
    }

    /**
     * 自制简易栈
     *
     * @param <T>
     */
    private class Stack<T> {

        private static final int DEFAULT_SIZE = 16;
        private int size;
        private Object[] elements;

        Stack() {
            elements = new Object[DEFAULT_SIZE];
        }

        T push(T obj) {
            elements[size++] = obj;
            return obj;
        }

        T pop() {
            T obj = (T) elements[--size];
            return obj;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }


}
