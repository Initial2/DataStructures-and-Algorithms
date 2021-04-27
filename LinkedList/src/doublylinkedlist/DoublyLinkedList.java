package doublylinkedlist;


/**
 * 双向链表
 *
 * @author initial
 * @create 2021-04-27 12:42
 */
public class DoublyLinkedList<T> {
    
    private Node<T> head = new Node<>(null);
    private int numOfNodes;
    
    /**
     * 链表中添加数据
     * 此添加操作,只能添加到当前链表末尾.
     *
     * @param data 要添加的数据
     */
    public void add(T data) {
        if (isEmpty()) {
            head.data = data;
            head.pre = null;
            numOfNodes++;
            return;
        }
        
        Node<T> tNode = new Node<>(data);
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = tNode;
        tNode.pre = temp;
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
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        //要插入元素的下一个元素位置
        Node<T> newNodeNext = temp.next;
        
        Node<T> newNode = new Node<>(data);
        temp.next = newNode;
        newNode.pre = temp;
        
        newNodeNext.pre = newNode;
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
        
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        
    }
    
    
    private boolean isEmpty() {
        return numOfNodes == 0;
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
        if (index == 0) {
            T data = temp.data;
            head = head.next;
            head.pre = null;
            numOfNodes--;
            return data;
        }
        
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        
        temp.pre.next = temp.next;
        temp.next.pre = temp.pre;
        T data = temp.data;
        numOfNodes--;
        return data;
        
    }
    
    
    static class Node<T> {
        private Node<T> pre;
        private Node<T> next;
        private T data;
        
        public Node(T data) {
            this.data = data;
        }
        
        
        public Node<T> getPre() {
            return pre;
        }
        
        public void setPre(Node<T> pre) {
            this.pre = pre;
        }
        
        public Node<T> getNext() {
            return next;
        }
        
        public void setNext(Node<T> next) {
            this.next = next;
        }
        
        public T getData() {
            return data;
        }
        
        public void setData(T data) {
            this.data = data;
        }
    }
    
    
}
