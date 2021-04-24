package sparsearray;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author initial
 * @create 2021-04-24 15:05
 */
public class SparseArrayTest {
    
    @Test
    public void test() {
        int[][] matrix = {
                {0, 0, 0, 0, 0, 16, 14},
                {12, 0, 10, 0, 0, 7, 0},
                {0, 10, 0, 3, 5, 6, 0},
                {0, 0, 3, 0, 4, 0, 0},
                {0, 0, 5, 4, 0, 2, 8},
                {16, 7, 6, 0, 2, 0, 9},
                {14, 0, 0, 0, 0, 0, 0}
        };
        
        int[][] matrix1 = SparseArray.zipMatrix(matrix, 0);
        
        System.out.println("压缩后的稀疏矩阵为: ");
        for (int[] ints : matrix1) {
            System.out.println(Arrays.toString(ints));
        }
        
        int[][] ints1 = SparseArray.recoverSparseArray(matrix1, 0);
        
        System.out.println("恢复后的矩阵为: ");
        for (int[] ints : ints1) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
