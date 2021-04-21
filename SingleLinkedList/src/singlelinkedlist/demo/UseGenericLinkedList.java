package singlelinkedlist.demo;

/**
 * 使用泛型结构的单链表
 * <p>
 * 这里设计的是带表头的单链表.
 *
 * @author Inital
 */
public class UseGenericLinkedList<T> {
    
    
    public Node<T> head = new Node<>();
    private int length = 0;
    private Node<T> temp = null;
    
    /**
     * 提供判断链表是否为空的方法
     */
    public boolean isEmpty() {
        return head.next == null;
    }
    
    /**
     * 获取当前链表的长度
     */
    public int getLength() {
        return length;
    }
    
    /**
     * 提供添加节点的方法
     *
     * @param data 需要添加的节点
     */
    public void add(T data) {
        if (data == null) {
            return;
        }
        
        // 把需要添加的数据,给打包成一个Node.
        Node<T> tNode = new Node<>(data);
        temp = head;
        if (length == 0) {
            temp.next = tNode;
            length++;
            return;
        }
        while (true) {
            if (temp.next == null) {
                temp.next = tNode;
                length++;
                break;
            }
            temp = temp.next;
        }
        
    }
    
    /**
     * 提供删除指定位置节点的方法
     *
     * @param index 指定删除的位置
     */
    public boolean delete(int index) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        if (index == 0) {
            head.next = head.next.next;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                temp.next = temp.next.next;
                length--;
                return true;
            }
            temp = temp.next;
            
        }
        return false;
    }
    
    /**
     * 提供修改指定位置节点的方法
     *
     * @param index 指定需要修改的节点的位置
     * @param data  需要覆盖的新的数据
     */
    public boolean set(int index, T data) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        // 把需要添加的数据,给打包成一个Node.
        Node<T> tNode = new Node<>(data);
        
        if (index == 0) {
            tNode.next = head.next.next;
            head.next = tNode;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                tNode.next = temp.next.next;
                temp.next = tNode;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * 提供插入节点的方法
     *
     * @param index 指定需要插入的节点的位置
     * @param data  需要插入的新的数据
     */
    public boolean insert(int index, T data) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        Node<T> tNode = new Node<>(data);
        
        if (index == 0) {
            tNode.next = head.next;
            head.next = tNode;
            length++;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                tNode.next = temp.next;
                temp.next = tNode;
                length++;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * 提供一个查询的方法
     *
     * @return Node<T> 返回要查询位置的节点
     */
    public Node<T> get(int index) {
        if (length == 0) {
            return null;
        }
        
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
            if (i == index) {
                return temp;
            }
        }
        return null;
    }
    
    /**
     * 提供遍历链表的方法.
     * 此方法打印链表中所有节点的信息
     */
    public void showList() {
        if (isEmpty()) {
            return;
        }
        Node<T> temp = head.next;
        while (true) {
            if (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            } else {
                return;
            }
        }
        
        
    }
    
    /**
     * 节点类. 根据提供的类型, 创建对应的节点
     *
     * @param <E>
     */
    public class Node<E> {
        
        public E data;
        public Node<E> next = null;
        
        public Node(E data) {
            this.data = data;
        }
        
        public Node() {
        }
        
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
    
    
}

