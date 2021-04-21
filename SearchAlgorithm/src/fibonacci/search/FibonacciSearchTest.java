package fibonacci.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 斐波那契查找法
 *
 * @author initial
 * @create 2021-03-30 16:07
 */
public class FibonacciSearchTest {
    
    @Test
    public void test() {
        int[] array = {1, 8, 10, 89, 1000, 1024};
        int i = fibSearch(array, 89);
        System.out.println(i);
    }
    
    
    /**
     * 首先定义一个斐波那契数列
     *
     * @return int[] 返回一个斐波那契数列
     */
    private int[] fib() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        
        
        for (int i = 2; i < 20; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
    
    /**
     * 斐波那契查找算法
     *
     * @param arr       给定的数组
     * @param findValue 要查找的值
     * @return int 返回对应位置的索引, -1为没查找到
     */
    private int fibSearch(int[] arr, int findValue) {
        
        //数组的左边界
        int left = 0;
        //数组的右边界
        int right = arr.length - 1;
        
        //存放用来和findValue比较的数组元素的索引
        int mid;
        
        //获取斐波那契数列
        int[] f = fib();
        
        //要查找的对应的斐波那契数列的索引,默认从0开始
        int k = 0;
        
        /*
        获取当right作为黄金分割点时,数组所需要的长度.
        也就是说: 假如这个数组长度为6, 那么它最后一位索引为5.
                 如果我们让这个最后一个索引5作为整个数组的黄金分割点. 我们需要把数组扩大多少.
                 f[k]的值就是当k作为黄金分割点,所需要的长度
         */
        
        //获取斐波那契分割数值下标 , 当right > f[k] - 1满足时, f[k]的元素就是我们需要的长度
        while (right > f[k] - 1) {
            k++;
        }
        
        /*
            因为我们的数组长度可能会比要求的长度小
            所以这里要对他进行扩容. 扩大到满足right点作为数组黄金分割点的长度
            也就是fib[k] 这个索引对应的数值.
            然后,用当前数组的最后一个元素把后面空白位置填充.
         */
        int[] temp = Arrays.copyOf(arr, f[k]);
        
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        
        
        //开始查找 查询终止条件和二分查找类似.
        while (left <= right) {
            
            //获取每轮根据黄金分割算出来的索引值
            mid = left + f[k - 1] - 1;
            
            //如果findValue比这个索引位置的值小, 把范围缩小到此索引左半部分 ,然后接着查
            if (findValue < temp[mid]) {
                
                /*（全部元素） = （前半部分）+（后半部分）
                    f[k]  =  f[k-1] + f[k-1]
                    因为 我们要查后前部分, 所以k=k-1;
                 */
                right = mid - 1;
                k--;
                
                //如果比这个索引位置的值大, 把范围缩小到此索引右半部分 ,然后接着查
            } else if (findValue > temp[mid]) {
                /*
                    （全部元素） = （前半部分）+（后半部分）
                    f[k]  =  f[k-1] + f[k-2]
                    因为我们要查后半部分, 所以k=k-2;
                 */
                left = mid + 1;
                k -= 2;
                
                //当我们找到这个数后,还要进行判断,
            } else {
                
                //如果满足则找到相应的位置
                // 因为我们查询的数组的右边界是right
                if (mid <= right) {
                    return mid;
                    
                } else {
                    //出现这种情况是查找到补充的元素 因为mid已经>right了
                    //而补充的元素与right位置的元素一样, 所以返回right的位置即可.
                    return right;
                }
            }
            
        }
        return -1;
    }
    
}
