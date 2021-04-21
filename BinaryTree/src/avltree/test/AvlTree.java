package avltree.test;

import org.junit.jupiter.api.Test;

class AvlTreeTest {
    @Test
    public void test() {
        AvlTree avlTree = new AvlTree();
        avlTree.addNode(new Node(10));
        avlTree.addNode(new Node(12));
        avlTree.addNode(new Node(8));
        avlTree.addNode(new Node(9));
        avlTree.addNode(new Node(7));
        avlTree.addNode(new Node(6));
        
        avlTree.infixOrder();
        System.out.println("树的高度:" + avlTree.root.getHeight());
        System.out.println("左子树高度:" + avlTree.root.leftHeight());
        System.out.println("右子树高度:" + avlTree.root.rightHeight());
        
        
    }
}


/**
 * 平衡二叉树
 *
 * @author initial
 * @create 2021-04-07 19:42
 */
public class AvlTree {
    
    /**
     * 二叉排序树的根节点
     */
    Node root;
    
    
    /**
     * 添加节点
     *
     * @param node 待添加的节点
     */
    public void addNode(Node node) {
        //1. 如果根节点为空, 直接让它作为根节点
        if (this.root == null) {
            this.root = node;
            return;
        }
        
        //不为空,则从根节点开始判断,插入
        this.root.add(node);
    }
    
    
    /**
     * 删除value对应的节点
     *
     * @param value 指定要删除的节点的值
     */
    public void deleteNode(int value) {
        if (root == null) {
            return;
        }
        //1. 首先获取删除节点
        Node target = root.search(value);
        
        //2. 获取删除节点的父节点
        Node parent = root.searchParent(value);
        
        
        //3. 判断此节点是一个什么类型的节点
        /*
         0 代表要删除的节点两个子节点都不为空. (非叶子结点)
         1 代表要删除的节点,有一个子节点  (非叶子结点)
         2 代表要删除的节点没有子节点. (叶子结点)
         */
        int count = 0;
        if (target.left == null) {
            count += 1;
        }
        if (target.right == null) {
            count += 1;
        }
        
        //根据不同的节点类型,执行不同的删除操作
        switch (count) {
            //如果要删除的节点没有子节点.
            case 2: {
                //1. 首先判断它是不是根节点. 是根节点就把它置空即可
                if (target == root) {
                    root = null;
                    return;
                }
                
                //2. 然后再判断target是parent的左还是右子节点
                if (parent.left != null && parent.left.number == target.number) {
                    //是左子节点就把左子节点置空
                    parent.left = null;
                    break;
                }
                if (parent.right != null && parent.right.number == target.number) {
                    //是右子节点就把右子节点置空
                    parent.right = null;
                    break;
                }
                
            }
            
            //如果要删除的节点有1个子节点.
            case 1:
                /*1. 首先要进行判断, 如果要删除的节点是根节点. 且此根节点只有一个子树.
                     那么就需要特殊处理. 因为target的parent为空.会产生空指针异常
                     左子节点不为空,就让左子节点成为新的根节点
                 */
                if (parent == null) {
                    if (target.left != null) {
                        root = target.left;
                        break;
                    }
                    //左子节点是空的,就让右子节点成为新的根节点
                    root = target.right;
                    break;
                    
                }
                
                //2. 其次判断target是parent的左还是右子节点
                //如果target是parent的左子节点,就证明,这个target的所有子节点的值,都比parent小.
                if (parent.left != null && parent.left.number == target.number) {
                    //如果target左子节点为空. 就让 parent.left指向target.right
                    if (target.left == null) {
                        parent.left = target.right;
                    } else {
                        //如果target右子节点为空. 就让parent.left指向target.left
                        parent.left = target.left;
                    }
                    break;
                }
                
                //如果是parent的右子节点,就证明,这个target的所有子节点的值,都比parent大.
                if (parent.right != null && parent.right.number == target.number) {
                    //如果target左子节点为空. 就让 parent.right指向target.right
                    if (target.left == null) {
                        parent.right = target.right;
                    } else {
                        //如果target右子节点为空. 就让parent.right指向target.left
                        parent.right = target.left;
                    }
                    break;
                }
                
                //如果要删除的节点有2个子节点.
            case 0:
                /*
                1. 从target的右子树开始,找到它的右子树中最小的那个节点
                   并且删除最小的子节点. 把最小值获取到
                 */
                //2.然后让target的值改为最小值即可
                target.number = getMinValue(target.right);
                break;
            
            default:
                break;
        }
        
    }
    
