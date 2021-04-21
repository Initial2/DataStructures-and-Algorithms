package insert.search;

import org.junit.jupiter.api.Test;

/**
 * 插值查找算法
 *
 * @author initial
 * @create 2021-03-30 10:13
 */
public class InsertSearchTest {
    
    @Test
    public void test() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        
        int i = insertSearch(arr, 0, arr.length - 1, 99);
        System.out.println(i);
    }
    
    
    /**
     * @param arr       给定要查询的数组
     * @param left      查询的左边界
     * @param right     查询的右边界
     * @param findValue 要查询的值
     * @return int  返回-1代表没查到, 如果查到返回索引
     */
    private int insertSearch(int[] arr, int left, int right, int findValue) {
        
        
        /*
            递归终止条件1:
                left > right 就是查完了也没发现
                findValue < arr[0] || findValue > arr[arr.length - 1]
                因为我们给定的数组是有序的, 所以如果我们要查找的值比最小的还小, 或者比最大的还大,
                那么就没有比较继续查找了
         */
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        
        
        /*
        1. 获取用插值查找算法计算的索引, 这里findValue也参与到了运算
            这里体现了midIndex的自适应
         */
        int midIndex = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[midIndex];
        
        
       
        /*
        2.  递归终止条件2:
            已经查找到了对应的值,返回索引
         */
        if (findValue == midValue) {
            return midIndex;
        }
        
        
        //3. 开始递归查找
        if (findValue < midValue) {
            return insertSearch(arr, left, midIndex - 1, findValue);
        } else if (findValue > midValue) {
            return insertSearch(arr, midIndex + 1, right, findValue);
        } else {
            return -1;
        }
        
        
    }
}
