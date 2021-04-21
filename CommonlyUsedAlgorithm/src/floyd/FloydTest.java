package floyd;

import org.junit.Test;

/**
 * @author initial
 * @create 2021-04-21 14:30
 */
public class FloydTest {
    @Test
    public void test() {
        String[] vertex = {"A", "B", "C", "D", "E", "F", "G"};
        //用N来表示两个顶点不连通  n = 1000.
        final int n = 1000;
        //用0来表示当前顶点没有最短距离, 例如AA, BB, CC这些顶点自身到自身没有最短距离
        int[][] matrix = new int[][]{
                {0, 5, 7, n, n, n, 2},
                {5, 0, n, 9, n, n, 3},
                {7, n, 0, n, 8, n, n},
                {n, 9, n, 0, n, 4, n},
                {n, n, 8, n, 0, 5, 4},
                {n, n, n, 4, 5, 0, 6},
                {2, 3, n, n, 4, 6, 0}
        };
        
        Floyd.floyd(vertex, matrix);
        
    }
}
