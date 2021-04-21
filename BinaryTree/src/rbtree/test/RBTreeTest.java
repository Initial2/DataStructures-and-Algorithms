package rbtree.test;

import java.util.Scanner;

/**
 * @author initial
 * @create 2021-04-13 11:18
 */
public class RBTreeTest {
    public static void main(String[] args) {
        RedBlackTree<Integer, String> rbTree = new RedBlackTree<>();
        
        while (true) {
            Scanner scanner = new Scanner(System.in);
            rbTree.insert(scanner.nextInt(), "张三");
            TreeOperation.show(rbTree.getRoot());
        }
        
        
    }
}
