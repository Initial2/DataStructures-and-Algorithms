package PrimAlgorithm;

import java.util.Arrays;

/**
 * 普利姆算法解决修路问题的图
 *
 * @author initial
 * @create 2021-04-18 14:17
 */
public class Graph {
    
    
    public final int numOfVertex;
    public final int[][] linkedMatrix;
    public final char[] vertex;
    
    
    /**
     * 给定信息,创建一个图
     *
     * @param num          图的顶点数量
     * @param vertex       图的每个顶点的值
     * @param linkedMatrix 初始化图的邻接矩阵
     */
    public Graph(int num, char[] vertex, int[][] linkedMatrix) {
        this.numOfVertex = num;
        this.vertex = vertex;
        this.linkedMatrix = linkedMatrix;
    }
    
    
    /**
     * 打印当前图的信息
     */
    public void showGraph() {
        for (int[] matrix : linkedMatrix) {
            System.out.println(Arrays.toString(matrix));
        }
    }
    
}
