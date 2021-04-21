package threadedbinarytree;

import org.junit.jupiter.api.Test;

/**
 * 线索化二叉树
 *
 * @author initial
 * @create 2021-04-01 19:39
 */
public class ThreadedBinaryTree {
    
    TreeNode root;
    
    public void suffixThreaded(TreeNode root) {
        //不为空才中序线索化
        if (this.root != null) {
            this.root.threadedTree(root);
        }
    }
    
    
    public void suffixThreadedOrder() {
        //1. 先看看左节点是否为空, 不为空就继续中序遍历
        if (this.root != null) {
            this.root.suffixThreadedOrder(root);
        }
    }
    
    
}


class Test1 {
    @Test
    public void test() {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(6);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(10);
        TreeNode treeNode5 = new TreeNode(14);
        
        
        tree.root = treeNode;
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        
        tree.suffixThreaded(tree.root);
        tree.suffixThreadedOrder();
        
        
    }
    
}