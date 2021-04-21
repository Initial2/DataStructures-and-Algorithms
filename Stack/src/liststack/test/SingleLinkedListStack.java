package liststack.test;

import org.junit.jupiter.api.Test;


class SingleLinkedListStackTest {
    
    @Test
    public void test() {
        SingleLinkedListStack<Integer> listStack = new SingleLinkedListStack<>();
        listStack.add(1);
        listStack.add(2);
        listStack.add(3);
        listStack.show();
        
        Integer pop = listStack.pop();
        Integer pop1 = listStack.pop();
        Integer pop2 = listStack.pop();
        
        
        listStack.show();
    }
    
    
}


/**
 * 单链表实现栈
 *
 * @author initial
 * @create 2021-03-24 12:05
 */
public class SingleLinkedListStack<T> {
    
    
    /**
     * 定义链表的头
     */
    public final Node<T> head = new Node<>();
    /**
     * 初始化链表长度
     */
    int length = 0;
    /**
     * 定义一个栈顶. 默认指向链表头部
     */
    private Node<T> top = head;
    
    
    public SingleLinkedListStack() {
    }
    
    
    /**
     * 判断当前栈是否为空
     *
     * @return 为空返回true
     */
    public boolean isEmpty() {
        return length <= 0;
    }
    
    
    public void add(T data) {
        
        //不向栈中添加空数据
        if (data == null) {
            return;
        }
        
        //先把数据打包成一个节点
        Node<T> tNode = new Node<>(data);
        
        
        //判断是不是添加的第一个节点.
        //如果不是就先让tNode的next指向目前的栈顶结点
        //如果是第一次添加那么可以跳过这个步骤,直接执行 head.next = tNode;
        if (length != 0) {
            tNode.next = head.next;
        }
        
        //然后让head的下一个指向他即可. 这样一来他其实就变成了栈顶节点
        head.next = tNode;
        //让top指针重新指向栈顶节点
        top = tNode;
        length++;
        
        
    }
    
    
    /**
     * 取出栈顶的元素
     *
     * @return 返回该元素
     */
    public T pop() {
        
        if (isEmpty()) {
            return null;
        }
        
        //把栈顶元素拿出来 此时的top就代表栈顶节点
        T value = top.data;
        
        //因为是取出,就相当于把栈顶节点给删除掉
        //所以新的栈顶节点就应该指向被删除的那个后面
        head.next = top.next;
        
        //重新设置栈顶节点的位置
        top = top.next;
        
        return value;
        
    }
    
    
    /**
     * 打印当前栈的所有节点信息
     */
    public void show() {
        
        //定义一个辅助指针,帮我们遍历栈
        Node<T> temp = top;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        
    }
    
    
    class Node<T> {
        private T data;
        
        private Node<T> next;
        
        public Node() {
        }
        
        public Node(T data) {
            this.data = data;
        }
        
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
