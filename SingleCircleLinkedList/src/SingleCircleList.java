import org.junit.jupiter.api.Test;


/**
 * 约瑟夫问题的测试
 * 这里用5个节点来测试
 */
class JosepfuTest {
    
    @Test
    public void test() {
        josepfuTest(125, 10, 20);
    }
    
    
    /**
     * 约瑟夫问题的实现方法
     *
     * @param length 要创建的链表长度,也就是有多少个节点
     * @param begin  开始从第几个节点数数
     * @param times  每次要数几下
     */
    public void josepfuTest(int length, int begin, int times) {
        
        //根据指定长度创建链表
        SingleCircleList list = new SingleCircleList();
        for (int i = 1; i <= length; i++) {
            list.add(i);
        }
        
        //first用来指向要数数的那个节点. 如果是从第一个开始数, 那么first就是要数数的那个节点
        Node first = list.first;
        
        /*
            然后我们创建一个辅助指针helper 这个指针永远指向first的上一个节点
            这个辅助指针helper用来帮我们实现删除节点的操作.
            因为first刚开始肯定在第一个节点, 所以我们就遍历链表, 找到first前一个节点
            让helper指向他
         */
        Node helper = list.first;
        while (true) {
            //如果help.next == first了 就证明已经到list.first前一个位置了
            if (helper.next == list.first) {
                break;
            }
            helper = helper.next;
        }
        
        /*
        接下来就要确定从那个节点开始数数
         */
        
        //这是我们要往后找的次数. 从begin位置开始, 那我们就遍历begin-1次即可
        //如果就是从第一个开始数,那我们就不需要遍历了
        int count = begin - 1;
        
        // 不是从第一个开始数,那我们就要让first和helper移动到到指定位置
        while (count != 0) {
            first = first.next;
            helper = helper.next;
            count--;
        }
    
        /*
        到这里我们已经确定了链表的长度, 要开始数数的节点
        接来下就可以开始进行测试
         */
        
        
        //开始测试
        //只要链表不为空,就一直执行
        while (length > 0) {
            /*
                开始数数的操作
                注意: 两个指针后移的次数.
                例: 如果从1开始往后数5次. 那么就是1 , 2, 3, 4, 5. 第5个出去
                    但是,指针只用往后面移4次即可
             */
            for (int i = 1; i < times; i++) {
                first = first.next;
                helper = helper.next;
            }
            
            /*
            此时的first所在的节点就是要删除的节点.
            然后我们把这个节点删除
             */
            
            //首先打印这个节点的信息
            System.out.println("当前出去的节点为:" + first);
            
            first = first.next;
            helper.next = first;
            
            //长度-1
            length--;
            
        }
        
        
    }
}


/**
 * 单向循环链表
 *
 * @author initial
 * @create 2021-03-23 19:31
 */
public class SingleCircleList {
    
    /**
     * 此指针永远指向第一个添加进来的元素
     */
    public Node first;
    
    /**
     * 链表的节点数量
     */
    public int length;
    
    
    /**
     * 判断此链表是否为空
     *
     * @return boolean 如果为空返回true
     */
    public boolean isEmpty() {
        return length <= 0;
    }
    
    public void add(int num) {
        
        if (num < 1) {
            return;
        }
        
        //第一次添加, 就把first指向这个节点
        if (isEmpty()) {
            first = new Node(num);
            first.next = first;
            length++;
            return;
        }
        
        //不是第一次添加
        Node newNode = new Node(num);
        Node cur = first;
        
        //找到最后那个节点
        while (cur.next != first) {
            cur = cur.next;
        }
        
        //让最后节点指向新的节点
        //新的节点指向first
        cur.next = newNode;
        newNode.next = first;
        length++;
        
    }
    
    /**
     * 遍历操作
     */
    public void show() {
        //新建辅助指针,从第一个节点开始遍历
        Node cur = first;
        
        //如果cur.next==first. 就证明已经遍历完了.
        //那么当前的cur就是最后一个节点
        //不等于的话就把cui后移继续遍历
        while (true) {
            System.out.println(cur.num);
            if (cur.next == first) {
                break;
            }
            cur = cur.next;
        }
        
        
    }
    
}

class Node {
    int num;
    Node next;
    
    public Node(int num) {
        this.num = num;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}