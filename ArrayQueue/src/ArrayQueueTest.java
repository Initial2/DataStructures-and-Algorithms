import java.util.Scanner;


/**
 * @author initial
 * @create 2021-03-20 15:46
 */

public class ArrayQueueTest {
    
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("请输入你的选择");
            System.out.println("a(add)  g(get) h(getHead) e(exit)");
            String next = scanner.next();
            switch (next) {
                case "a":
                    System.out.println("请输入要添加的数据: ");
                    if (arrayQueue.add(scanner.nextInt())) {
                        System.out.println("添加成功");
                    }
                    break;
                case "g":
                    System.out.println("取出的数据为: " + arrayQueue.get());
                    break;
                case "h":
                    System.out.println("取出的头部数据为: " + arrayQueue.getHead());
                    break;
                case "e":
                    return;
                default:
                    return;
            }
        }
        
    }
    
}

