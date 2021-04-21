package binarysearch.test;

/**
 * 二分查找法的实现
 * 非递归方式
 *
 * @author initial
 * @create 2021-04-14 19:56
 */
public class BinarySearch {
    
    /**
     * 默认提供升序的数组
     *
     * @param array 排好序的数组
     * @param value 要查的值
     * @return -1没找到.   找到返回索引
     */
    public int binarySearch(int[] array, int value) {
        int startIndex = 0;
        int endIndex = array.length - 1;
        
        //只要startIndex <= endIndex就继续查找
        while (startIndex <= endIndex) {
            //获取中间索引
            int midIndex = (startIndex + endIndex) / 2;
            
            //如果找到,返回索引
            if (value == array[midIndex]) {
                return midIndex;
            }
            
            //要查的大于中间值
            if (value > array[midIndex]) {
                //更新起始位置,开始向右半边查找
                startIndex = midIndex + 1;
                
            } else {
                //要查的小于中间值
                //更新结束位置, 开始向左半边查找
                endIndex = midIndex - 1;
                
            }
            
        }
        
        return -1;
    }
    
}
