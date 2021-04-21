package preorder.test;

/**
 * 二叉树的节点类
 *
 * @author initial
 * @create 2021-03-31 15:51
 */
public class TreeNode {
    
    final int id;
    private final String name;
    
    /**
     * 每个节点,都有左右节点.
     * 默认都为空
     */
    private TreeNode left;
    private TreeNode right;
    
    
    TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    /**
     * 前序遍历
     */
    void preOrder() {
        //1. 首先打印当前节点信息
        System.out.println(this);
        
        //2. 看看左节点是否为空, 不为空就继续前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        
        //3. 看看右节点是否为空, 不为空就继续前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
        
    }
    
    /**
     * 指定id进行前序查找
     *
     * @param id 指定的id
     * @return TreeNode 返回null,没查找到. 查找到就返回此节点
     */
    TreeNode preSearch(int id) {
        //1. 首先判断当前节点是不是要查的节点
        if (this.id == id) {
            return this;
        }
        
        /*
        2. 看看左节点是否为空, 不为空就继续前序查找,
            此时需要定义一个临时变量, 获取递归查找的结果
         */
        TreeNode treeNode = null;
        if (this.left != null) {
            treeNode = this.left.preSearch(id);
        }
        
        
        //如果左节点前序递归查找的结果不为空, 证明我们已经查找到了.
        // 返回它即可
        if (treeNode != null) {
            return treeNode;
        }
        
        /*
        3.程序运行到这里,证明左节点没查找到. 此时treeNode它还是null;
        那么就看看右节点是否为空, 不为空就继续前序查找和之前类似, 获取查找结果然后判断
         */
        if (this.right != null) {
            treeNode = this.right.preSearch(id);
        }
        
       /*
        程序运行到这里,证明左节点没查找到, 右节点不确定有没有查找到
        就不管查没查到都给它返回.没查找到就返回的就是null
        查到了返回的就是对应的节点
         */
        return treeNode;
        
        
    }
    
    
    /**
     * 中序遍历
     */
    void suffixOrder() {
        
        //1. 先看看左节点是否为空, 不为空就继续中序遍历
        if (this.left != null) {
            this.left.suffixOrder();
        }
        
        //2. 打印当前节点信息
        System.out.println(this);
        
        //3. 看看右节点是否为空, 不为空就继续前序遍历
        if (this.right != null) {
            this.right.suffixOrder();
        }
        
    }
    
    
    /**
     * 指定id进行中序查找
     *
     * @param id 指定的id
     * @return TreeNode 返回null,没查找到. 查找到就返回此节点
     */
    TreeNode suffixSearch(int id) {
        
        TreeNode tempNode = null;
        
        //1. 先看看左节点是否为空, 不为空就继续中序查找
        if (this.left != null) {
            tempNode = this.left.suffixSearch(id);
        }
        
        //左节点找到,返回
        if (tempNode != null) {
            return tempNode;
        }
        
        //2. 左节点没查到, 判断当前节点是否是要查的
        //当前节点匹配,返回
        if (this.id == id) {
            return this;
        }
        
        //3. 左节点,当前节点都不匹配. 看看右节点是否为空, 不为空就继续前序遍历
        if (this.right != null) {
            tempNode = this.right.suffixSearch(id);
        }
        
        /* 这里和前序查找相同.
           程序运行到这里就证明左节点和当前节点都没查到.
           所以不管右节点找没找到都返回tempNode.
         */
        return tempNode;
        
    }
    
    
    /**
     * 后序遍历
     */
    void postOrder() {
        //1. 先看看左节点是否为空, 不为空就继续中序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        
        //2. 看看右节点是否为空, 不为空就继续前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        
        //3. 打印当前节点信息
        System.out.println(this);
    }
    
    
    /**
     * 后序查找
     *
     * @param id 提供的id
     * @return TreeNode 查不到为null
     */
    TreeNode postSearch(int id) {
        
        TreeNode tempNode = null;
        
        //1. 先看看左节点是否为空, 不为空就继续中序遍历
        if (this.left != null) {
            tempNode = this.left.postSearch(id);
        }
        
        //左节点找到.直接返回
        if (tempNode != null) {
            return tempNode;
        }
        
        
        //2. 看看右节点是否为空, 不为空就继续前序遍历
        if (this.right != null) {
            tempNode = this.right.postSearch(id);
        }
        
        //右节点找到.直接返回
        if (tempNode != null) {
            return tempNode;
        }
        
        
        //3. 左右都不是,看看当前是不是
        //当前是就返回this
        if (this.id == id) {
            return this;
        }
        
        //当前也不是,返回null
        return null;
        
        
    }
    
    
    /**
     * 删除某个节点
     *
     * @param id 提供的id
     */
    void delete(int id) {
        //这里我们就默认root结点不为空了, 因为我们上一层已经判断过了
        
        //1. 首先判断当前节点左子节点是否为要删除的
        if (this.left != null && this.left.id == id) {
            
            //如果是, 首先进行判断,看看他是不是一个叶子结点
            
            //如果它的左子节点为空,右不为空.
            if (this.left.left == null && this.left.right != null) {
                //就让它的右子节点代替他
                this.left = this.left.right;
                return;
            }
            
            //如果右子节点为空,左不为空
            if (this.left.right == null && this.left.left != null) {
                //让左子节点代替他
                this.left = this.left.left;
                return;
            }
            
            //如果左右子节点都不为空, 就让左子节点替代它
            if (this.left.left != null && this.left.right != null) {
                //代替它时,要考虑到它的右节点. 所以先让它的右子节点挂在左子节点上
                this.left.left.right = this.right.right;
                //然后再替代它
                this.left = this.left.left;
                return;
            }
            
            //如果左右子节点为空,就直接让它置空
            this.left = null;
            return;
            
        }
        
        //2. 其次判断当前节点右子节点是否为要删除的. 方法和之前一样
        if (this.right != null && this.right.id == id) {
            
            //首先进行判断,看看他是不是一个叶子结点
            
            //如果左子节点为空,右不为空
            if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
                return;
            }
            
            //如果右子节点为空,左不为空
            if (this.right.left != null && this.right.right == null) {
                this.right = this.right.left;
                return;
            }
            
            //如果左右子节点都不为空, 就让左子节点替代它
            if (this.right.left != null && this.right.right != null) {
                //代替它时,要考虑到它的右子节点. 所以先让它的右子节点挂在左子节点上
                this.right.left.right = this.right.right;
                //然后再代替
                this.right = this.right.left;
                return;
            }
            
            //如果左右子节点为空. 直接让它置空
            this.right = null;
            return;
            
        }
        
        //3. 如果左右子节点都不是,就继续遍历
        if (this.left != null) {
            this.left.delete(id);
        }
        
        if (this.right != null) {
            this.right.delete(id);
        }
        
        
    }
    
    
    public TreeNode getLeft() {
        return left;
    }
    
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    
    public TreeNode getRight() {
        return right;
    }
    
    public void setRight(TreeNode right) {
        this.right = right;
    }
    
    
    /**
     * 重写toString(), 方便打印信息.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    
    
}
