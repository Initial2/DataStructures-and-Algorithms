package singlelinkedlist.demo.exercise;

import singlelinkedlist.demo.UseGenericLinkedList;

/**
 * 要求:
 * <p>
 * 合并两个有序链表, 合并以后新链表依然有序
 *
 * @author initial
 * @create 2021-03-23 10:43
 */
public class CombonTowList {
    
    public static void main(String[] args) {
        UseGenericLinkedList<Integer> list1 = new UseGenericLinkedList<>();
        list1.add(3);
        list1.add(4);
        list1.add(7);
        list1.add(8);
        list1.add(10);
        
        
        UseGenericLinkedList<Integer> list2 = new UseGenericLinkedList<>();
        list2.add(2);
        list2.add(5);
        list2.add(6);
        list2.add(11);
        
        
        solution(list1, list2);
        
    }
    
    public static void solution(UseGenericLinkedList<Integer> list1, UseGenericLinkedList<Integer> list2) {
        
        //分别获取这两个链表的首个节点
        UseGenericLinkedList<Integer>.Node<Integer> cur1 = list1.head.next;
        UseGenericLinkedList<Integer>.Node<Integer> cur2 = list2.head.next;
        
        //新建一个链表,用来存放合并后的节点
        UseGenericLinkedList<Integer> newList = new UseGenericLinkedList<>();
        //获取新链表的头指针
        UseGenericLinkedList<Integer>.Node<Integer> temp = newList.head;
        
        //开始遍历
        
        /*
         * 循环终止条件:  如果这两个链表其中任何一个遍历到头了,就结束
         */
        while (cur1 != null && cur2 != null) {
            // 如果cur1的值 < cur2的值 那么就让新链表的节点指向cur1.
            // 然后让cur1后移, 比较cur1的下一个节点.
            if (cur1.data < cur2.data) {
                temp.next = cur1;
                cur1 = cur1.next;
            } else {
                // 如果cur1的值 >= cur2的值 那么就让新链表的节点指向cur2.
                // 然后让cur2后移, 比较cur2的下一个节点.
                temp.next = cur2;
                cur2 = cur2.next;
            }
            //新链表每添加一个节点,就把temp后移, 让temp时刻保持在新链表的最后一个节点的位置
            temp = temp.next;
        }
        
        
        /*
        当我们跳出循环后, 就开始判断是cur1 遍历到头了, 还是cur2 遍历到头了
         */
        /*
        cur2到头了, 就让temp.next指向cur1.
        因为cur2遍历结束以后, 当前cur1位置的节点的值,是一定会比新链表中所有节点的值都大.
        所以我们直接把新链表最后一个节点接上 cur1就可以了
         */
        if (cur2 == null) {
            temp.next = cur1;
        }
        
        /*
         * cur1 为空亦然.
         * 让新链表最后一个节点 接上 cur2.
         */
        if (cur1 == null) {
            temp.next = cur2;
        }
        
        newList.showList();
        list1.showList();
        list2.showList();
    }
}
