package circylarlinkedlists;

import org.junit.jupiter.api.Test;


/**
 * @author initial
 * @create 2021-04-26 18:05
 */
public class CircularLinkedListTest {
    @Test
    public void test() {
        CircularLinkedLists<Integer> list = new CircularLinkedLists<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        list.remove(4);
        list.insert(0, -1);
        list.showList();
        
    }
    
}
