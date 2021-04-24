package sparsearray;


import java.util.Arrays;

/**
 * 二维数组--->稀疏数组
 *
 * @author initial
 * @create 2021-03-19 15:38
 */
public class SparseArray {
    
    /**
     * 压缩二维矩阵. 使用稀疏数组存储
     *
     * @param matrix 需要压缩的二维矩阵, 压缩后,不会改变原矩阵
     * @param num    需要压缩的二维矩阵中,重复次数最多的元素.
     * @return 返回经过压缩后的稀疏矩阵.
     */
    public static int[][] zipMatrix(int[][] matrix, int num) {
        
        if (matrix == null) {
            throw new RuntimeException("矩阵为空");
        }
        
        //遍历matrix,获取ElementsNum的个数
        int elementsNum = 0;
        for (int[] value : matrix) {
            for (int i : value) {
                if (i != num) {
                    elementsNum++;
                }
            }
        }
        
        //初始化稀疏矩阵
        int[][] sparseArray = new int[elementsNum + 1][3];
        sparseArray[0][0] = matrix.length;
        sparseArray[0][1] = matrix[0].length;
        sparseArray[0][2] = elementsNum;
        
        //向稀疏矩阵中添加元素
        int index = 1;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] != num) {
                    sparseArray[index][0] = row;
                    sparseArray[index][1] = col;
                    sparseArray[index][2] = matrix[row][col];
                    index++;
                }
            }
        }
        
        return sparseArray;
    }
    
    
    /**
     * 把稀疏矩阵重新转换为原矩阵
     *
     * @param sparseArray 要转换的稀疏矩阵
     * @param num         矩阵的填充元素.
     * @return 返回恢复过后的原矩阵
     */
    public static int[][] recoverSparseArray(int[][] sparseArray, int num) {
        
        //1.初始化原矩阵
        int[][] matrix = new int[sparseArray[0][0]][sparseArray[0][1]];
        
        for (int[] ints : matrix) {
            Arrays.fill(ints, num);
        }
        
        for (int row = 1; row < sparseArray.length; row++) {
            //sparseArray[row][0] 每个稀疏元素的所在行
            //sparseArray[row][1] 每个稀疏元素的所在列
            //sparseArray[row][2]; 每个稀疏元素的值
            matrix[sparseArray[row][0]][sparseArray[row][1]] = sparseArray[row][2];
            
            
        }
        return matrix;
        
    }
    
    
}
