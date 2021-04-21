package stack.use;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 一个完整的逆波兰表达式计算器
 * 支持小数点运算
 * 逆波兰表达式的计算
 *
 * @author initial
 * @create 2021-03-24 16:38
 */


public class PolandNotationTest {
    
    @Test
    public void test() {
        
        // 给定一个运算表达式, 把它转换为List类型
        ArrayList<String> originList = toInfixList("(1.5+(2*1.5))/0.5/3");
        
        //把转换后的List型表达式, 转换成List型的逆波兰表达式
        ArrayList<String> strings1 = infixToSuffix(originList);
        System.out.println(strings1);
        //传入逆波兰表达式,计算结果
        Double result = getResult(strings1);
        System.out.println(result);
        
        
    }
    
    /**
     * 把字符串中缀表达式,转换为list类型. 方便进行操作
     *
     * @param str 要进行转换的中缀表达式,字符串类型
     * @return List<String>  把传进来的字符串,转换成List类型
     */
    private ArrayList<String> toInfixList(String str) {
        
        // num 用来拼接数字,因为数字可能不止是一位
        StringBuilder num = new StringBuilder();
        
        //用来存放结果的链表
        ArrayList<String> list = new ArrayList<>();
        
        //开始遍历此字符串
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                //如果是符号,就直接添加了. 不需要进行判断
                case '+':
                case '-':
                case '*':
                case '/':
                case '(':
                case ')':
                    list.add(str.charAt(i) + "");
                    break;
                
                default:
                    
                    // 先让num拼接上当前数字.
                    num.append(str.charAt(i));
                    
                    //判断,如果下一位是小数点,就接着遍历
                    if (i + 1 < str.length() && ".".equals(String.valueOf(str.charAt(i + 1)))) {
                        num.append(str.charAt(i + 1));
                        i++;
                        continue;
                    }
                    
                    //如果当前数字的下一位还是数字. 就直接进行下一次遍历.
                    // 直到当前数字下一位不是数字. 或者遍历到了结尾
                    if (i + 1 < str.length() && (int) str.charAt(i + 1) >= 48 && (int) str.charAt(i + 1) <= 57) {
                        continue;
                    }
                    
                    //到这里数字就拼接完成了, 直接添加即可
                    list.add(num.toString());
                    
                    //再把数字清空,预备下一次拼接
                    num = new StringBuilder();
                    break;
            }
        }
        return list;
    }
    
    
    /**
     * 给定一个中缀表达式的list, 把它转换为一个逆波兰表达式
     *
     * @param list 给定的中缀表达式list
     * @return ArrayList<String> 返回转换后的逆波兰表达式
     */
    private ArrayList<String> infixToSuffix(List<String> list) {
        
        //1. 初始化两个容器
        // operateList 用来存放运算符的
        Stack<String> operateStack = new Stack<>();
        
        /*
            resultList 用来存放结果.
            如果我们使用栈来存放的话, 那么结果还要倒序一下才是逆波兰表达式.
            并且在转换过程中, 我们的s2栈, 根本不需要取出数据.
            所以这里使用list来替代栈. 好处是: 结果不需要倒序了.
         */
        ArrayList<String> resultList = new ArrayList<>();
        
        
        //2. 开始遍历此list
        for (String s : list) {
            switch (s) {
                //如果是 ( 直接入符号栈
                case "(":
                    operateStack.push(s);
                    break;
                
                //如果是运算符号
                case "*":
                case "/":
                case "+":
                case "-":
                    //首先判断, 符号栈是否为空,为空直接添加
                    if (operateStack.isEmpty()) {
                        operateStack.push(s);
                        break;
                    }
                    //如果符号栈不为空, 判断他们两个的优先级
                    
                    /* 如果当前符号的优先级 <= 栈顶优先级. 例: 当前: +  栈顶: *
                        就把栈顶的 * 取出来放到resultList中继续比较
                        
                  注意: 一定要加上operateStack不为空的判断条件.
                        如果符号栈只有一个 - 号, 当前符号为+号 ,那么根据我们的判断.
                        会把-号取出来放到resultList中. 然后接着进行判断.
                        此时的栈已经为空, operateStack.peek()会报空指针异常
                     */
                    while (!operateStack.isEmpty() && getPriority(s) <= getPriority(operateStack.peek())) {
                        resultList.add(operateStack.pop());
                    }
                    
                    //如果当前符号的优先级比栈顶优先级高.
                    //我们就直接加入符号栈
                    operateStack.push(s);
                    break;
                
                
                case ")":
                    //依次弹出符号栈的栈顶符号, 把他们加入到resultList中.
                    // 直到符号栈的栈顶符号为 ( 为止.
                    while (!"(".equals(operateStack.peek())) {
                        resultList.add(operateStack.pop());
                    }
                    //然后再把这个 ( 抵消掉
                    operateStack.pop();
                    break;
                
                //如果是数字,直接加入resultList
                default:
                    resultList.add(s);
                    break;
            }
        }
        
        //到这里我们已经遍历完List了.
        //下面开始依次将符号栈中的元素取出来,然后添加到resultList中
        while (!operateStack.isEmpty()) {
            resultList.add(operateStack.pop());
        }
        
        //最后返回结果
        return resultList;
        
    }
    
    
    /**
     * 获取运算符的优先级
     *
     * @param operate 运算符
     * @return 返回优先级数字
     */
    private int getPriority(String operate) {
        switch (operate) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }
    
    
    /**
     * 根据给的逆波兰表达式list, 开始计算
     *
     * @param strings 由逆波兰表达式转换的list
     * @return int 返回计算结果
     */
    private Double getResult(List<String> strings) {
        Stack<Double> stack = new Stack<>();
        
        //遍历此list
        for (String string : strings) {
            
            //根据string的内容,执行相应的操作
            switch (string) {
                case "+":
                    //取出一个,栈顶后移. 再取出一个. 相当于取出了栈顶和次栈顶元素
                    //运算完再push进栈
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    //减法需要注意顺序,所以不能用+法的写法
                    Double num1 = stack.pop();
                    Double num2 = stack.pop();
                    stack.push(num2 - num1);
                    break;
                case "*":
                    //和+法相同,因为*不用注意顺序
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    //和-法相同, 要注意顺序
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 / num1);
                    break;
                
                //如果不是运算符,那么证明是数字, 就直接入栈即可
                default:
                    stack.push(Double.parseDouble(string));
                    break;
            }
        }
        
        //最后栈顶元素就是运算结果
        return stack.pop();
        
    }
    
    
}
