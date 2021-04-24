package array.queue;

/**
 * 数组模拟队列
 *
 * @author initial
 * @create 2021-04-14 18:51
 */
public class ArrayQueue<T> {
    
    /**
     * 队列大小
     */
    private final int QueueSize;
    
    /**
     * 数组队列
     */
    private T[] arrayQueue;
    
    /**
     * 队列中元素的个数
     */
    private int numOfElements;
    
    /**
     * 队列头部指针
     */
    private int front;
    
    /**
     * 队列尾部指针
     */
    private int rear;
    
    
    /**
     * 初始化队列
     *
     * @param length 指定队列的长度
     */
    public ArrayQueue(int length) {
        QueueSize = length;
        arrayQueue = (T[]) new Object[length];
        front = 0;
        rear = 0;
    }
    
    
    /**
     * 向队列中添加元素
     *
     * @param value 要添加的元素
     * @return 添加成功返回true
     */
    public boolean insert(T value) {
        //1. 首先要判断队列是否已满
        if (numOfElements == QueueSize) {
            throw new RuntimeException("队列已满,无法添加元素.");
        }
        
        arrayQueue[rear] = value;
        rear = (rear + 1) % QueueSize;
        numOfElements++;
        return true;
    }
    
    
    /**
     * 取出队列中的元素
     */
    public T pop() {
        //首先判断队列是否已经空了
        if (isEmpty()) {
            throw new RuntimeException("队列为空,无法删除元素.");
        }
        T popData = arrayQueue[front];
        
        arrayQueue[front] = null;
        front = (front + 1) % QueueSize;
        numOfElements--;
        return popData;
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
     * 获取当前队列中元素的个数
     *
     * @return 返回当前队列中元素的个数
     */
    public int getQueueSize() {
        return numOfElements;
    }
    
    
    /**
     * 清空当前队列
     */
    public void clear() {
        arrayQueue = (T[]) new Object[QueueSize];
        front = 0;
        rear = 0;
        numOfElements = 0;
    }
    
    
    /**
     * 查看当前队列头部元素是什么
     *
     * @return T  返回当前队列头部元素
     */
    public T peek() {
        return arrayQueue[front];
    }
    
    
    /**
     * 按顺序打印当前队列中所有的元素
     */
    public void show() {
        if (numOfElements == 0) {
            System.out.println("队列为空.");
            return;
        }
        
        //头指针为front, 元素个数numOfElements. 所以需要遍历 front + numOfElements次.
        for (int i = front; i < numOfElements + front; i++) {
            //i % QueueSize 考虑环形打印情况.
            System.out.println(arrayQueue[i % QueueSize]);
            
        }
    }
    
}
