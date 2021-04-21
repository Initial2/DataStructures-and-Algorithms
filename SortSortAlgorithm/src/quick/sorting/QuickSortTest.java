package quick.sorting;

import org.junit.jupiter.api.Test;

/**
 * 快速排序实现
 *
 * @author initial
 * @create 2021-03-28 17:17
 */
public class QuickSortTest {
    
    @Test
    public void test() {
        int[] array = {-9, 23, 0, 23, -567, 70, -11, 500, -19};
        int[] ints = quickSort(array, 0, array.length - 1);
        for (int anInt : ints) {
            System.out.print(anInt + "  ");
        }
    }
    
    
    private int[] quickSort(int[] arr, int left, int right) {
        
        //左索引
        int l = left;
        //右索引
        int r = right;
        //找到数组中间的那个元素,以中间的值作为基准,进行快速排序
        int midNum = arr[(left + right) / 2];
        
        int temp = 0;
        
        
        while (l < r) {
            
            //找到midNum左边所有比它大的数, 因为我们规定midNum左边只能放比它小的数
            // arr[left] < midNum 它最终的终止条件, 就是left已经到了midNum这个位置.
            while (arr[l] > midNum) {
                l++;
            }
            
            //找到midNum右边所有比它小的数, 因为我们规定midNum右边只能放比它大的数
            // arr[right] > midNum 它最终的终止条件, 就是right也已经到了midNum这个位置.
            while (arr[r] < midNum) {
                r--;
            }
            
             /*
                如果left >= right满足了.
                证明我们查找已经结束了. 因为left最终是查找到midNum这个位置
                right最终也是查找到midNum这个位置.  那么此时left == right;
                这样的话 midNum左边已经是全部比它小的.  midNum右边已经是全部比它大的.
                直接退出循环即可
                 */
            if (l >= r) {
                break;
            }
            
            
            // 如果还没有查到头,就把每次找到的元素交换他们的位置.
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            
            
            /*
            如果arr[l] == midNum, 那么上面的条件while (arr[l] < midNum) 就不满足,
            就交换l 和 r位置上的元素.  这样一来 arr[r]就 == midNum了.
            如果我们不让r-- 前移继续比较前一个元素.
            那么下次循环 while (arr[r] > midNum)  就会不满足.  就又把l 和 r位置上的元素互换.
            这样一来, 就会形成死循环
             */
            if (arr[l] == midNum) {
                r--;
            }
            
            //如果发现右边有和midNum相同的值,同理.  我们就让left后移,比较下一个
            if (arr[r] == midNum) {
                l++;
            }
            
            
        }
        
        
        //如果l == r, 证明l 和 r 都在midNum这里 .我们要把他们错开才能进行递归
        if (l == r) {
            //重新定义索引, l移到midNum后面
            l++;
            
            //重新定义索引,r移到midNum前面
            r--;
        }
        
        //开始左递归
        if (left < r) {
            //范围0 - r  r也就是midNum前一个位置
            quickSort(arr, left, r);
        }
        
        //开始右递归
        if (right > l) {
            //范围l - arr.length-1  l也就是midNum后一个位置
            quickSort(arr, l, right);
        }
        
        return arr;
    }
}
