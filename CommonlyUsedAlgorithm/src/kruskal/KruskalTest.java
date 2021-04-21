package kruskal;

import org.junit.Test;

import java.util.Arrays;


/**
 * 克鲁斯卡尔算法的实现.
 * 解决公交问题
 *
 * @author initial
 * @create 2021-04-19 17:42
 */
public class KruskalTest {
    
    
    @Test
    public void test() {
        
        Edge[] kruskal = kruskal();
        System.out.println(Arrays.toString(kruskal));
        
        
    }
    
    public Edge[] kruskal() {
        
        //1. 首先初始化图
        String[] station = new String[]{"A", "B", "C", "D", "E", "F", "G"};
        int[][] matrix = new int[][]{
                {0, 12, 0, 0, 0, 16, 14},
                {12, 0, 10, 0, 0, 7, 0},
                {0, 10, 0, 3, 5, 6, 0},
                {0, 0, 3, 0, 4, 0, 0},
                {0, 0, 5, 4, 0, 2, 8},
                {16, 7, 6, 0, 2, 0, 9},
                {14, 0, 0, 0, 8, 9, 0}
        };
        Graph graph = new Graph(station, matrix);
        
        //2. 获取图中都有多少条边
        int numOfEdges = graph.getNumOfEdges();
        
        //3. 获取图中边的集合
        Edge[] edges = graph.getEdges();
        
        //4. 对边进行排序,从小到大
        Arrays.sort(edges);
        
        //5. 创建记录所有顶点的终点的数组, 7个顶点,长度就是7
        int[] ends = new int[7];
        
        
        //6.创建一个数组存放结果, 也就是最小生成树.  有7个顶点,所以最小生成树只有6条边.
        Edge[] result = new Edge[6];
        //从0开始存放
        int resultIndex = 0;
        
        //7. 开始进行计算
        //遍历所有的边
        for (int i = 0; i < numOfEdges; i++) {
            //7.1 首先获取当前最小的那一条边的两个顶点的索引.
            int startVertex = graph.getPosition(edges[i].startVertex);
            int endVertex = graph.getPosition(edges[i].endVertex);
            
            //7.2 计算这两个顶点的终点.
            int end1 = graph.getEndVertexIndex(ends, startVertex);
            int end2 = graph.getEndVertexIndex(ends, endVertex);
            
            //7.3 如果两个顶点的终点不相同, 则添加
            if (end1 != end2) {
                result[resultIndex] = edges[i];
                resultIndex++;
                //然后更新当前节点的终点
                ends[end1] = end2;
            }
        }
        
        return result;
    }
    
    
}
