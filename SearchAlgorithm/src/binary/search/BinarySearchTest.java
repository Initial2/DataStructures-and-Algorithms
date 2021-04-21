package binary.search;

import org.junit.jupiter.api.Test;

/**
 * 二分查找法
 * 使用递归实现
 *
 * @author initial
 * @create 2021-03-29 19:53
 */
public class BinarySearchTest {
    
    @Test
    public void test() {
        int[] arr = {1, 5, 7, 9, 11, 17, 22};
        int i = binarySearch(arr, 0, arr.length - 1, 22);
        
        System.out.println(i);
    }
    
    /**
     * @param array     给定的查找数组
     * @param left      每次查找的左界限
     * @param right     每次查找的右界限
     * @param findValue 要查找的值
     * @return int 返回查找到相同元素的索引. 如果没有返回-1
     */
    private int binarySearch(int[] array, int left, int right, int findValue) {
        
        
        //1. 首先获取数组中中间的那个元素的索引和值
        int midIndex = (left + right) / 2;
        int midValue = array[midIndex];
        
        
        //终止条件1: 已经查到了那个数, 返回索引
        if (midValue == findValue) {
            return midIndex;
        }
        
        //终止条件2: 递归了查找整个数组也没找到, 返回-1.
        /*
         left > right 因为我们每次没有查找到, 就会把范围进一步缩小.
         如果一直查找不到, 那么范围最终会缩小到一个点.
         如果这个索引的值还不等与我们查找的元素,
         那么要查找的元素就不存在.直接返回-1即可.
         */
        if (left > right) {
            return -1;
        }
        
        
        //3. 然后就进行判断
        
        //如果查找的数,比中间数大, 就把范围设置为右半部分,接着查找
        if (findValue > midValue) {
            return binarySearch(array, midIndex + 1, right, findValue);
        }
        
        //如果查找的数,比中间数小, 就把范围设置为左半部分,接着查找
        if (findValue < midValue) {
            return binarySearch(array, left, midIndex - 1, findValue);
        }
        
        
        return -1;
        
        
    }
    
    
}
