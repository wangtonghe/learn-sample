package com.wthfeng.learn.lang.alg;

import java.util.Stack;

/**
 * 二叉树
 *
 * @author wangtonghe
 * @since 2018/9/23 17:06
 */
public class BinaryTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public BinaryTree() {

    }

    public BinaryTree(int rootValue) {
        root = new TreeNode(rootValue);
    }

    public void setLeft(TreeNode node, TreeNode left) {
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        node.setLeft(left);
    }

    public void setRight(TreeNode node, TreeNode right) {
        if (node == null) {
            throw new NullPointerException("node is null");
        }
        node.setRight(right);
    }

    public void setRoot(TreeNode treeNode) {
        this.root = treeNode;
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     */
    public void frontTraverse(TreeNode root) {
        if (root != null) {
            System.out.print(root.getValue() + " ");
            frontTraverse(root.getLeft());
            frontTraverse(root.getRight());
        }
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     */
    public void midTraverse(TreeNode root) {
        if (root != null) {
            midTraverse(root.getLeft());
            System.out.print(root.getValue() + " ");
            midTraverse(root.getRight());
        }

    }

    /**
     * 后序遍历
     *
     * @param root 根节点
     */
    public void behindTraverse(TreeNode root) {
        if (root != null) {
            behindTraverse(root.getLeft());
            behindTraverse(root.getRight());
            System.out.print(root.getValue() + " ");
        }
    }

    /**
     * 前序非递归遍历
     * 使用栈保存结果
     *
     * @param root 根节点
     */
    public void frontNotR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            System.out.print(node.getValue() + " ");
            stack.push(node.getRight());
            stack.push(node.getLeft());
        }
    }

    /**
     * 中序非递归遍历
     * 使用栈保存结果
     *
     * @param root 根节点
     */
    public void midNotR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(new TreeNode(node.getValue()));
                stack.push(node.getLeft());
            } else {
                System.out.print(node.getValue() + " ");
            }
        }
    }

    /**
     * 中序遍历非递归
     *
     * @param root 根节点
     */
    public void midNotR2(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                TreeNode cur = stack.pop();
                System.out.print(cur.getValue() + " ");
                node = cur.getRight();
            }
        }
    }

    public void behindNotR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if(node!=null){
                stack.push(node.getRight());
                stack.push(node.getLeft());
                System.out.println();
            }

        }

    }


}
