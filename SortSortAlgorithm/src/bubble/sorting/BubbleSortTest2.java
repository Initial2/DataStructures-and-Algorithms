package bubble.sorting;

import org.junit.jupiter.api.Test;

/**
 * 冒泡排序的优化算法
 *
 * @author initial
 * @create 2021-03-27 10:39
 */
public class BubbleSortTest2 {
    
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
        2. 定义一个标记. 用来记录每次排序时,是否真的进行了交换
         默认为false, 也就是没有进行交换
         */
        boolean flag = false;
    
        
        
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
                /*
                如果前一个元素比后一个元素大. 交换次序即可
                并且将 flag的值改为true. 证明此次排序进行了交换
                 */
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            
            /*
            最后进行判断, 如果flag为false.
            那么证明这一趟排序,根本没有发生元素交换.
            那么也就证明这个数组已经是有序的了,不需要再次进行排序了
             */
            if (!flag) {
                break;
            }
            
            //如果flag为true, 那么证明这一趟发生了交换. 就让flag重置,进行下一趟判断
            flag = false;
            
        }
        return arr;
    }
}
