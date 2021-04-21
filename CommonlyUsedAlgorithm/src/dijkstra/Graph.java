package dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法 求最短路径
 * 用到的图
 *
 * @author initial
 * @create 2021-04-20 10:34
 */
public class Graph {
    
    public final String[] vertexes;
    public final int[][] matrix;
    
    
    public Graph(String[] vertexes, int[][] matrix) {
        this.vertexes = vertexes;
        this.matrix = matrix;
    }
    
    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
    
}
