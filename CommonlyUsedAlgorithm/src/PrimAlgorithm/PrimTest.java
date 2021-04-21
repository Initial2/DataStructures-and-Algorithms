package PrimAlgorithm;

import org.junit.Test;

import java.util.HashMap;

/**
 * 普利姆算法 解决修路问题
 *
 * @author initial
 * @create 2021-04-18 14:32
 */
public class PrimTest {
    
    
    @Test
    public void test() {
        
        //顶点集合
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //顶点个数
        int num = vertex.length;
        //顶点的连接矩阵
        int[][] linkedMatrix = new int[][]{
                {0, 5, 7, 0, 0, 0, 2},
                {5, 0, 0, 9, 0, 0, 3},
                {7, 0, 0, 0, 8, 0, 0},
                {0, 9, 0, 0, 0, 4, 0},
                {0, 0, 8, 0, 0, 5, 4},
                {0, 0, 0, 4, 5, 0, 6},
                {2, 3, 0, 0, 4, 6, 0}
        };
        
        //初始化图
        Graph graph = new Graph(num, vertex, linkedMatrix);
        
        //创建最小生成树对象, 获取最小生成树
        MinTree minTree = new MinTree();
        HashMap<String, Integer> mst = minTree.getMST(graph, 0);
        
        
        //遍历获得的结果
        for (String s : mst.keySet()) {
            System.out.print(s + "-->");
            System.out.println(mst.get(s));
        }
        
        
    }
    
    
}
