package preorder.test;


import org.junit.jupiter.api.Test;

class BinaryTreeTest {
    
    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree();
        
        binaryTree.setRoot(new TreeNode(1, "宋江"));
        
        TreeNode root = binaryTree.getRoot();
        TreeNode subNode1 = new TreeNode(2, "吴用");
        TreeNode subNode2 = new TreeNode(3, "卢俊义");
        TreeNode subNode3 = new TreeNode(4, "林冲");
        TreeNode subNode4 = new TreeNode(5, "关胜");
        
        
        root.setLeft(subNode1);
        root.setRight(subNode2);
        
        subNode2.setLeft(subNode4);
        subNode2.setRight(subNode3);
        
        binaryTree.preOrder();
        binaryTree.deleteNode(3);
        System.out.println();
        binaryTree.preOrder();
        
        
    }
}


/**
 * 一个普通的二叉树
 *
 * @author initial
 * @create 2021-03-31 15:50
 */
public class BinaryTree {
    
    /**
     * 需要有一个根节点
     * 默认为空
     */
    private TreeNode root;
    
    
    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        this.root.preOrder();
    }
    
    /**
     * 前序查找,根据id
     *
     * @param id 提供的id
     */
    public TreeNode preSearch(int id) {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        return this.root.preSearch(id);
    }
    
    /**
     * 中序遍历
     */
    public void suffixOrder() {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        this.root.suffixOrder();
    }
    
    /**
     * 中序查找,根据id
     *
     * @param id 提供的id
     * @return TreeNode 返回查找到的节点
     */
    public TreeNode suffixSearch(int id) {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        return this.root.suffixSearch(id);
    }
    
    
    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        this.root.postOrder();
    }
    
    /**
     * 后序查找,根据id
     *
     * @param id 提供的id
     * @return TreeNode 返回查找到的节点
     */
    public TreeNode postSearch(int id) {
        if (root == null) {
            throw new RuntimeException("根节点为空");
        }
        return this.root.postSearch(id);
    }
    
    /**
     * 根据id 删除对应的结点
     *
     * @param id 提供的id
     */
    public void deleteNode(int id) {
        //1. 首先判断非空判断
        if (this.root == null) {
            return;
        }
        
        //2. 判断当前根节点是否为要删除的
        if (root.id == id) {
            root = null;
            return;
        }
        
        //都不是就判断子节点
        this.root.delete(id);
        
        
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    
}
