package singlelinkedlist.demo;

import org.junit.jupiter.api.Test;


/**
 * 使用泛型结构的单链表. 测试
 *
 * @author initial
 * @create 2021-03-21 18:04
 */

public class UseGenericLinkedListTest {
    
    @Test
    public UseGenericLinkedList<Integer> test1() {
        UseGenericLinkedList<Integer> integerList = new UseGenericLinkedList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(3);
        integerList.add(3);
        return integerList;
        
    }
}



