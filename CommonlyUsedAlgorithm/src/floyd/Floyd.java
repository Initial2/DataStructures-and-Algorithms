package floyd;

import java.util.Arrays;

/**
 * @author initial
 * @create 2021-04-21 14:29
 */
public class Floyd {
    
    
    public static void floyd(String[] vertex, int[][] matrix) {
        Graph graph = new Graph(vertex, matrix);
        int length;
        // 中间顶点从0(A)开始
        for (int midVertexIndex = 0; midVertexIndex < graph.vertexes.length; midVertexIndex++) {
            //开始顶点从0(A)开始
            for (int startVertexIndex = 0; startVertexIndex < graph.vertexes.length; startVertexIndex++) {
                //结束顶点从0(A)开始
                for (int endVertexIndex = 0; endVertexIndex < graph.vertexes.length; endVertexIndex++) {
                    //如果startVertexIndex == endVertexIndex就是出现了从开始顶点经过中间顶点再到开始顶点之类的情况
                    // 例如: A-B-A, B-A-B, C-A-C A-C-A 这些我们都可以不用处理, 因为没有意义.
                    if (startVertexIndex == endVertexIndex) {
                        continue;
                    }
                    
                    //length就应该 =  从开始到中间的距离 + 从中间到结尾的距离
                    length = graph.matrix[startVertexIndex][midVertexIndex] + graph.matrix[midVertexIndex][endVertexIndex];
                    //如果当前length的距离, 小于从开始到结束直达的距离.
                    if (length < graph.matrix[startVertexIndex][endVertexIndex]) {
                        //就更新最短距离
                        graph.matrix[startVertexIndex][endVertexIndex] = length;
                        //然后更新前驱节点
                        graph.pre_vertex[startVertexIndex][endVertexIndex] = graph.pre_vertex[midVertexIndex][endVertexIndex];
                    }
                    
                }
            }
        }
        
        graph.showGraph();
        
    }
    
    
    /**
     * 弗洛伊德算法的图
     *
     * @author initial
     * @create 2021-04-21 14:22
     */
    static class Graph {
        
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
        
        
    }
    
    
}
