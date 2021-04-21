package hearsort.test;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 堆排序
 * 这里升序排序
 *
 * @author initial
 * @create 2021-04-05 11:25
 */
public class HeapSortTest {
    
    
    @Test
    public void test() {
        int[] array = {4, 6, 8, 5, 9, 1, -55, 66, 12, 27};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
    
    
    private void heapSort(int[] array) {
         /*
        1. 首先把数组改造成一个大顶堆
           i = array.length/ 2 -1, 此时i就是此数组最后一个子树(非叶子结点)的位置;
           自下向上,一次改造
         */
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustArray(array, i, array.length);
            
        }
        
        
        /*
        2. 然后交换头尾元素,也就是把最大元素,放到数组最末尾.
         */
        for (int j = array.length - 1; j > 0; j--) {
            //交换元素位置
            int temp = array[j];
            array[j] = array[0];
            array[0] = temp;
            //然后接着比较
            adjustArray(array, 0, j);
        }
    }
    
    
    /**
     * 给定一个数组,从index作为父节点开始,比较它的所有子树
     * 把大的子节点和其父节点进行交换.
     * 保证index节点的值是他所有子节点最大的.
     *
     * @param array  给定的数组
     * @param index  开始调整的位置
     * @param length 数组的长度
     */
    private void adjustArray(int[] array, int index, int length) {
        //1.首先获取index位置的值
        int temp = array[index];
        
        /*
        2. 然后开始遍历从index开始的所有子结点
           index * 2 +1 是index的左子节点.  每次比较过后,
           i = i * 2 +1,也就是跳转到i的左子节点
         */
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            //如果左子节点值比右子节点小.
            //i + 1 < length 防止索引越界
            if (i + 1 < length && array[i] < array[i + 1]) {
                //那就i++,让i移动到右子节点索引
                i++;
            }
            
             /*
                如果子节点值比temp大.
                让array[index]存放大的值.
                然后让index 指向i
            */
            if (array[i] > temp) {
                array[index] = array[i];
                index = i;
            } else {
                //如果子节点没index父节点大. 直接退出比较
                break;
            }
            
        }
        /*
        最后, 让array[index]的值=temp. 完成交换
        因为我们之前for循环,如果有比index大的值,那么index存放的是大的值.
        然后让index指向了比它大的值的位置. 此时比index大的值,我们还没有交换.
         */
        array[index] = temp;
    }
    
}
