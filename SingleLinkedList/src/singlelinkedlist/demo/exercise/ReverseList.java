package singlelinkedlist.demo.exercise;

import singlelinkedlist.demo.UseGenericLinkedList;

import java.util.Stack;

/**
 * 将给定链表翻转.
 * 要求: 不能打破原有链表的结构.
 * 用一个新的链表来存放反转的原有链表
 * <p>
 * 方法:  可以使用Stack栈的 先进后出思想
 *
 * @author initial
 * @create 2021-03-23 10:06
 */
public class ReverseList<T> {
    
    public static void main(String[] args) {
        UseGenericLinkedList<Integer> oldList = new UseGenericLinkedList<>();
        oldList.add(1);
        oldList.add(2);
        oldList.add(3);
        oldList.add(4);
        oldList.add(5);
        reverseList(oldList);
    }
    
    public static void reverseList(UseGenericLinkedList<Integer> oldList) {
        //新建一个栈
        Stack<Integer> stack = new Stack<>();
        
        //获取需要反转的链表的首个节点
        UseGenericLinkedList<Integer>.Node<Integer> temp = oldList.head.next;
        
        //遍历需要反转的链表
        while (temp != null) {
            //把oldList中每一个节点的数据,存放到栈中
            stack.add(temp.data);
            temp = temp.next;
        }
        
        //新建一个链表
        UseGenericLinkedList<Integer> newList = new UseGenericLinkedList<>();
        
        //把栈中的每一个元素打包成节点添加到newList中
        while (stack.size() > 0) {
            newList.add(stack.pop());
        }
        
        newList.showList();
        System.out.println();
        oldList.showList();
        
    }
    
}
