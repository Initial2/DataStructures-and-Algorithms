package singlylinkedlist;

/**
 * 单向链表的实现
 * 此链表带头指针,头指针的next指向链表的第一个元素
 * head.next = 链表第一个元素.
 *
 * @author initial
 * @create 2021-04-25 17:58
 */
public class SinglyLinkedList<T> {
    
    private final Node<T> head;
    private int numOfNodes = 0;
    
    public SinglyLinkedList() {
        head = new Node<>(null);
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
        Node<T> tNode = new Node<T>(data);
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = tNode;
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
        
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
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
        
        Node<T> temp = head.next;
        while (temp != null) {
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
    public T delete(int index) {
        if (index < 0 && index > numOfNodes) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        T data = temp.next.data;
        temp.next = temp.next.next;
        return data;
        
    }
    
    
    /**
     * 清空当前链表所有元素
     */
    public void clear() {
        head.next = null;
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
    
    
    static class Node<T> {
        private final T data;
        private Node<T> next;
        
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
