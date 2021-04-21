package PrimAlgorithm;

import java.util.HashMap;

/**
 * 普利姆算法 解决修路问题中的最小生成树
 *
 * @author initial
 * @create 2021-04-18 14:22
 */
public class MinTree {
    
    
    /**
     * 获取最小生成树.
     *
     * @param graph      从graph中获取最小生成树
     * @param StartIndex 从graph图中,哪个顶点开始获取.
     */
    public HashMap<String, Integer> getMST(Graph graph, int StartIndex) {
        
        //1. 创建一个数组, 标记图中的顶点是否被访问过 默认值为0尚未被访问
        int[] isVisited = new int[graph.numOfVertex];
        
        //2. 标记当前开始节点为已访问
        isVisited[StartIndex] = 1;
        
        //3. 创建3个变量, 用来记录每一次遍历都是哪两个顶点的边,被选中.
        //以及这两个顶点的边的权值
        int rowIndex = -1;
        int colIndex = -1;
        int minWeight = 10000;
        
        
        //4. 创建一个HashMap存放结果
        HashMap<String, Integer> resultMap = new HashMap<>(20);
        
        
        //5. 开始遍历查找,
        
        /*
        根据Prim算法, 我们有N个顶点, 那么最小生成树就有N-1条边.
        所以我们只需遍历N-1次就可以了.
         */
        for (int i = 0; i < graph.numOfVertex - 1; i++) {
            
            //i1用来表示已经访问过的节点
            for (int i1 = 0; i1 < graph.numOfVertex; i1++) {
                //如果isVisited[i1] != 1 证明他不是访问过的点, 直接跳过,进行下一次查找,
                if (isVisited[i1] != 1) {
                    continue;
                }
                //i2 表示尚未被访问过的节点
                for (int i2 = 0; i2 < graph.numOfVertex; i2++) {
                    
                    //如果i1已经被访问过,i2尚未被访问. 并且 它俩之间是联通的
                    if (isVisited[i2] == 0 && graph.linkedMatrix[i1][i2] != 0) {
                        
                        //如果这两个连通的点, 它俩的边的权值, 比之前的MinWeight要小. 就更新MinWeight.
                        if (graph.linkedMatrix[i1][i2] < minWeight) {
                            minWeight = graph.linkedMatrix[i1][i2];
                            //同时记录下来是哪两个点.
                            rowIndex = i1;
                            colIndex = i2;
                        }
                        
                    }
                }
            }
            
            /*
            每经过一轮,就会找到一个最优的边.
            记录下来是哪两个顶点, 以及他们的边的权值
             */
            String twoVertex = graph.vertex[rowIndex] + "" + graph.vertex[colIndex];
            int weight = graph.linkedMatrix[rowIndex][colIndex];
            resultMap.put(twoVertex, weight);
            
            /*
            然后重新置minWeight为默认值,进行下一次查找. 以及该节点标记为已访问
            rowIndex 记录的是i1, i1代表已经访问过的节点了. 所以不需要在标记
            colIndex 记录的是i2, i2代表的是还没访问过的节点, 所以需要标记colIndex为已访问
             */
            minWeight = 10000;
            isVisited[colIndex] = 1;
        }
        
        //最后返回结果
        return resultMap;
        
    }
    
}


