package shell.sorting;

import org.junit.jupiter.api.Test;


/**
 * 希尔排序的一般实现
 * 使用交换法
 *
 * @author initial
 * @create 2021-03-28 13:35
 */
public class ShellSortTest {
    
    @Test
    public void test() {
        
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] ints = shellSort(array);
        
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        
    }
    
    
    /**
     * 希尔排序
     *
     * @param array 给定需要排序的数组
     * @return int[]  返回排序完成后的数组
     */
    private int[] shellSort(int[] array) {
        
        /*
       
        gap表示我们的增量   初始增量为 10/2= 5
        每次把数组根据增量gap 分为若干个小数组.
        这里数组初始长度为10 ,第一次分为10/2. 5个小数组
        
        然后减少增量, 让gap /=2;
        第二次分为: 5/2 = 2个小数组
        
        然后减少增量, 让gap /=2;
        第三次分为 2/2 = 1 也就是1个数组.
         */
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            
            //然后分别遍历我们分好的每一个数组, 这里用插入排序
            for (int i = gap; i < array.length; i++) {
                
                //每次先让它和有序表中最后一个元素比较.
                int insertIndex = i;
                
                //获取需要进行插入的元素的数值
                int insertValue = array[i];
                
                
                //只要这个有序表我们还没有遍历到头, 且还存在比要插入的元素更小的元素.
                //就一直比较下去
                while (insertIndex - gap >= 0 && insertValue < array[insertIndex - gap]) {
                      /*
                        发现一个比它大的.
                        让比他大的元素后移一位
                      */
                    array[insertIndex] = array[insertIndex - gap];
                    
                    //然后index - gap,继续比较前一个有序表中的元素
                    insertIndex -= gap;
                    
                }
                
                
                //只有当索引位置改变了,我们再修改值.
                //不然就证明当前的数值就是最小的,不用换位置.
                if (insertIndex != i) {
                    //最后把元素插入相应的位置
                    array[insertIndex] = insertValue;
                }
                
            }
            
        }
        return array;
        
        
    }
}
