package sparsearray.test.read;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * 二维数组--->稀疏数组   然后把稀疏数组存到map.data文件中
 * 使用HashMap的写法
 *
 * @author initial
 * @create 2021-03-19 15:38
 */
public class SparseArray1 {
    
    @Test
    public void test() {
        
        //1.  准备一个二维数组
        // 首先创建一个二维数组. 如果我们不添加值,int型默认值为0
        //所以数组中的元素默认值都是0
        int[][] sourceArray = new int[11][11];
        
        //向二维数组中添加不同的元素, 这样一来,一个原数组就创建好了
        sourceArray[2][4] = 5;
        sourceArray[4][4] = 6;
        sourceArray[6][8] = 7;
        sourceArray[7][3] = 8;
        
        //3. 获取该数组中,有多少行,多少列. 几个不同的元素
        //并把不同的元素的索引存到HashMap中
        var row = sourceArray.length;
        var col = sourceArray[0].length;
        var sum = 0;
        var hashMap = new HashMap<Integer, Integer>();
        for (var i = 0; i < row; i++) {
            for (var j = 0; j < col; j++) {
                if (sourceArray[i][j] != 0) {
                    hashMap.put(i, j);
                    sum++;
                }
            }
        }
        
        //4. 创建稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = sum;
        
        //5. 把不同的元素添加到稀疏数组中
        var count = 1;
        var entries = hashMap.entrySet();
        for (var next : entries) {
            var key = next.getKey();
            var value = next.getValue();
            sparseArray[count][0] = key;
            sparseArray[count][1] = value;
            sparseArray[count][2] = sourceArray[key][value];
            count++;
        }
        
        //把稀疏数组存到map.data文件中
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("C:\\Users\\Inital\\Desktop\\map.data");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //6. 看看效果
            for (int[] ints : sparseArray) {
                for (var anInt : ints) {
                    bufferedOutputStream.write(anInt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
