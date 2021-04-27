package circylarlinkedlists;

import org.junit.jupiter.api.Test;

/**
 * 单向循环链表应用:
 * 约瑟夫问题
 *
 * @author initial
 * @create 2021-04-27 12:01
 */
public class JosephuTest {
    
    @Test
    public void test() {
        josephu(5, 0, 4);
    }
    
    
    /**
     * 约瑟夫问题实现
     *
     * @param length 链表的长度
     * @param start  从哪个节点开始  start=1 就证明从1这个节点开始.
     * @param step   每次步长是多少
     */
    public void josephu(int length, int start, int step) {
        //1. 创建链表
        CircularLinkedLists<Integer> list = new CircularLinkedLists<>();
        
        for (int i = 1; i <= length; i++) {
            list.add(i);
        }
        
        //2. 找到开始报数的节点
        CircularLinkedLists.Node<Integer> startNode = list.getHead();
        while (start > 1) {
            startNode = startNode.getNext();
            start--;
        }
        
        if (start == 0) {
            startNode = list.getHead();
        }
        
        //3. 找到开始报数节点的上一个节点
        CircularLinkedLists.Node<Integer> preNode = list.getHead();
        while (preNode.getNext() != null && preNode.getNext() != startNode) {
            preNode = preNode.getNext();
        }
        
        //4.开始处理
        while (length > 0) {
            
            for (int i = 1; i <= step; i++) {
                startNode = startNode.getNext();
                preNode = preNode.getNext();
            }
            
            System.out.println(startNode.getData());
            startNode = startNode.getNext();
            preNode.next = startNode;
            length--;
            
        }
    }
}
