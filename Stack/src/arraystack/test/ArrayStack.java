package arraystack.test;

import org.junit.jupiter.api.Test;

/**
 * 用数组来实现栈的测试类
 *
 * @author initial
 * @create 2021-03-24 10:31
 */
class ArrayStackTest {
    
    @Test
    public void test() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        
        arrayStack.show();
        
        System.out.println();
        System.out.println(arrayStack.pop());
    }
    
}


/**
 * 数组模拟栈
 *
 * @author initial
 * @create 2021-03-24 10:31
 */
public class ArrayStack<T> {
    
    /**
     * 定义一个常量, 用来指定栈的大小
     */
    private final int MAX_SIZE;
    
    /**
     * 定义一个栈, 先不指定大小
     */
    private final T[] ARRAYSTACK;
    
    /**
     * 定义一个栈顶. 默认为-1
     */
    private int top = -1;
    
    
    /**
     * 根据传入的大小, 赋值给MAX_SIZE
     *
     * @param maxSize 指定创建栈的大小
     */
    public ArrayStack(int maxSize) {
        this.MAX_SIZE = maxSize;
        ARRAYSTACK = (T[]) new Object[maxSize];
    }
    
    /**
     * 看看当前栈顶的元素是什么
     *
     * @return 返回当前栈顶的元素
     */
    public T peek() {
        return ARRAYSTACK[top];
    }
    
    /**
     * 判断当前栈是否为空
     *
     * @return 为空返回true
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * 判断当前栈是否已满
     *
     * @return 为满返回true
     */
    public boolean isFull() {
        return top == MAX_SIZE - 1;
    }
    
    
    /**
     * 入栈操作
     *
     * @param data 要压入栈的数据
     */
    public void push(T data) {
        
        if (isFull()) {
            throw new RuntimeException("当前栈已满,无法添加.");
        }
        // 首先让top++; 上移一格
        top++;
        
        //然后把数据压入栈顶的位置
        ARRAYSTACK[top] = data;
        
    }
    
    /**
     * 出栈操作
     *
     * @return int 返回出栈的数据
     */
    public T pop() {
        
        //首先判断是否栈空
        if (isEmpty()) {
            throw new RuntimeException("当前栈为空.");
        }
        
        //首先定义一个临时变量value,存放栈顶的数据
        T value = ARRAYSTACK[top];
        
        //取出数据后,栈顶下移
        top--;
        
        //然后返回此数据
        return value;
    }
    
    
    /**
     * 显示当前栈中有多少个元素
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("当前栈为空.");
        }
        
        //定义一个临时变量value,来代替栈顶.
        //这里是遍历操作不是取出操作.所以top栈顶我们不能随便移动位置
        int value = top;
        while (value != -1) {
            System.out.println(ARRAYSTACK[value]);
            //取出数据后,栈顶下移
            value--;
        }
        
    }
}