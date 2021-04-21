package threadedbinarytree;

/**
 * 线索化二叉树的节点
 *
 * @author initial
 * @create 2021-04-01 19:39
 */
public class TreeNode {
    
    final int id;
    
    /**
     * 每个节点,都有左右节点.
     * 默认都为空
     */
    TreeNode left;
    TreeNode right;
    
    /**
     * 每个节点的左右指针,我们都给他一个标记.
     * 如果leftType=0,证明它指向的是一个左子树,
     * 如果是1证明它指向的是一个前驱结点
     */
    private int leftType;
    
    /**
     * rightType=0,证明它指向的是一个右子树,
     * 如果是1证明它指向的是一个后继结点
     */
    private int rightType;
    
    /**
     * pre指针用于指向当前结点的上一个节点.用于线索化后继结点
     */
    private TreeNode pre;
    
    
    TreeNode(int id) {
        this.id = id;
        
    }
    
    /**
     * 从当前节点开始线索化
     * 这里采用的是中序遍历,所以线索化也是中序遍历的方法
     *
     * @param node 开始的节点
     */
    void threadedTree(TreeNode node) {
        //1. 首先判断当前节点是否为空, 为空无法线索化
        if (node == null) {
            return;
        }
        
        //2. 首先线索化左节点,也就是前驱节点
        threadedTree(node.left);
        
        /*3.运行到这里就证明当前节点的左指针为空了
            处理当前节点的前驱节点
            只有当当前节点的左节点为空时,才可以线索化
            让左节点的left指向前驱节点,然后修改它的类型
         */
        if (node.left == null) {
            node.left = pre;
            node.leftType = 1;
        }
        
        /*4. 处理当前节点的后继节点
            我们线索化后继结点. 必须要先走到后继结点的位置.
            然后让pre指向当前结点.  此时pre就相当于之前的当前结点.
            然后看看之前的当前结点.right是否为空, 为空就让它指向现在的node
            也就是之前节点的后继结点.
     
         */
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        
        //5. 每次指向后, pre就后移. 时刻让pre保持是node的上一个节点
        pre = node;
        
        
        //6. 接着线索化右子树
        threadedTree(node.right);
        
        
    }
    
    
    /**
     * 从node处开始, 进行中序线索化遍历
     *
     * @param node 要开始的结点
     */
    public void suffixThreadedOrder(TreeNode node) {
        
        while (node != null) {
            //1. 首先找到中序线索化开始的结点,也就是找到第一个leftType=1的节点
            while (node.leftType == 0) {
                node = node.left;
            }
            
            //2.找到后打印
            System.out.println(node);
            
            
            //3. 然后判断它的后继节点, 如果node.rightType == 1 证明我们之前线索化处理时,给他设置了后继结点
            //  所以直接让它移动到它的后继结点并且打印.
            while (node.rightType == 1) {
                //如果是直接后继,就打印
                System.out.println(node.right);
                //然后node后移到它的后继结点
                node = node.right;
            }
            //运行到此, 证明当前节点的rightType=0 ,也就是它本来就有后继结点
            //我们之前线索化没处理他. 所以直接让它移动到它的后继结点
            node = node.right;
            
        }
        
        
    }
    
    
    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                '}';
    }
    
    
}
