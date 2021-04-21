import java.util.Scanner;

/**
 * 数组模拟环形队列  此方法实际容量为最大容量
 * 可以重复使用
 *
 * @author initial
 * @create 2021-03-20 17:28
 */
public class CircleArrayQueueTest {
    
    public static void main(String[] args) {
        
        CircleArrayQueue<String> circleArrayQueue = new CircleArrayQueue<>(3);
        Scanner scanner = new Scanner(System.in);
        
        circleArrayQueue.add("abcd");
        circleArrayQueue.add("abcd");
        
        circleArrayQueue.add("efg");
        
        
        System.out.println(circleArrayQueue.getHeadEle());
        System.out.println(circleArrayQueue.getEle());
        circleArrayQueue.showQueue();
        
        
    }
}

/**
 * 数组模拟环形队列实现
 */
class CircleArrayQueue<T> {
    //队列
    private final T[] arr;
    //队列最大长度
    private final int maxSize;
    //队列头指针
    private int front = 0;
    //队列尾指针
    private int rear = 0;
    //队列中的有效数据个数
    private int eleNum = 0;
    
    //初始化队列
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        
        //根据提供的泛型, 创建对应类型的数组
        arr = (T[]) new Object[maxSize];
    }
    
    //判断是否为空
    public boolean isEmpty() {
        return eleNum == 0;
    }
    
    //判断是否已满
    public boolean isFull() {
        //有效元素=最大容量 那么就存满了
        return eleNum == maxSize;
    }
    
    //添加操作
    public boolean add(T data) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = data;
        /*
        (rear +1) % maxSize.  如果rear此时指向队列最后一个位置.
        那么 rear + 1 就等于maxSize . 此时(rear +1) % maxSize 就等于0
        那么就相当于,自动的把rear移动到队列最开始的地方了.
        相当于一个循环操作.
         */
        rear = (rear + 1) % maxSize;
        eleNum++;
        return true;
    }
    
    //取操作
    public T getEle() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        //创建临时变量存储对应front位置的数据
        T temp = arr[front];
        //取出后, 把此位置的元素赋默认值.
        arr[front] = (T) new Object();
        /*
         然后front该指向下一个元素.
         (front + 1) % maxSize;和前面有些类似. 都是当front已经指向了队列最后一个元素
         (front + 1) % maxSize 就可以把front重新指回队列最开始的位置0.
         相当于循环操作
         */
        front = (front + 1) % maxSize;
        eleNum--;
        return temp;
    }
    
    //提供查看头元素的方法
    public T getHeadEle() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
    
    //提供遍历队列的方法
    public void showQueue() {
        if (isEmpty()) {
            System.out.println();
        }
        System.out.print("队列内容为: ");
        
        /*
        从front位置开始, 有几个有效元素就遍历几次
        arr[i % maxSize] , 当i > maxSize时, i % maxSize
        就可以把索引重新调回队列的开始位置
         */
        for (int i = front; i < eleNum + front; i++) {
            System.out.print(arr[i % maxSize] + "\t");
            
        }
    }
    
}
