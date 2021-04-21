import org.junit.jupiter.api.Test;

/**
 * 双向链表
 *
 * @author initial
 * @create 2021-03-23 15:57
 */

class DoubleLinkedListTest {
    
    @Test
    public void test() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        
        list.addByOrder(5);
        list.addByOrder(9);
        list.addByOrder(6);
        list.addByOrder(1);
        list.addByOrder(7);
        list.addByOrder(2);
        
        
        list.showList();
        
        
    }
    
}

/**
 * @author Inital
 */
public class DoubleLinkedList<T> {
    
    
    /**
     * 初始化链表头指针. 默认指向null
     */
    private final Node<T> head = new Node<>(null);
    /**
     * 记录此链表的节点个数
     */
    private int length;
    
    /**
     * 查询链表节点个数
     *
     * @return int 返回length长度
     */
    public int getSize() {
        return length;
    }
    
    
    /**
     * 查询当前链表是否为空
     *
     * @return boolean 为空返回true,反之false
     */
    public boolean isEmpty() {
        return length <= 0;
    }
    
    
    /**
     * 添加一个数据的方法.
     * 此方法默认添加到链表末尾
     *
     * @param data 要添加的数据
     */
    public void add(T data) {
        
        if (data == null) {
            return;
        }
        
        // 首先把传进来的数据给打包成一个Node.
        Node<T> tNode = new Node<>(data);
        
        //然后遍历此链表, 找到最后一个节点
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //然后把打包好的节点添加到末尾
        temp.next = tNode;
        tNode.prev = temp;
        length++;
        
    }
    
    
    /**
     * 提供一个按照添加元素的大小进行添加的方法
     * 此方法需要遍历链表
     */
    public void addByOrder(T data) {
        
        if (data == null) {
            return;
        }
        
        //如果没有节点, 直接添加
        if (length == 0) {
            add(data);
            return;
        }
        
        //首先创建临时指针, 指向第一个结点
        Node<T> temp = head.next;
        
        //然后打包此数据
        Node<T> tNode = new Node<>(data);
        
        //开始遍历此链表
        while (true) {
            //如果temp == null了 那就证明已经遍历到最后也没有比data更大的
            //也就是说目前链表的所有节点都没它大
            //所以直接把他添加到尾部即可
            if (temp == null) {
                add(data);
                break;
            }
            
            //如果发现一个节点的data比它大
            //就把它添加到此节点的前面
            if ((int) temp.data > (int) data) {
                tNode.next = temp.prev.next;
                temp.prev.next = tNode;
                tNode.prev = temp.prev;
                temp.prev = tNode;
                break;
            }
            //如果上面都不满足, temp指向下一个节点继续判断
            temp = temp.next;
            
        }
        
        
    }
    
    
    /**
     * 修改指定位置的节点
     *
     * @param index 提供要修改的节点的索引
     * @param data  要修改为什么数据
     */
    public void set(int index, T data) {
        
        if (index < 0 || index > length - 1) {
            return;
        }
        
        //定义一个临时指针,指向第一个节点
        Node<T> temp = head.next;
        
        //要修改index位置上的节点, 就遍历到index 的位置即可.
        // 因为我们这里的temp是从第一个节点开始的.
        //temp就是第0个索引的节点
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        //找到指定位置的节点了. 然后修改数据
        temp.data = data;
        
    }
    
    
    /**
     * 删除指定位置的节点
     *
     * @param index 指定删除的位置
     */
    public void delete(int index) {
        
        if (index < 0 || index > length - 1) {
            return;
        }
        
        //定义一个临时指针,指向第一个节点
        Node<T> temp = head.next;
        
        //要删除index位置上的节点, 就遍历到index 的位置即可.
        // 因为我们这里的temp是从第一个节点开始的.
        //temp就是第0个索引的节点
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        
        
        //找到指定位置的节点了. 然后删除
        temp.prev.next = temp.next;
        
        /*
            然后进行判断,如果删除的是最后一个节点.
             那么temp.next 就是空的. 就不用再执行temp.next.prev = temp.next了.
             不然会出现空指针异常
         */
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        
        //然后节点个数-1
        length--;
    }
    
    
    /**
     * 获取指定位置的节点的数据
     *
     * @param index 指定位置
     * @return T 返回该节点的数据
     */
    public T getData(int index) {
        if (index < 0 || index > length - 1) {
            return null;
        }
        
        //定义一个临时指针,指向第一个节点
        Node<T> temp = head.next;
        
        //要删除index位置上的节点, 就遍历到index 的位置即可.
        // 因为我们这里的temp是从第一个节点开始的.
        //temp就是第0个索引的节点
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        
        
        //找到指定位置的节点了
        return temp.data;
        
    }
    
    /**
     * 在指定位置插入新的节点
     *
     * @param index 索引位置
     */
    public void insert(int index, T data) {
        if (index < 0 || index > length - 1) {
            return;
        }
        
        //打包数据为节点
        Node<T> tNode = new Node<>(data);
        
        //定义一个临时指针,指向第一个节点
        Node<T> temp = head.next;
        
        //要插入index位置上的节点, 就遍历到index 的位置即可.
        // 例: index =4.  那么我们从0开始就遍历3次即可
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        
        //找到指定位置的节点了. 然后插入
        temp.prev.next = tNode;
        tNode.prev = temp.prev;
        temp.prev = tNode;
        tNode.next = temp;
        
        //最后节点个数+1
        length++;
        
    }
    
    
    /**
     * 提供遍历此链表的方法
     */
    
    public void showList() {
        Node<T> temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        
    }
    
}

class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;
    
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