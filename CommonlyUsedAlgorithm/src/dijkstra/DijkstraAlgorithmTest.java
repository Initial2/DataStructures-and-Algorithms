package dijkstra;

import org.junit.Test;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法  求最短路径
 *
 * @author initial
 * @create 2021-04-20 10:36
 */
public class DijkstraAlgorithmTest {
    Graph graph = null;
    VisitedVertex visitedVertex = null;
    
    @Test
    public void test() {
        dijkstra(2);
        showResult();
        
    }
    
    
    public void dijkstra(int startIndex) {
        //这里用N 来表示,图中的顶点是不连通的
        final int n = Integer.MAX_VALUE;
        String[] vertexes = {"A", "B", "C", "D", "E", "F", "G"};
        int[][] matrix = new int[][]{
                {n, 5, 7, n, n, n, 2},
                {5, n, n, 9, n, n, 3},
                {7, n, n, n, 8, n, n},
                {n, 9, n, n, n, 4, n},
                {n, n, 8, n, n, 5, 4},
                {n, n, n, 4, 5, n, 6},
                {2, 3, n, n, 4, 6, n}
        };
        //初始化图
        graph = new Graph(vertexes, matrix);
        //初始化顶点类
        visitedVertex = new VisitedVertex(graph.vertexes.length, startIndex);
        
        update(startIndex);
        
        //遍历所有顶点, 根据深度优先思想. 每轮都选择一个新的出发顶点.
        for (int i = 1; i < graph.vertexes.length; i++) {
            startIndex = visitedVertex.getNextIndex();
            System.out.println("本轮新出发顶点为:" + startIndex);
            update(startIndex);
        }
        
    }
    
    /**
     * 更新从 出发顶点 到 Index这个索引对应的顶点到其他顶点的距离.
     * 以及前驱顶点.
     *
     * @param index Index这个索引对应的顶点
     */
    public void update(int index) {
        
        int length = 0;
        
        //从graph.matrix[startIndex] 这一行开始遍历.
        for (int i = 0; i < graph.matrix[index].length; i++) {
            /*
            如果graph.matrix[index][i] == Integer.MAX_VALUE 证明当前顶点到i这个顶点是不通的.
            所以也就没有最短路径, 不做处理
             */
            if (graph.matrix[index][i] != Integer.MAX_VALUE) {
                /*
                因为我们是从出发顶点开始遍历的.
                 所以距离为:  从出发顶点到index这个顶点的距离 + index顶点到其他顶点的距离.
                 */
                length = visitedVertex.getDistance(index) + graph.matrix[index][i];
                //如果当前顶点没有被访问过, 并且从出发顶点到达i顶点的距离 < 之前从出发顶点到i这个顶点的距离
                if (!visitedVertex.isVisited(i) && length < visitedVertex.getDistance(i)) {
                    //就更新最短距离
                    visitedVertex.updateDistance(i, length);
                    //并且更新它的前驱顶点. 从index到i的距离 所以index是i的前驱
                    visitedVertex.updatePreVertex(i, index);
                }
            }
        }
        
        
    }
    
    
    public void showResult() {
        System.out.println("已访问顶点数组为:");
        System.out.println(Arrays.toString(visitedVertex.isVisited));
        System.out.println();
        System.out.println("已访问顶点的前驱顶点为:");
        System.out.println(Arrays.toString(visitedVertex.pre_Visited));
        System.out.println();
        System.out.println("从开始顶点到其它顶点的最短路径为:");
        System.out.println(Arrays.toString(visitedVertex.distance));
        
        
    }
    
}
