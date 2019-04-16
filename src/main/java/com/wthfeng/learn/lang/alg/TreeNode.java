package com.wthfeng.learn.lang.alg;

/**
 * @author wangtonghe
 * @since 2018/9/23 17:19
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    void setRight(TreeNode right) {
        this.right = right;
    }
}
