package merget.sorting;

import org.junit.jupiter.api.Test;


/**
 * 归并排序
 * 使用递归实现
 *
 * @author initial
 * @create 2021-03-28 19:46
 */
public class MergeSortTest {
    
    
    @Test
    public void test() {
        //定义需要排序的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        
        //临时数组
        int[] temp = new int[80000];
        
        
        long startTime = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long endTime = System.currentTimeMillis();
        
        
        System.out.println(endTime - startTime + "ms");
        
    }
    
    
    /**
     * 对原来的数组进行排序
     *
     * @param arr   需要排序的数组, 每次递归调用, 传进来的数组都不一样
     * @param left  每次传进来的数组的起始位置
     * @param right 每次传进来的数组的数组末尾位置
     * @param temp  临时数组
     */
    private void mergeSort(int[] arr, int left, int right, int[] temp) {
        
        //只要left != right  就可以继续分. 因为不能分的条件就是全部分成一个了
        //那么left == right
        if (left != right) {
            int mid = (left + right) / 2;
            
            //分解左半部分
            mergeSort(arr, left, mid, temp);
            
            //分解右半部分
            mergeSort(arr, mid + 1, right, temp);
            
            //最后合并
            merge(arr, left, mid, right, temp);
            
        }
    }
    
    
    /**
     * 把数组进行分解
     *
     * @param arr   需要排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param right 右边有序序列的初始索引
     * @param mid   中间初始索引
     * @param temp  临时数组,用于存放排序结果
     */
    private void merge(int[] arr, int left, int mid, int right, int[] temp) {
        
        //1. 初始化索引
        
        //leftIndex就是左边那个有序序列的起始索引
        int leftIndex = left;
        
        //rightIndex就是右边那个有序序列的起始索引  这两个序列从mid分隔开
        int rightIndex = mid + 1;
        
        //初始化临时数组的索引
        int tempIndex = 0;
        
        
        //这个判断条件表示, 如果这两个有序序列, 都没有合并到底 ,我们就继续合并操作
        //只要有任何一个已经合并完了, 就退出
        while (leftIndex <= mid && rightIndex <= right) {
            
            //如果左边那个有序序列当前元素, 小于右边的. 就把左边小的添加到临时数组
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[tempIndex] = arr[leftIndex];
                
                //然后,leftIndex后移比较下一个
                leftIndex++;
                //同时tempIndex也后移,指向下一个位置
                tempIndex++;
            } else {
                //反之, 就把右边的添加到temp中
                temp[tempIndex] = arr[rightIndex];
                
                //然后,后移比较下一个
                rightIndex++;
                tempIndex++;
            }
            
        }
        
        
        
        /*
          while结束以后, 就证明有一个序列合并完了.
          我们就把把剩余的没合并完有序序列,依次加入到temp中
         */
        
        //如果左边有序序列添加完了, 就把右边剩下的加入到temp中
        while (rightIndex <= right) {
            temp[tempIndex] = arr[rightIndex];
            rightIndex++;
            tempIndex++;
        }
        
        //如果右边有序序列添加完了, 就把左边剩下的加入到temp中
        while (leftIndex <= mid) {
            temp[tempIndex] = arr[leftIndex];
            leftIndex++;
            tempIndex++;
        }
        
        
        
        /*
             最后把存放合并结果的temp数组copy到源数组中
             注意: 并不是每次都把temp全部copy.  因为除了最后一次
             前面的temp都不可能放满. 我们合并了几个,就copy几个
         */
        //初始化临时数组索引,从头开始copy
        tempIndex = 0;
        
        // 我们合并了几个,就copy几个
        while (left <= right) {
            arr[left] = temp[tempIndex];
            tempIndex++;
            left++;
        }
        
    }
}
