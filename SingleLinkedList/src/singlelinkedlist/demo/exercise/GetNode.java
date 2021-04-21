package singlelinkedlist.demo.exercise;


import singlelinkedlist.demo.UseGenericLinkedList;
import singlelinkedlist.demo.UseGenericLinkedListTest;

/**
 * 查找单链表中倒数第K个节点
 *
 * @author initial
 * @create 2021-03-22 16:25
 */
public class GetNode {
    
    
    public static void main(String[] args) {
        
        
        UseGenericLinkedList<Integer> list = new UseGenericLinkedListTest().test1();
        solution(list, 4);
    }
    
    
    /**
     * 此方法用于, 链表中没有getLength()之类的,能够获取当前链表的节点个数的方法
     * 也就是我们无法提前知道链表中的节点个数
     *
     * @param index 要获取的倒数的节点索引
     */
    
    public static void solution(UseGenericLinkedList<Integer> list, int index) {
        
        // 不合法索引
        if (index < 0) {
            return;
        }
        
        //空链表
        if (list == null) {
            return;
        }
        
        //1. 首先获取此链表的head
        UseGenericLinkedList<Integer>.Node<Integer> temp = list.head;
        
        //2. 遍历此链表, 统计有多少个节点
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        
        //不合法的索引
        if (index > length) {
            return;
        }
        
        
        /*
            3. 然后再次遍历此链表, 这次只遍历到(length - index) + 1的地方
               因为如果节点数为5, 我们获取倒数第3个.  其实就是获取5-3 索引位置为2上的节点
               那么我们就需要从头遍历 2+1次
         */
        int times = (length - index) + 1;
        UseGenericLinkedList<Integer>.Node<Integer> temp1 = list.head;
        while (times != 0) {
            temp1 = temp1.next;
            times--;
        }
        System.out.println(temp1);
        
    }
    
    
}
