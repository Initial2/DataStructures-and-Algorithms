import java.util.Scanner;

/**
 * 数组模拟循环队列, 此方法可存储数据数量= 最大容量-1
 *
 * @author initial
 * @create 2021-03-20 20:59
 */
public class CircleArrayQueueTest1 {
    public static void main(String[] args) {
        CircleArrayQueue2 circleArrayQueue = new CircleArrayQueue2(3);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println();
            System.out.println("请输入你的选择");
            System.out.println("a(add)  g(get) s(show) e(exit)");
            String next = scanner.next();
            switch (next) {
                case "a":
                    System.out.println("请输入要添加的数据: ");
                    circleArrayQueue.add(scanner.nextInt());
                    break;
                case "g":
                    System.out.println("取出的数据为: " + circleArrayQueue.getEle());
                    break;
                case "s":
                    circleArrayQueue.showQueue();
                    break;
                case "e":
                    return;
            }
        }
    }
}

class CircleArrayQueue2 {
    //队列
    private int[] arr;
    //队列最大长度
    private int maxSize;
    //队列头指针
    private int front = 0;
    //队列尾指针
    private int rear = 0;
    //队列中的有效数据个数
    private int eleNum = 0;
    
    
    //初始化队列
    public CircleArrayQueue2(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }
    
    //判断是否为空
    public boolean isEmpty() {
        return eleNum == 0;
    }
    
    //判断是否已满
    public boolean isFull() {
        return eleNum == maxSize - 1;
    }
    
    //添加操作
    public void add(int data) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = data;
        rear = (rear + 1) % maxSize;
        eleNum++;
    }
    
    //取操作
    public int getEle() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        eleNum--;
        return temp;
    }
    
    //提供查看头元素的方法
    public int getHeadEle() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
    
    //提供遍历队列的方法
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("");
        }
        System.out.print("队列内容为: ");
        for (int i = front; i < eleNum + front; i++) {
            System.out.printf("arr[%d] = %d\t", i % maxSize, arr[i % maxSize]);
            
            
        }
    }
}