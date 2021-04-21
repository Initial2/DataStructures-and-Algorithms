package stack.use;

import arraystack.test.ArrayStack;
import org.junit.jupiter.api.Test;

/**
 * 用数组模拟栈, 来实现一个简易计算器
 *
 * @author initial
 * @create 2021-03-24 13:27
 */


class CalculatorTest {
    @Test
    public void test() {
        Calculator calculator = new Calculator();
        int calculator1 = calculator.calculator("411+1+19");
        System.out.println(calculator1);
    }
}


/**
 * @author Inital
 */
public class Calculator {
    
    /**
     * 首先定义一个存放数字的栈, 这里我们预设最多只能存10个数字,
     */
    ArrayStack<Integer> numStack = new ArrayStack<>(10);
    
    /**
     * 首先定义一个存放符号的栈, 这里我们也预设最多只能存10个符号,
     */
    ArrayStack<Character> operatorStack = new ArrayStack<>(10);
    
    
    /**
     * @param str 给定一个字符串表达式
     * @return 返回计算后的结果
     */
    public int calculator(String str) {
        
        //首先解析字符串
        parseString(str);
        
        
        /*
         * 到此为止我们已经遍历完了整个字符串.
         * 下面可以开始计算了
         * 只要符号栈里面还有符号,我们就一直计算.
         * 每次取出2个栈中的元素. 1个操作符, 然后把结果再存入栈
         */
        while (!operatorStack.isEmpty()) {
            Integer pop1 = numStack.pop();
            Integer pop2 = numStack.pop();
            Character pop3 = operatorStack.pop();
            numStack.push(getResult(pop1, pop2, pop3));
        }
       
        /*
        当运算符栈空的时候,numStack中栈顶的元素一定是我们的结果
        直接返回它即可
         */
        return numStack.pop();
        
    }
    
    
    private void parseString(String str) {
        
        /*
        初始化要拼接的数字,因为数字有可能不止一位
         */
        String num = "";
        
        
        //遍历整个字符串
        for (int i = 0; i < str.length(); i++) {
            char chars = str.charAt(i);
            
            //判断chars是什么,然后执行相应处理
            
            //如果是运算符
            if (isOperator(chars)) {
                //那么就要先判断当前符号栈是否为空
                if (!operatorStack.isEmpty()) {
                    /*
                    如果不为空, 那么我们就要进行比较. 看看当前的操作符和栈中的操作符哪个优先级更高.
                     */
                    
                    /*
                    如果当前的操作符优先级 <  栈顶的操作符的优先级, 我们就需要从numStack中pop出两个数
                    然后把当前栈中栈顶的操作符拿出来, 进行运算
                    例:  当前操作符是 +号 , 栈顶是*号  那么我们肯定要保证乘法比加法先算, 一旦让+号入栈
                         那么根据栈的特性. +号一定比*先运行.这肯定不行
                     */
                    if (getPriority(chars) <= getPriority(operatorStack.peek())) {
                        
                        //取出2个数字
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        
                        //取出栈中的那个优先级高的操作符
                        Character operator = operatorStack.pop();
                        
                        //然后进行运算, 接着让结果进数字栈
                        numStack.push(getResult(num1, num2, operator));
                        
                        //比他优先的操作符已经处理完了,然后让当前操作符入符号栈
                        operatorStack.push(chars);
                        
                    } else {
                        //如果当前的操作符优先级 > 栈顶的操作符的优先级, 我们直接添加即可
                        operatorStack.push(chars);
                    }
                    
                } else {
                    //如果当前符号栈为空, 那么就直接添加符号
                    operatorStack.push(chars);
                }
                
                
            } else {
                /*
                   如果不是操作符,先用临时变量num存储这个数字,
                   因为数字可能不止一位,需要拼接
                 */
                num += chars;
                
                //如果当前数字已经是最后一个字符了,直接添加
                if (i == str.length() - 1) {
                    numStack.push(Integer.parseInt(num));
                } else {
                    /*
                        如果当前数字不是最后一位,那么就要判断那他有几位,
                        如果下一位不是符号,那么就证明这个数字我们还没遍历完,
                        我们就先不添加这个数字.
                        如果下一位是符号,那么这个数字我们就遍历完了,直接添加
                     */
                    if (isOperator(str.charAt(i + 1))) {
                        numStack.push(Integer.parseInt(num));
                        
                        //添加完之后把num重置回空, 防止下一个数组拼接出错
                        num = "";
                    }
                    
                }
                
            }
            
            
        }
    }
    
    
    /***
     * @param num1  要运算的第一个数
     * @param num2  要运算的第二个数
     * @param operator  传进来的操作符
     * @return int 返回运算结果
     */
    private int getResult(int num1, int num2, Character operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                //注意:  因为我们把数存在栈当中,先取得num1, 后取得num2
                //这就代表了num2先进栈,然后才是num1进栈.   所以是num2 - num1;
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                //这里和减法一样. 因为+ 和 * 对两个数顺序没要求. 但是- 和 \有要求
                return num2 / num1;
            default:
                return 0;
        }
    }
    
    
    /**
     * 判断传进来的字符是不是操作符号
     *
     * @param chars 传进来的字符
     * @return 是操作符返回true
     */
    public boolean isOperator(char chars) {
        switch (chars) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }
    
    public int getPriority(char chars) {
        switch (chars) {
            //  + - 的优先级为1
            case '+':
            case '-':
                return 1;
            
            //  * / 的优先级比 + -高
            case '*':
            case '/':
                return 2;
            
            default:
                return 0;
        }
    }
}
