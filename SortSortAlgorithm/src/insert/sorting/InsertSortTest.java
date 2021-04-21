package insert.sorting;

import org.junit.jupiter.api.Test;

/**
 * 插入排序实现
 *
 * @author initial
 * @create 2021-03-27 20:32
 */
public class InsertSortTest {
    
    
    @Test
    public void test() {
        int[] arr = {101, 34, 119, 89};
        int[] ints = insertSort(arr);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    
    
    /**
     * 插入排序算法
     *
     * @param arr 给定需要排序的数组
     * @return int[] 返回排序好的数组
     */
    private int[] insertSort(int[] arr) {
    
        /*
            从第2个元素开始. 第1个元素被我们当成有序表了
         */
        for (int i = 1; i < arr.length; i++) {
            
            //获取需要进行插入的元素的数值
            int insertValue = arr[i];
            
            //每次先让它和有序表中最后一个元素比较.
            int insertIndex = i - 1;
            
            /*
                insertIndex >= 0.  只要索引还没到有序表的头
                insertValue < arr[insertIndex]  只要要插入的值, 比有序表中的元素要小.
             */
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                /*
                让比他大的元素后移一位 arr[insertIndex] 比他大, 就让它后移
                 */
                arr[insertIndex + 1] = arr[insertIndex];
                
                //然后index -- , 继续比较前一个有序表中的元素
                insertIndex--;
            }
            
            /*
            如果经过我们的遍历, insertIndex的值就没变,
            那么就证明当前的数值就是最小的,不用换位置.
            直接进行下一次比较
             */
            if (insertIndex == i - 1) {
                continue;
            }
            
            
            /*
                到这里,我们就已经找到了待插入的元素的位置
                因为循环终止条件, 要么我们已经查到头了,
                要么我们要插入的值已经比有序表中的元素小了
                如果我们因为查到头推出的循环, 那么insertIndex就是-1,
                我们让insertValue 放在 insertIndex+1 也就是有序表的头一个位置
               
                如果我们因为要插入的值已经比前一个小了退出的循环,
                那么insertIndex此时就是比他大的元素的位置,
                我们直接放在它后面即可. 即insertIndex+1
             */
            arr[insertIndex + 1] = insertValue;
            
        }
        return arr;
        
    }
}
