package sparsearray.test;

import org.junit.jupiter.api.Test;

/**
 * 稀疏数组--->源数组
 *
 * @author initial
 * @create 2021-03-19 15:12
 */
public class SparseArrayToSourceArray {
    @Test
    public void test() {
        //首先获取我们转换后的稀疏数组
        int[][] sparseArray = new SourceArrayToSparseArray().sourceArrayToSparseArray();
        
        //1.读取稀疏数组第一行, 创建对应行列数的二维数组
        var row = sparseArray[0][0];
        var col = sparseArray[0][1];
        int[][] recoverArray = new int[row][col];
        
        //2. 然后遍历稀疏数组, 把读取出来的不同元素,添加到恢复后的数组中
        //从第一行开始读.
        for (var i = 1; i < sparseArray.length; i++) {
            var row1 = sparseArray[i][0];  //获取该元素原来的所在行
            var col1 = sparseArray[i][1];  //获取该元素原来的所在列
            recoverArray[row1][col1] = sparseArray[i][2];  //修改对应位置上的值
            
        }
        
        //3. 最后打印看看效果
        System.out.println("恢复后的数组为: ");
        for (int[] ints : recoverArray) {
            System.out.println();
            for (var anInt : ints) {
                System.out.print(anInt + "\t\t");
            }
        }
    }
}
