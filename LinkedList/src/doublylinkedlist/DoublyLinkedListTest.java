package doublylinkedlist;

import org.junit.jupiter.api.Test;

/**
 * @author initial
 * @create 2021-04-27 12:52
 */
public class DoublyLinkedListTest {
    @Test
    public void test() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        
        
        list.remove(1);
        list.showList();
    }
}
