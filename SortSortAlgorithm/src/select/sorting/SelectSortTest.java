package select.sorting;


import org.junit.jupiter.api.Test;

/**
 * 选择排序的一般实现
 *
 * @author initial
 * @create 2021-03-27 10:59
 */
public class SelectSortTest {
    
    
    @Test
    public void test() {
        
        //需要排序的数组
        int[] array = {5, 9, 11, 2, 5, 8};
        
        int[] ints = selectSort(array);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        
        
    }
    
    /**
     * 使用选择排序, 来对数组进行排序
     *
     * @param array 给定需要排序的数组
     * @return int[] 返回排序好的数组
     */
    private int[] selectSort(int[] array) {
        
        
        //1. 选择排序和冒泡排序一样, 也要进行数组长度-1次排序
        for (int i = 0; i < array.length - 1; i++) {
            /*
               定义2个临时变量currentMinIndex, 和currentMinNum.
               用来存储每一轮数组中最小元素的索引. 以及数值
               这里我们默认每轮开始时, 最小元素是i这个位置.
            */
            int currentMinIndex = i;
            int currentMinNum = array[currentMinIndex];
            
            
            //然后遍历数组,从i开始. 要找出数组中最小的那个元素
            for (int j = i; j < array.length - 1; j++) {
                //如果有比当前元素更小的元素.
                if (currentMinNum > array[j + 1]) {
                    //记录下来它的位置, 以及数值. 然后进行下一次比较
                    currentMinIndex = j + 1;
                    currentMinNum = array[currentMinIndex];
                }
                
                
            }
            
            /*
            每一轮遍历结束,都先进行判断, 如果currentMinIndex == i;
            证明就没比i这个位置更小的元素, 没有发生交换. 所以就不用进行交换了
             */
            if (currentMinIndex == i) {
                continue;
            }
            
            //只有currentMinIndex发生改变了,才进行交换
            //都让最小的元素与i所在的元素交换位置
            array[currentMinIndex] = array[i];
            array[i] = currentMinNum;
            
        }
        
        return array;
    }
    
}
