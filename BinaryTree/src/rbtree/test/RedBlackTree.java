package rbtree.test;


/**
 * 红黑树的实现
 *
 * @author initial
 * @create 2021-04-12 19:38
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node<K, V> root;
    
    
    public Node<K, V> getRoot() {
        return root;
    }
    
    /**
     * 判断当前节点的颜色,是否为红色
     *
     * @param node 需要判断的节点
     * @return true为红色, false为黑色
     */
    private boolean isRed(Node<K, V> node) {
        return node != null && node.color == RED;
    }
    
    
    /**
     * 把当前节点改为红色
     *
     * @param node 指定的节点
     */
    private void setRed(Node<K, V> node) {
        if (node == null) {
            return;
        }
        node.color = RED;
    }
    
    /**
     * 把当前节点改为黑色
     *
     * @param node 指定的节点
     */
    private void setBlack(Node<K, V> node) {
        if (node == null) {
            return;
        }
        node.color = BLACK;
    }
    
    /**
     * 获取当前节点的父节点
     *
     * @param node 当前节点
     * @return 返回当前节点的父节点
     */
    private Node<K, V> getParent(Node<K, V> node) {
        return node == null ? null : node.parent;
    }
    
    
    /**
     * 左旋红黑树
     * 假设要以x节点为根节点,旋转它和它的子树.
     * y为x节点的右子节点. 也就是当前根节点的右子节点.
     * 思路:
     * 1. 将x的右子节点设置为y的左子节点
     * 2. 将y的左子节点的父节点设置为x
     * 3. 当x的父节点不为空时, 把y的父节点设置为x的父节点
     * 4. 判断x是在它的父节点的哪一边. 把y挂到和x相同的一边.
     * 5. 把x的父节点,重新设置为y.
     * 6. 把y的左子节点设置为x.
     */
    private void leftRotate(Node<K, V> x) {
        Node<K, V> y = x.right;
        
        //1: 将x的右子节点设置为y的左子节点
        x.right = y.left;
        
        //2:将y的左子节点的父节点设置为x
        if (y.left != null) {
            y.left.parent = x;
        }
        
        
        //3. 当x的父节点不为空时, 把y的父节点设置为x的父节点
        if (x.parent != null) {
            y.parent = x.parent;
            //4.  判断x是在它的父节点的哪一边. 把y挂到和x相同的一边.
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            //如果x没有父节点,证明x是根节点. 旋转过后y就变成了根节点
            //重新把根节点设置成y即可
            root = y;
            root.parent = null;
        }
        
        //5. 把x的父节点,重新设置为y.
        x.parent = y;
        //6. 把y的左子节点设置为x.
        y.left = x;
        
    }
    
    
    /**
     * 右旋转
     * 思路大致和左旋转相同
     *
     * @param y 要开始旋转的节点
     */
    private void rightRotate(Node<K, V> y) {
        Node<K, V> x = y.left;
        
        //1. 先让y的左节点指向x的右节点
        y.left = x.right;
        //2.然后xy的右节点的父节点为y
        if (x.right != null) {
            x.right.parent = y;
        }
        
        //3.当y的父节点不为空时, 让x的父节点等于y的父节点
        if (y.parent != null) {
            x.parent = y.parent;
            //4.  判断y是在它的父节点的哪一边. 把x挂到和y相同的一边.
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            //如果y没有父节点,证明y是根节点. 旋转过后x就变成了根节点
            //重新把根节点设置成x即可
            root = x;
            root.parent = null;
        }
        //5. 把y的父节点,重新设置为x.
        y.parent = x;
        //6. 把x的左子节点设置为y.
        x.right = y;
        
    }
    
    
    /**
     * 向红黑树中插入一条元素.
     *
     * @param key   键
     * @param value 值
     */
    public void insert(K key, V value) {
        Node<K, V> kvNode = new Node<K, V>();
        kvNode.key = key;
        kvNode.value = value;
        //注意: 新添加的节点一定是红结点!!!
        kvNode.color = RED;
        insert(kvNode);
    }
    
    
    private void insert(Node<K, V> node) {
        //要插入节点的父节点
        Node<K, V> parent = null;
        
        //创建一个辅助指针,遍历红黑树
        Node<K, V> temp = root;
        while (temp != null) {
            parent = temp;
            //通过比较key值,开始确定应该插入的位置
            int cmp = node.key.compareTo(temp.key);
            if (cmp > 0) {
                //要插入的元素的key比当前节点的key大. 向右边走
                temp = temp.right;
            } else if (cmp == 0) {
                //key相同就进行替换,把value设置为新的值. 然后直接返回
                temp.value = node.value;
                return;
            } else {
                //要插入的元素的key比当前节点的key小. 向左边走
                temp = temp.left;
            }
            
        }
        
        //到此我们就已经找到了要插入元素的父节点
        //让要插入的node的父节点指向parent即可
        node.parent = parent;
        
        //接下来判断要插入的节点该挂在父节点左边还是右边
        if (parent != null) {
            //还是比较key的值
            int cmp = node.key.compareTo(parent.key);
            //这里没有==0的情况. 之前已经进行过替换了
            if (cmp > 0) {
                // node的key > parent的key, 挂在parent右边
                parent.right = node;
            } else {
                // node的key < parent的key, 挂在parent左边
                parent.left = node;
            }
        } else {
            //如果parent == null 证明是第一次添加. 就让它作为根节点即可
            root = node;
            root.color = BLACK;
        }
        
        //最后, 当我们插入一个节点之后. 原来的红黑树可能被破坏.
        //需要重新进行修整
        insertFixUp(node);
        
        
    }
    
    /**
     * 插入修正. 当我们插入一个节点之后. 原来的红黑树可能被破坏.
     * 需要重新进行平衡化
     *
     * @param node 从要插入的节点开始平衡化
     */
    private void insertFixUp(Node<K, V> node) {
        //1. 如果是首次插入. 就要把根节点设置为黑色
        this.root.color = BLACK;
        
        //2. 只有当要插入的节点的父节点为红色. 我们才要采取对应措施
        Node<K, V> parent = getParent(node);
        
        //插入节点的爷爷节点. 注意: 如果父节点为红色,那么就一定有爷爷节点.
        Node<K, V> gparent = getParent(parent);
        
        
        //如果父节点存在,并且为红结点,我们才采取措施
        if (isRed(parent)) {
            
            //插入节点的叔叔节点
            Node<K, V> uncle;
            
            //情景4.1: 父节点为爷爷节点的左子树,叔叔节点为爷爷节点的右子树
            if (parent == gparent.getLeft()) {
                //叔叔节点为爷爷节点的哪一边. 父为左,叔叔为右, 反之.
                uncle = gparent.getRight();
                
                //情景4.1: 叔叔节点存在,并且为红色 (父-叔双红)
                if (isRed(uncle)) {
                    //把父节点和叔叔节点染成黑色,
                    setBlack(parent);
                    setBlack(uncle);
                    //把爷爷节点染成红色
                    setRed(gparent);
                    //然后以爷爷节点为当前节点,递归修正
                    insertFixUp(gparent);
                    return;
                }
                
                
                //4.2: 叔叔节点不存在. 或者为黑色. 此时又要分情况
                if (uncle == null || isBlack(uncle)) {
                    //4.2.1: 插入节点为父节点的左子节点.
                    if (node == parent.getLeft()) {
                        //把父节点染成黑色,爷爷节点染成红色
                        setBlack(parent);
                        setRed(gparent);
                        //然后以爷爷节点进行右旋即可
                        rightRotate(gparent);
                        return;
                    }
                    
                    //4.2.2: 插入节点为父节点的右子节点.
                    if (node == parent.getRight()) {
                        //先以父节点进行一次左旋
                        leftRotate(parent);
                        //然后递归修正
                        insertFixUp(parent);
                    }
                    
                }
                
            } else {
                
                uncle = gparent.left;
                //情景4.1: 叔叔节点存在,并且为红色 (父-叔 双红)
                if (isRed(uncle)) {
                    //把父节点和叔叔节点染成黑色,
                    setBlack(parent);
                    setBlack(uncle);
                    //把爷爷节点染成红色
                    setRed(gparent);
                    //然后以爷爷节点为当前节点,递归修正
                    insertFixUp(gparent);
                    return;
                }
                
                //4.3: 父节点为爷爷节点的右子树. 叔叔节点不存在,或者为黑色
                if (uncle == null || isBlack(uncle)) {
                    //4.3.1 : 插入节点为父节点的右子节点
                    if (node == parent.right) {
                        //把父节点染成黑色,爷爷染成红色. 进行左旋
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }
                    
                    //4.3.2 : 插入节点为父节点的左子节点
                    if (node == parent.left) {
                        //以父节点进行一次左旋
                        rightRotate(parent);
                        insertFixUp(parent);
                    }
                }
            }
            
            
        }
    }
    
    private boolean isBlack(Node<K, V> uncle) {
        return uncle != null && uncle.color == BLACK;
    }
    
    
    /**
     * 中序遍历
     */
    public void infixOrder() {
        infixOrder(root);
    }
    
    private void infixOrder(Node<K, V> node) {
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.println("key:" + node.key + "value:" + node.value);
        if (node.right != null) {
            infixOrder(node.right);
        }
        
    }
    
    
    /**
     * 红黑树的节点
     * 继承了Comparable类. 所以具有比较的功能
     */
    static class Node<K extends Comparable<K>, V> {
        
        /**
         * left 当前节点的左子节点
         * right 当前节点的右子节点
         * parent 当前节点的父节点
         * color   当前节点的颜色 true为红色,false为黑色
         * key 当前节点的键
         * value 当前节点的值
         */
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private boolean color;
        private K key;
        private V value;
        
        
        public Node() {
        }
        
        public Node(Node<K, V> left, Node<K, V> right, Node<K, V> parent, boolean color, K key, V value) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
            this.key = key;
            this.value = value;
        }
        
        public Node<K, V> getLeft() {
            return left;
        }
        
        public void setLeft(Node<K, V> left) {
            this.left = left;
        }
        
        public Node<K, V> getRight() {
            return right;
        }
        
        public void setRight(Node<K, V> right) {
            this.right = right;
        }
        
        public Node<K, V> getParent() {
            return parent;
        }
        
        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }
        
        public boolean isColor() {
            return color;
        }
        
        public void setColor(boolean color) {
            this.color = color;
        }
        
        public K getKey() {
            return key;
        }
        
        public void setKey(K key) {
            this.key = key;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
        
        
    }
    
    
}
