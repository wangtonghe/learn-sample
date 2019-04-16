package com.wthfeng.learn.lang.alg;

import java.util.Stack;

/**
 * @author wangtonghe
 * @since 2019/2/23 14:40
 */
public class TreeNodeTest {

    class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        TreeNode root = new TreeNode(pre[0]);

        reserveBuild(root, 0, 0, pre.length - 1, pre, in);

        return root;
    }

    /**
     *
     * @param node
     * @param base
     * @param low
     * @param high
     * @param pre
     * @param in
     */
    private void reserveBuild(TreeNode node, int base, int low, int high, int[] pre, int[] in) {
        int rootValue = pre[base];
        int index = getIndex(rootValue, in);
        int leftLen = index - low;
        int rightLen = high - index;
        // 存在左子树
        if (leftLen > 0) {

            TreeNode left = new TreeNode(pre[base + 1]);
            node.left = left;
            if (leftLen > 1) {
                reserveBuild(left, base + 1, low, index - 1, pre, in);
            }

        }
        if (rightLen > 0) {
            TreeNode right = new TreeNode(pre[base + 1 + leftLen]);
            node.right = right;
            if (rightLen > 1) {
                reserveBuild(right, base + 1 + leftLen, index + 1, high, pre, in);
            }

        }
    }

    private int getIndex(int val, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (val == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = treeNodeTest.reConstructBinaryTree(pre, in);
        print(treeNode);

    }

    private static void print(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        System.out.println(root.val);
        stack.push(root.right);
        stack.push(root.left);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }

        }

    }


}
