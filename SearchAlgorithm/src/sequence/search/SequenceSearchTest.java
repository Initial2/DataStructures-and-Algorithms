package sequence.search;

import org.junit.jupiter.api.Test;

/**
 * 线性查找
 *
 * @author initial
 * @create 2021-03-29 19:25
 */
public class SequenceSearchTest {
    
    @Test
    public void test() {
        int[] array = {1, 3, 5, 6, 71, 2, 945};
        int i = sequenceSearch(array, 71);
        System.out.println(i);
    }
    
    
    /**
     * 返回-1则代表没有查找到
     *
     * @param arr   给定一个数组
     * @param value 要查找的元素
     * @return int 返回该元素第一次出现的下标
     */
    private int sequenceSearch(int[] arr, int value) {
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
