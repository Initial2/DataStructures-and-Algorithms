package binary.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * 二分查找法
 * 递归实现2
 * 如果要查找的元素, 数组中存在多个.  那么就把所有相同的元素索引都返回
 *
 * @author initial
 * @create 2021-03-29 20:33
 */
public class BinarySearchTest2 {
    
    @Test
    public void test() {
        int[] arr = {1, 1, 5, 7, 9, 9, 22, 155, 200, 200};
        ArrayList<Integer> integers = binarySearch(arr, 0, arr.length - 1, 0);
        
        
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
    
    /**
     * @param array     给定的查找数组
     * @param left      每次查找的左界限
     * @param right     每次查找的右界限
     * @param findValue 要查找的值
     * @return ArrayList<Integer> 返回查找到相同元素的索引. 如果没有返回空
     */
    private ArrayList<Integer> binarySearch(int[] array, int left, int right, int findValue) {
        
        ArrayList<Integer> integers = new ArrayList<>();
        
        //1. 首先获取数组中中间的那个元素的索引和值
        int midIndex = (left + right) / 2;
        int midValue = array[midIndex];
        
        
        //终止条件1: 递归了查找整个数组也没找到, 返回-1.
        /*
         left > right 因为我们每次没有查找到, 就会把范围进一步缩小.
         如果一直查找不到, 那么范围最终会缩小到一个点.
         如果这个索引的值还不等与我们查找的元素,
         那么要查找的元素就不存在.直接返回-1即可.
         */
        if (left > right) {
            
            return new ArrayList<Integer>();
        }
        
        //终止条件2:  如果我们查找到那个数了, 不要立即返回. 再看看他旁边的元素是不是和他相等
        if (findValue == midValue) {
            
            //先把当前位置索引添加到list中
            integers.add(midIndex);
            
            //然后开始判断
            
            //看看他左边有没有跟他相等的,有也添加它的索引
            //这里要首先做判断, 如果查找的元素刚好在第0个位置,或者最后一个位置.
            //我们要防止下标越界
            int index = midIndex - 1;
            while (index >= 0 && array[index] == findValue) {
                integers.add(index);
                index--;
            }
            
            //然后看看他右边有没有跟他相等的,有也添加它的索引,同样也要进行索引判断,防止越界
            index = midIndex + 1;
            while (index <= array.length - 1 && array[index] == findValue) {
                integers.add(index);
                index++;
            }
            
            return integers;
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
        
        
        return new ArrayList<Integer>();
        
    }
    
}