    /**
     * 该方法遍历从指定节点作为根节点的一颗平衡二叉树中,
     * 找到最小的节点. 然后删除此节点.
     *
     * @param node 从指定节点开始遍历
     * @return 返回最小的节点
     */
    private int getMinValue(Node node) {
        //创建一个临时表量,存储最小的值
        int temp = node.number;
        
        //循环找到这个树的最后一层的左子节点
        //因为他就是这个树的最小值
        while (node.left != null) {
            node = node.left;
            temp = node.number;
        }
        
        //找到以后. 把这个最小的节点删除
        deleteNode(temp);
        
        //返回最小值
        return temp;
        
        
    }
    
    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root == null) {
            return;
        }
        
        this.root.infixOrder();
    }
    
}


/**
 * 二叉排序树的节点
 */
class Node {
    /**
     * 每个节点的值
     */
    int number;
    
    Node left;
    Node right;
    
    public Node(int number) {
        this.number = number;
    }
    
    
    /**
     * @return 返回以node节点作为根节点的子树的高度
     */
    public int getHeight() {
        //递归遍历这颗树. 计算高度
        return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
    }
    
    /**
     * @return 返回根节点的左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        
        return left.getHeight();
    }
    
    /**
     * @return 返回根节点的右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.getHeight();
    }
    
    
    /**
     * 左旋转AVL数
     */
    public void leftRotate() {
        //1. 创建新节点. 它的值等于当前根节点的值
        Node newNode = new Node(this.number);
        
        //2.把新节点的左子节点设置为当前根节点的左子树
        newNode.left = this.left;
        
        //3.把新节点的右节点,设置为当前根节点的右子节点的左子节点.
        newNode.right = this.right.left;
        
        //4.把当前根节点的值,替换为当前根节点的右子节点的值
        this.number = this.right.number;
        
        //5.把当前根节点的右节点, 设置为当前根节点的右子节点的右子节点.
        this.right = this.right.right;
        
        //6. 把当前根节点的左子节点,设置为新节点
        this.left = newNode;
    }
    
    
    /**
     * 从当前节点开始, 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(this.number);
        
        newNode.right = this.right;
        
        newNode.left = this.left.right;
        this.number = this.right.number;
        this.left = this.left.left;
        this.right = newNode;
        
    }
    
    
    /**
     * 添加一个节点的方法
     *
     * @param node 要添加的节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        
        //1. 首先判断它和当前子树的根节点的大小
        
        //如果当前子树根节点比node值大, node应该放在它左子节点
        if (this.number > node.number) {
            //如果他的左子节点为空, 直接挂上node
            if (this.left == null) {
                this.left = node;
            } else {
                //左子节点不为空, 则递归添加
                this.left.add(node);
            }
            //如果当前子树根节点比node值小, node应该放在它右子节点
        } else {
            //如果他的右子节点为空, 直接挂上node
            if (this.right == null) {
                this.right = node;
            } else {
                //右子节点不为空, 则递归添加
                this.right.add(node);
            }
        }
        
        //添加完一个节点,就要判断是否进行旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                this.left.rightRotate();
            }
            leftRotate();
            return;
        }
        
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (this.left != null && this.left.leftHeight() < this.left.rightHeight()) {
                this.left.leftRotate();
            }
            rightRotate();
        }
        
        
    }
    
    
    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        
        System.out.println(this);
        
        if (this.right != null) {
            this.right.infixOrder();
        }
        
    }
    
    /**
     * 根据提供的值, 查找对应的节点
     *
     * @param value 提供的值
     * @return 返回null代表没找到, 否则返回对应的节点
     */
    public Node search(int value) {
        //如果是当前节点,直接返回
        if (this.number == value) {
            return this;
        }
        
        //如果不是,则分情况
        
        //如果比当前节点值小, 且当前节点左子节点不为空. 就向左递归查找
        if (value < this.number && this.left != null) {
            return this.left.search(value);
        }
        //如果比当前节点值大, 且当前节点右子节点不为空. 就向右递归查找
        if (value >= this.number && this.right != null) {
            return this.right.search(value);
        }
        
        //都不满足,没找到, 就返回null
        return null;
    }
    
    
    /**
     * 获取指定节点的父节点
     *
     * @param value 对应的节点
     * @return 返回value对应的父节点.  没找到就返回null
     */
    public Node searchParent(int value) {
        
        //如果当前节点的左,或者右子节点的值等于给定的值.
        //那么当前节点就是要查找的父节点
        if (this.left != null && this.left.number == value) {
            return this;
        }
        
        if (this.right != null && this.right.number == value) {
            return this;
        }
        
        
        //如果不是,就分情况
        
        //如果要查询的节点的值大于当前节点, 且当前节点右节点不为空.
        //就向右递归查找
        if (value >= this.number && this.right != null) {
            return this.right.searchParent(value);
        }
        
        //如果要查询的节点的值小于当前节点, 且当前节点左子节点不为空.
        //就向左递归查找
        if (value < this.number && this.left != null) {
            return this.left.searchParent(value);
        }
        
        //都不满足, 就没找到. 返回null
        return null;
        
    }
    
    
    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                '}';
    }
    
    
}