package arrbinarytree.test;

/**
 * 顺序存储二叉树
 *
 * @author initial
 * @create 2021-04-01 17:16
 */
public class ArrBinaryTreeTest {
    
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(array);
        arrBinaryTree.preOrder(0);
        System.out.println();
        arrBinaryTree.suffixOrder(0);
        System.out.println();
        arrBinaryTree.postOrder(0);
        
    }
    
    
}

class ArrBinaryTree {
    private final int[] arr;
    
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    
    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 从哪开始遍历,数组的索引.
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        //1. 首先打印当前节点
        System.out.println(arr[index]);
        
        //2. 然后向左递归遍历, 这里要防止数组越界
        if ((index * 2 + 1) < arr.length) {
            this.preOrder((index * 2 + 1));
        }
        
        //3. 然后向左=右递归遍历, 这里也要防止数组越界
        if ((index * 2 + 2) < arr.length) {
            this.preOrder((index * 2 + 2));
        }
    }
    
    /**
     * 顺序存储二叉树的中序遍历
     *
     * @param index 从哪开始遍历,数组的索引.
     */
    public void suffixOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        //1. 首先向左递归遍历, 这里要防止数组越界
        if ((index * 2 + 1) < arr.length) {
            this.suffixOrder((index * 2 + 1));
        }
        
        //2. 然后打印当前节点
        System.out.println(arr[index]);
        
        
        //3. 然后向右递归遍历, 这里也要防止数组越界
        if ((index * 2 + 2) < arr.length) {
            this.suffixOrder((index * 2 + 2));
        }
    }
    
    /**
     * 顺序存储二叉树的后序遍历
     *
     * @param index 从哪开始遍历,数组的索引.
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        //1. 首先向左递归遍历, 这里要防止数组越界
        if ((index * 2 + 1) < arr.length) {
            this.postOrder((index * 2 + 1));
        }
        
        
        //2. 然后向右递归遍历, 这里也要防止数组越界
        if ((index * 2 + 2) < arr.length) {
            this.postOrder((index * 2 + 2));
        }
        
        //3. 然后打印当前节点
        System.out.println(arr[index]);
        
    }
}
