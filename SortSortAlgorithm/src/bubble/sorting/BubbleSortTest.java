package bubble.sorting;

import org.junit.jupiter.api.Test;

/**
 * 冒泡排序的一般写法
 *
 * @author initial
 * @create 2021-03-27 9:55
 */
public class BubbleSortTest {
    
    @Test
    public void test() {
        
        //1. 首先定义一个需要排序的数组
        int[] array = {3, 9, -1, 10, -2};
        int[] ints = bubbleSort(array);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    
    
    /**
     * 把给定的数组,用冒泡排序算法进行排序
     *
     * @param arr 给定的数组
     * @return 返回排序后的数组
     */
    public int[] bubbleSort(int[] arr) {
        
        //实现方法很简单, 双层for循环即可
        
        //1. 先定义一个临时变量,用于交换数组两个元素
        int temp = 0;
    
        /*
            冒泡排序, 需要排序的次数为: 数组的长度-1.
         */
        for (int i = 0; i < arr.length - 1; i++) {
            /*
            每轮排序都会确定数组中的最大的元素,放置在arr.length - 1 - i的位置
            也就是说:  第一轮排序, i = 0. 最大的元素放在倒数第1个位置
                     第二轮排序, i = 1. 最大的元素放在倒数第2个位置
                     以此类推.
             */
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前一个元素比后一个元素大. 交换次序即可
                // 如果想要从大到小排序, 只需要让arr[j] < arr[j + 1]  即可
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            
        }
        return arr;
    }
}








