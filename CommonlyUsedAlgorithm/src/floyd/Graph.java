package floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法的图
 *
 * @author initial
 * @create 2021-04-21 14:22
 */
public class Graph {
    
    /**
     * 图的顶点集合
     */
    private final String[] vertexes;
    
    /**
     * 用于存放图的邻接矩阵.
     */
    private final int[][] matrix;
    
    /**
     * 用于存放图中每个节点的前驱顶点
     */
    private final int[][] pre_vertex;
    
    
    public Graph(String[] vertexes, int[][] matrix) {
        //初始化顶点数组
        this.vertexes = vertexes;
        //初始化图的邻接矩阵
        this.matrix = matrix;
        
        //初始化图中每个节点的前驱顶点, 默认每个节点的前驱顶点都是他自己.
        //以下标形式存储
        pre_vertex = new int[vertexes.length][vertexes.length];
        for (int i = 0; i < pre_vertex.length; i++) {
            Arrays.fill(pre_vertex[i], i);
        }
        
    }
    
    
    /**
     * 打印当前图的信息
     */
    public void showGraph() {
        System.out.println("顶点集合:");
        for (String vertex : vertexes) {
            System.out.print(vertex + "\t");
        }
        
        System.out.println("\n");
        System.out.println("最短路径集合:");
        for (int[] vertex : matrix) {
            System.out.println(Arrays.toString(vertex) + "\t");
        }
        
        System.out.println("\n");
        System.out.println("前驱顶点集合:");
        for (int[] vertex : pre_vertex) {
            System.out.println(Arrays.toString(vertex) + "\t");
        }
    }
    
    
    public String[] getVertexes() {
        return vertexes;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public int[][] getPre_vertex() {
        return pre_vertex;
    }
}
