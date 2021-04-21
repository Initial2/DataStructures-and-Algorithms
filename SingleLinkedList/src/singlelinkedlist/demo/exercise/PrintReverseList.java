package singlelinkedlist.demo.exercise;

import singlelinkedlist.demo.UseGenericLinkedList;
import singlelinkedlist.demo.UseGenericLinkedListTest;


/**
 * 反转单链表
 *
 * @author initial
 * @create 2021-03-22 17:29
 */
public class PrintReverseList {
    
    
    public static void main(String[] args) {
        
        UseGenericLinkedList<Integer> list = new UseGenericLinkedListTest().test1();
        solution(list);
    }
    
    
    /**
     * 给一个链表,把这个链表给倒序存储起来
     *
     * @param list 给定的链表
     */
    public static void solution(UseGenericLinkedList<Integer> list) {
        
        //获取要反转的链表的首个结点
        UseGenericLinkedList<Integer>.Node<Integer> current = list.head.next;
        
        //用来存放current下一个节点的位置
        UseGenericLinkedList<Integer>.Node<Integer> currentNext = null;
        
        //新建一个链表
        UseGenericLinkedList<Integer> newList = new UseGenericLinkedList<>();
        
        //获取这个链表的头指针
        UseGenericLinkedList<Integer>.Node<Integer> newHead = newList.head;
        
        
        while (current != null) {
            //首先, 把当前节点的下一个节点地址记录下来, 防止链表断裂丢失
            currentNext = current.next;
            
            //把当前节点的下一个位置指向链表中的第一个节点实现反转操作
            current.next = newHead.next;
            //当前节点指向cur
            newHead.next = current;
            //添加完成, cur后移一位
            current = currentNext;
        }
        
        newList.showList();
        System.out.println();
        list.showList();
        
    }
}
