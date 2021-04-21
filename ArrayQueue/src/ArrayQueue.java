/**
 * @author initial
 * @create 2021-04-14 18:51
 */
public class ArrayQueue {
    
    //队列
    int[] arr;
    //队列最大长度
    private int maxSize;
    //队列头指针
    private int front = -1;
    //队列尾指针
    private int rear = -1;
    
    //提供构造方法, 初始化队列
    public ArrayQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("输入的长度不合法");
        }
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }
    
    //提供判断队列是否为空的方法
    public boolean isEmpty() {
        return front == rear;
    }
    
    //提供判断队列是否已满的方法
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    
    //提供添加数据的方法add(),向队列中添加数据
    public boolean add(int data) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        /*
        每次先把尾指针rear后移一位. 再添加
         */
        arr[++rear] = data;
        return true;
    }
    
    //提供队列取出数据的方法, 只能取出队列中的首元素
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        
        /*
        因为front指向的是头元素的前一个位置,所以每次取数据先让 front+1,跳到队列的头元素
         */
        return arr[++front];
    }
    
    //提供查询队列首个元素的方法
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        /*
        因为只是查头元素, 并不是取. 所以front只需要+1 跳到首元素位置即可.
        注意:
              这里的 front + 1 只是查询第 front + 1 元素的位置
               他并不会改变front的值. 所以执行查询之后,front的值并没有变
         */
        return arr[front + 1];
    }
    
    
}
