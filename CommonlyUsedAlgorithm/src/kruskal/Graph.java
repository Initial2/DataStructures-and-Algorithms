package kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * 需要用到的图的实现
 *
 * @author initial
 * @create 2021-04-19 17:41
 */
public class Graph {
    
    /**
     * 存放图的边, 邻接矩阵
     */
    public final int[][] linkedMatrix;
    
    /**
     * 存放图的顶点数组
     */
    public final String[] vertexes;
    
    /**
     * 记录图有几条边
     */
    private int numOfEdges;
    
    
    /**
     * 给定信息,创建一个图
     *
     * @param vertex       图的每个顶点的值
     * @param linkedMatrix 初始化图的邻接矩阵
     */
    public Graph(String[] vertex, int[][] linkedMatrix) {
        this.vertexes = vertex;
        this.linkedMatrix = linkedMatrix;
    }
    
    
    /**
     * 遍历图, 获取它的所有边
     *
     * @return 返回它的所有边的总数
     */
    public int getNumOfEdges() {
        for (int i = 0; i < linkedMatrix.length; i++) {
            /*
                j = i+1 避免统计重复的边
                例:
                    A->B = 12  B->A = 12 这其实只是一条边连接AB,所以只用统计一次即可
             */
            for (int j = i + 1; j < linkedMatrix.length; j++) {
                if (linkedMatrix[i][j] != 0) {
                    numOfEdges++;
                }
            }
        }
        return numOfEdges;
        
    }
    
    
    /**
     * 根据指定的顶点, 获取它对应的vertexes[]下标.
     * 在顶点数组里面的下标. 也就是第几个顶点.
     *
     * @param vertex 指定的顶点
     * @return 获取到返回下标, 获取不到返回-1
     */
    public int getPosition(String vertex) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
        
    }
    
    
    /**
     * 获取当前图中,所有边的集合
     *
     * @return 返回当前图中, 所有边的集合.
     */
    public Edge[] getEdges() {
        //根据边的数量创建一个数组存放.
        Edge[] edges = new Edge[numOfEdges];
        //初始索引为0,从头开始存放
        int index = 0;
        
        for (int i = 0; i < linkedMatrix.length; i++) {
            //j = i+1 ,与上面的同理. 跳过重复的边
            for (int j = i + 1; j < linkedMatrix.length; j++) {
                if (linkedMatrix[i][j] != 0) {
                    edges[index] = new Edge(vertexes[i], vertexes[j], linkedMatrix[i][j]);
                    index++;
                }
            }
        }
        return edges;
        
    }
    
    /**
     * 获取下标为index的顶点,它在当前最小生成树中的终点的索引.
     *
     * @param ends  它记录个各个顶点的下一个连接顶点信息.
     *              如果ends[index] = 2.  证明index这个顶点的下一个顶点的索引为2.
     * @param index 下标为index
     * @return 返回index下标对应的顶点, 在ends中终点的索引, 如果存在,返回
     * 如果不存在, 则返回自身
     */
    public int getEndVertexIndex(int[] ends, int index) {
        /*
        如果当前顶点的下一个顶点不为0, 证明当前顶点有下一个顶点. 就一直向下找.
        如果当前顶点的下一个顶点为0,证明当前顶点就是终点, 他已经没有下一个顶点了.
         */
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
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
