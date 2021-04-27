package singlylinkedlist;

import org.junit.jupiter.api.Test;

/**
 * @author initial
 * @create 2021-04-25 18:04
 */
public class SinglyLinkedListTest {
    @Test
    public void test() {
    
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        
        list.insert(2, 6);
        list.showList();
        System.out.println();
        list.delete(2);
        list.showList();
    }
}
