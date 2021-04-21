package radix.sorting;

import org.junit.jupiter.api.Test;


/**
 * 基数排序
 *
 * @author initial
 * @create 2021-03-29 17:08
 */
public class RadixSortTest {
    
    @Test
    public void test() {
        //要排序的数组
        int[] arr = {53, 3, 542, 748, 14, 214};
        
        for (int i : radixSort(arr)) {
            System.out.println(i);
        }
        
        
    }
    
    
    /**
     * @param arr 要排序的数组
     * @return int[] 返回排序好的数组
     */
    private int[] radixSort(int[] arr) {
        
        //1. 首先定义一个二维数组, 用于存放每次排序后的元素
        // 总共有10个桶, 每个桶的大小, 最差情况是所有元素都放一个桶, 所以用arr.length
        int[][] bucket = new int[10][arr.length];
        
        /*
          2. 再定义一个一维数组, 用来记录每个桶中放了几个元素.
             因为当我们排完序,取的时候,并不知道每个桶里放了几个元素, 所以
             事先用一个一维数组, 数组的每个索引就对应了每个桶.  数组每个索引的值
             就对应了这个桶放了几个元素
         */
        int[] eachBucketNum = new int[10];
        
        
        /*
            3. 在进行基数排序之前, 我们首先要知道数组中最大的数字,它有几位.
               这样我们才能知到比较到那一位就可以退出排序
         */
        
        //假设最大值是第一个
        int max = arr[0];
        
        //遍历数组找到最大的数
        for (int k : arr) {
            max = Math.max(k, max);
        }
        
        //获取最大数字有几位
        int maxLength = (max + "").length();
        
        
        /*
            4. 知道位数以后,就可以开始进行排序
               第一轮比较个位, 第二轮比较十位....
         */
        for (int length = 0, n = 1; length < maxLength; length++, n *= 10) {
            
            //开始对每一轮进行排序
            for (int k : arr) {
                /*
                    第一轮 n= 1  k / n % 10 , 其实就是k%10 比较个位
                    第二轮 n= 10 , k / n % 10 = k /10 % 10 其实就是比较十位
                    第三轮n = 100 比较百位, 以此类推
                 */
                
                //placeIndex就是根据位数的数字,来决定存放的位置
                int placeIndex = k / n % 10;
                
                // bucket[placeIndex]代表把它放到bucket对应的桶中,
                // eachBucketNum[placeIndex]代表了放在这个桶中第几位.
                bucket[placeIndex][eachBucketNum[placeIndex]] = k;
                
                //刚开始放在第0个位置, 每次放完就后移,准备下次放入
                eachBucketNum[placeIndex]++;
            }
            
            
            //4. 然后再遍历这10个桶,把桶中排好序的元素,依次添加到原数组中
            int index = 0;
            
            for (int i = 0; i < bucket.length; i++) {
                /*
                eachBucketNum[i] 就代表了我们每个桶放的元素的个数.
                只有当它!=0 才代表我们之前往里面放过元素. 所以取出
                 */
                if (eachBucketNum[i] != 0) {
                    //放了几个取出几个
                    for (int j = 0; j < eachBucketNum[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                    //每把一个桶中的元素取完, 重置eachBucketNum[i]的值, 方便下一次记录
                    eachBucketNum[i] = 0;
                    
                }
                
            }
            
            
        }
        
        
        return arr;
        
    }
}
