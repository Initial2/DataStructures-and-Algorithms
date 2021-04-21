package singlelinkedlist.demo;

import org.junit.jupiter.api.Test;


/**
 * 单链表测试
 *
 * @author initial
 * @create 2021-03-21 15:09
 */
public class SingleLinkedListTest {
    @Test
    public void test() {
        
        StudentLinkedList studentLinkedList = new StudentLinkedList();
        studentLinkedList.addNode(new StudentNode(1521621, "小明", 88.5));
        studentLinkedList.addNode(new StudentNode(6216, "小王", 97));
        studentLinkedList.addNode(new StudentNode(261631111, "罗翔", 100));
        studentLinkedList.addNode(new StudentNode(6163122, "翔", 100));
        
        StudentNode studentNode = studentLinkedList.get(0);
        
        System.out.println(studentNode);
        System.out.println();
        studentLinkedList.showList();
        
        
    }
    
}


/**
 * 一个用来存放学生成绩的链表
 */
class StudentLinkedList {
    
    /**
     * 提供一个head指针,用来记录此链表头个元素的位置.
     * 此节点不存放任何数据
     */
    private final StudentNode head = new StudentNode(0, "", 0.0);
    private int length = 0;
    private StudentNode temp = null;
    
    
    /**
     * 提供判断链表是否为空的方法
     */
    public boolean isEmpty() {
        return head.next == null;
    }
    
    
    /**
     * 获取当前链表的长度
     */
    public int getLength() {
        return length;
    }
    
    
    /**
     * 提供添加节点的方法
     *
     * @param node 需要添加的节点
     */
    public void addNode(StudentNode node) {
        if (node == null) {
            return;
        }
        
        temp = head;
        if (length == 0) {
            temp.next = node;
            length++;
            return;
        }
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                length++;
                break;
            }
            temp = temp.next;
        }
        
    }
    
    
    /**
     * 提供一个按照添加元素的大小进行添加的方法
     * 这里比较的是data大小 从高到低排序
     * 此方法需要遍历链表
     */
    public void addByOrder(StudentNode node) {
        temp = head;
        while (true) {
            //如果条件满足,代表已经比较到到最后一个元素了,直接添加即可
            if (temp.next == null) {
                temp.next = node;
                length++;
                break;
            }
            //此方法会找到第一个比node.score小的节点. 并把node添加到它前面.
            if (temp.next.score < node.score) {
                node.next = temp.next;
                temp.next = node;
                length++;
                break;
            }
            //temp每次后移一个
            temp = temp.next;
            
        }
        
        
    }
    
    
    /**
     * 提供删除指定位置节点的方法
     *
     * @param index 指定删除的位置
     */
    public boolean delete(int index) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        if (index == 0) {
            head.next = head.next.next;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                temp.next = temp.next.next;
                length--;
                return true;
            }
            temp = temp.next;
            
        }
        return false;
    }
    
    
    /**
     * 提供修改指定位置节点的方法
     *
     * @param index 指定需要修改的节点的位置
     * @param node  需要覆盖的新的节点
     */
    public boolean set(int index, StudentNode node) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        if (index == 0) {
            node.next = head.next.next;
            head.next = node;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                node.next = temp.next.next;
                temp.next = node;
                return true;
            }
            temp = temp.next;
        }
        
        return false;
    }
    
    /**
     * 提供插入节点的方法
     *
     * @param index 指定需要插入的节点的位置
     * @param node  需要插入的新的节点
     */
    public boolean insert(int index, StudentNode node) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        if (index == 0) {
            node.next = head.next;
            head.next = node;
            length++;
            return true;
        }
        
        temp = head.next;
        for (int i = 0; i < index; i++) {
            if (i == (index - 1)) {
                node.next = temp.next;
                temp.next = node;
                length++;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * 提供一个查询的方法
     */
    public StudentNode get(int index) {
        if (length == 0) {
            return null;
        }
        
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("索引越界");
        }
        
        temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
            if (i == index) {
                return temp;
            }
        }
        return null;
    }
    
    /**
     * 提供遍历链表的方法.
     * 此方法打印链表中所有节点的信息
     */
    public void showList() {
        if (isEmpty()) {
            return;
        }
        StudentNode temp = head.next;
        while (true) {
            if (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            } else {
                return;
            }
        }
        
        
    }
    
}


/**
 * 节点类.
 */
class StudentNode {
    /**
     * 定义节点中需要存放的数据
     */
    final int age;
    final String name;
    final double score;
    
    /**
     * next 用来存放下一个节点的地址值
     */
    StudentNode next = null;
    
    
    public StudentNode(int age, String name, double score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }
    
    /**
     * 重写toString()方法, 方便打印观察信息.
     *
     * @return null;
     */
    @Override
    public String toString() {
        return "Node{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}