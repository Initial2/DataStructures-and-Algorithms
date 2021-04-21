package graph.test;

/**
 * @author initial
 * @create 2021-04-13 21:12
 */
public class GraphTest {
    public static void main(String[] args) {
        
        Graph<Integer> graph = new Graph<>(8);
        //添加顶点
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertVertex(3);
        graph.insertVertex(4);
        graph.insertVertex(5);
        graph.insertVertex(6);
        graph.insertVertex(7);
        graph.insertVertex(8);
        
        //添加边
        graph.setVertexEdge(0, 1, 1);
        graph.setVertexEdge(0, 2, 1);
        graph.setVertexEdge(1, 3, 1);
        graph.setVertexEdge(1, 4, 1);
        graph.setVertexEdge(3, 7, 1);
        graph.setVertexEdge(4, 7, 1);
        graph.setVertexEdge(2, 5, 1);
        graph.setVertexEdge(2, 6, 1);
        graph.setVertexEdge(5, 6, 1);
        
        
        graph.showGraph();
        System.out.print("深度优先:");
        graph.dfs();
        System.out.println();
        System.out.print("广度优先:");
        graph.bfs(0);
        
        
    }
}
