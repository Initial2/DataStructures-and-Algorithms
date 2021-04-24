package linkedlist.queue;


/**
 * 链表模拟队列
 * 使用单向环形列表
 *
 * @author initial
 * @create 2021-04-24 16:29
 */
public class LinkedListQueue<T> {
    
    /**
     * 队列长度
     */
    private final int QueueSize;
    
    /**
     * 链表头指针
     */
    private final Node<T> head = new Node<>(null);
    
    
    /**
     * 队列头指针
     */
    private Node<T> front;
    
    /**
     * 队列尾指针
     */
    private Node<T> rear;
    
    /**
     * 队列元素个数
     */
    private int numOfElements;
    
    /**
     * 初始化队列
     *
     * @param queueSize 指定队列长度,该长度必须>0
     */
    public LinkedListQueue(int queueSize) {
        if (queueSize <= 0) {
            throw new RuntimeException("请输入合法的长度.");
        }
        
        QueueSize = queueSize;
        numOfElements = 0;
        Node<T> temp = head;
        
        //根据指定长度,先把队列创建出来
        for (int i = 0; i < queueSize; i++) {
            if (i == queueSize - 1) {
                temp.next = head;
                break;
            }
            temp.next = new Node<>(null);
            temp = temp.next;
        }
        
        //初始化front 和 rear的位置.
        rear = head;
        front = head;
    }
    
    
    /**
     * 判断队列是否为空
     *
     * @return 为空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return numOfElements == 0;
    }
    
    
    /**
     * 向队列中添加数据
     *
     * @param data 要添加的元素
     */
    public void insert(T data) {
        if (numOfElements == QueueSize) {
            throw new RuntimeException("队列已满,无法添加.");
        }
        rear.data = data;
        rear = rear.next;
        numOfElements++;
    }
    
    
    /**
     * 取出队列中的元素
     */
    public T pop() {
        //首先判断队列是否已经空了
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法删除元素.");
        }
        T data = front.data;
        front.data = null;
        front = front.next;
        numOfElements--;
        return data;
    }
    
    /**
     * 清空当前队列
     */
    public void clear() {
        numOfElements = 0;
        Node<T> temp = head;
        for (int i = 0; i < QueueSize; i++) {
            if (i == QueueSize - 1) {
                temp.next = head;
                break;
            }
            temp.next = new Node<>(null);
            temp = temp.next;
        }
        rear = head;
        front = head;
    }
    
    
    /**
     * 按顺序打印当前队列中所有的元素
     */
    public void show() {
        //有几个元素,就往后打印几次.
        Node<T> temp = front;
        for (int i = 0; i < numOfElements; i++) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        
    }
    
    /**
     * 看看当前队列头部是什么元素
     *
     * @return 当前队列头部是什么元素
     */
    public T peek() {
        return front.data;
    }
    
    static class Node<T> {
        private Node<T> next;
        private T data;
        
        private Node(T data) {
            this.data = data;
        }
    }
    
}
