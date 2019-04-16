package test.alg;

import com.wthfeng.learn.lang.alg.BinaryTree;
import com.wthfeng.learn.lang.alg.TreeNode;
import org.junit.Test;


/**
 * @author wangtonghe
 * @since 2018/9/23 17:16
 */
public class TestTree {


    @Test
    public void testTree() {


        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(8);
        TreeNode left = new TreeNode(6);
        TreeNode right = new TreeNode(10);
        TreeNode l_left = new TreeNode(5);
        TreeNode l_right = new TreeNode(7);
        TreeNode r_left = new TreeNode(9);
        TreeNode r_right = new TreeNode(11);

        binaryTree.setRoot(root);
        binaryTree.setLeft(root, left);
        binaryTree.setRight(root, right);
        binaryTree.setLeft(left, l_left);
        binaryTree.setRight(left, l_right);
        binaryTree.setLeft(right, r_left);
        binaryTree.setRight(right, r_right);

        binaryTree.frontTraverse(root);
        System.out.println();
        binaryTree.midTraverse(root);
        System.out.println();
        binaryTree.behindTraverse(root);
        System.out.println();
        binaryTree.frontNotR(root);
        System.out.println();
        binaryTree.midNotR(root);
        System.out.println();
        binaryTree.midNotR2(root);
    }

    public boolean verifyBeHindTraverse(int[] arr, int low, int high) {
        if (low == high) {
            return true;
        } else if (low > high) {
            return false;
        }
        int root = arr[high];
        int i;
        boolean hasLeft = false;
        boolean hasRight = false;
        boolean left = true, right = true;
        for (i = low; i < high; i++) {
            if (root < arr[i]) {
                hasLeft = true;
                break;
            }
        }
        if (i < high) {
            for (int k = i; k < high; k++) {
                if (root > arr[k]) {
                    return false;
                }
            }
            hasRight = true;
        } else {
            hasLeft = true;
        }
        if (hasLeft) {
            left = verifyBeHindTraverse(arr, low, i - 1);
        }
        if (hasRight) {
            right = verifyBeHindTraverse(arr, i, high - 1);
        }
        return left && right;

    }

    @Test
    public void testBeHindT() {
        int[] arr = {7, 4, 6, 5};
        System.out.println(verifyBeHindTraverse(arr, 0, arr.length - 1));
    }


}

