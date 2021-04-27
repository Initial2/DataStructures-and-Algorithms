package circylarlinkedlists;


/**
 * 单向循环链表
 *
 * @author initial
 * @create 2021-04-26 17:55
 */
public class CircularLinkedLists<T> {
    private Node<T> head;
    private int numOfNodes;
    
    
    public CircularLinkedLists() {
        head = new Node<>(null);
        numOfNodes = 0;
    }
    
    public Node<T> getHead() {
        return head;
    }
    
    
    /**
     * 链表中添加数据
     * 此添加操作,只能添加到当前链表末尾.
     *
     * @param data 要添加的数据
     */
    public void add(T data) {
        if (isEmpty()) {
            head.data = data;
            numOfNodes++;
            return;
        }
        
        Node<T> tNode = new Node<>(data);
        Node<T> temp = head;
        while (temp.next != null && temp.next != head) {
            temp = temp.next;
        }
        temp.next = tNode;
        tNode.next = head;
        numOfNodes++;
    }
    
    
    /**
     * 在指定位置插入新的元素.
     *
     * @param index 要插入的元素在链表中的位置.
     * @param data  插入的数据
     */
    public void insert(int index, T data) {
        if (index < 0 && index > numOfNodes) {
            throw new IndexOutOfBoundsException();
        }
        
        //如果是在0位置插入,就要更改head.
        if (index == 0) {
            Node<T> tNode = new Node<>(data);
            Node<T> temp = head;
            while (temp.next != null && temp.next != head) {
                temp = temp.next;
            }
            temp.next = tNode;
            tNode.next = head;
            head = tNode;
            return;
        }
        
        
        Node<T> temp = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        
        Node<T> newNodeNext = temp.next;
        Node<T> newNode = new Node<>(data);
        temp.next = newNode;
        newNode.next = newNodeNext;
        numOfNodes++;
        
    }
    
    /**
     * 打印当前链表所有元素
     */
    public void showList() {
        if (head.next == null) {
            return;
        }
        System.out.println(head.data);
        Node<T> temp = head.next;
        
        while (temp != null && temp != head) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        
    }
    
    
    /**
     * 删除元素
     *
     * @param index 指定索引位置
     * @return 返回删除的元素
     */
    public T remove(int index) {
        if (index < 0 && index > numOfNodes) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> temp = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        T data = temp.next.data;
        temp.next = temp.next.next;
        numOfNodes--;
        return data;
        
    }
    
    
    /**
     * 清空当前链表所有元素
     */
    public void clear() {
        head.next = null;
        head.data = null;
        numOfNodes = 0;
    }
    
    
    /**
     * 返回当前链表的元素个数
     *
     * @return 当前链表的元素个数
     */
    public int size() {
        return numOfNodes;
    }
    
    /**
     * 判断当前链表是否为空
     *
     * @return 为空返回true. 否则返回-1.
     */
    public boolean isEmpty() {
        return numOfNodes == 0;
    }
    
    
    static class Node<T> {
        private T data;
        public Node<T> next;
        
        public Node(T data) {
            this.data = data;
        }
        
        
        public Node<T> getNext() {
            return next;
        }
        
        public T getData() {
            return data;
        }
    }
    
}
