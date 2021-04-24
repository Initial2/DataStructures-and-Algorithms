package linkedlist.queue;

import org.junit.jupiter.api.Test;

/**
 * @author initial
 * @create 2021-04-24 16:48
 */
public class LinkedListQueueTest {
    @Test
    public void test() {
        LinkedListQueue<Integer> listQueue = new LinkedListQueue<>(7);
        listQueue.insert(5);
        listQueue.insert(6);
        listQueue.insert(7);
        
        listQueue.clear();
        
        listQueue.insert(8);
        listQueue.insert(9);
        
        System.out.println(listQueue.peek());
        listQueue.show();
        
    }
}
