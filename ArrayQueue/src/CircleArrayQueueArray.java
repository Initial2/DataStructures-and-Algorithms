import javax.management.RuntimeErrorException;
import java.util.Scanner;

/**
 * @author initial
 * @create 2021-03-20 20:41
 */


public class CircleArrayQueueArray {
    public static void main(String[] args) {
        CircleArrayQueue1 queue = new CircleArrayQueue1(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("e:退出程序");
            System.out.println("a:添加数据队列");
            System.out.println("g:从队列取出数据");
            System.out.println("h:查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是:" + res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列头的数据为：" + res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                
                default:
                    break;
            }
        }
    }
}

class CircleArrayQueue1 {
    private int maxsize;
    private int front;
    private int rear;
    private int[] arr;
    
    public CircleArrayQueue1(int arrsize) {
        maxsize = arrsize;
        arr = new int[maxsize];
        front = 0;//指向队列头
        rear = 0;//指向队列尾
    }
    
    public boolean isFull() {
        return (rear + 1) % maxsize == front;
    }
    
    public boolean isEmpty() {
        return rear == front;
    }
    
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        
        arr[rear] = n;
        rear = (rear + 1) % maxsize;
    }
    
    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列已满");
        }
        int value = arr[front];
        front = (front + 1) % maxsize;//后移
        return value;
    }
    
    public int size() {
        return (rear + maxsize - front) % maxsize;
    }
    
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxsize, arr[i % maxsize]);
            
        }
    }
    
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeErrorException(null, "队列为空，没有数据");
        }
        return arr[front + 1];
    }
}

