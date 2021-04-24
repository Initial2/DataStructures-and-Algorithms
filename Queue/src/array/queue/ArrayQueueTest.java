package array.queue;


/**
 * 数组模拟队列
 *
 * @author initial
 * @create 2021-03-20 15:46
 */

public class ArrayQueueTest {
    
    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(3);
    
        arrayQueue.insert(5);
        arrayQueue.insert(6);
        arrayQueue.insert(7);
    
        Integer pop = arrayQueue.pop();
        Integer pop1 = arrayQueue.pop();
    
        System.out.println("取出的数据为:" + pop);
        System.out.println("取出的数据为:" + pop1);
    
        arrayQueue.clear();
    
        arrayQueue.insert(5);
        arrayQueue.insert(6);
    
        System.out.println("=====");
        arrayQueue.show();
    
        System.out.println(arrayQueue.peek());
    
    }
    
}

