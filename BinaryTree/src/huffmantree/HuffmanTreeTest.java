package huffmantree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树的创建
 *
 * @author initial
 * @create 2021-04-05 14:33
 */
public class HuffmanTreeTest {
    
    @Test
    public void test() {
        //给定的数组
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        
        //返回赫夫曼树的根节点
        Node node = creatHuffmanTree(arr);
        //前序遍历
        node.preOrder();
        
    }
    
    
    /**
     * 根据给定的数组,创建一个赫夫曼树
     *
     * @param arr 给定的数组
     * @return Node 返回赫夫曼树的根节点
     */
    private Node creatHuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        //1. 首先把给定的数组,转换成一个个的Node.然后存到 ArrayList中
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        
        /*
        2. 开始处理
           每次处理,都要删除2个节点, 添加1个节点.
           所以当集合中只有一个元素. 赫夫曼树就创建完成了
         */
        while (nodes.size() > 1) {
            //①: 首先对nodes进行排序
            Collections.sort(nodes);
            
            //②. 取出前两个最小的node
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            
            /*
            ③. 构建一个新的二叉树, 根节点的权为这两个最小的node的权值的和
                然后让这个新结点,左右子节点分别指向它俩
             */
            Node newNode = new Node(leftNode.value + rightNode.value);
            newNode.leftNode = leftNode;
            newNode.rightNode = rightNode;
            
            //④: 这两个节点已经处理过了,把他俩从集合中删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            
            //⑤. 把新结点添加到集合中
            nodes.add(newNode);
        }
        
        //染回赫夫曼树的根节点.
        return nodes.get(0);
    }
    
}

/**
 * 赫夫曼树的节点
 */
class Node implements Comparable<Node> {
    /**
     * 每个节点的权值
     */
    int value;
    
    /**
     * 每个节点的左子节点,右子节点
     */
    Node leftNode;
    Node rightNode;
    
    
    /**
     * 根据权值,初始化Node
     *
     * @param value 给定的权值
     */
    public Node(int value) {
        this.value = value;
    }
    
    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this.value);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }
    
    
    /**
     * 因为在创建赫夫曼树的时候,需要对节点权值进行排序,
     * 所以这里实现以下comparable接口.
     *
     * @param o 要比较的结点
     * @return 比较大小
     */
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.value);
    }
    
    
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}