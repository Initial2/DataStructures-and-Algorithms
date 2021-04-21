package graph.test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图的简单实现,
 * 使用邻接矩阵
 *
 * @author initial
 * @create 2021-04-13 20:58
 */
public class Graph<T> {
    /**
     * 存放顶点的集合
     */
    private final ArrayList<T> vertexList = new ArrayList<>();
    
    /**
     * 存放图的邻接矩阵
     */
    private final int[][] matrix;
    
    /**
     * 记录当前顶点在遍历时,是否已经访问过
     */
    private boolean[] isVisited;
    
    /**
     * 记录当前图有几条边
     */
    private int numOfEdges = 0;
    
    
    /**
     * 根据顶点个数,创建邻接矩阵
     *
     * @param num 需要存放的顶点个数
     */
    public Graph(int num) {
        this.matrix = new int[num][num];
        this.isVisited = new boolean[num];
    }
    
    
    /**
     * 图的深度优先遍历
     * 包装方法
     */
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
        //每次访问过后, 把记录否被访问过得数组重新置为默认值.
        isVisited = new boolean[vertexList.size()];
    }
    
    
    /**
     * 图的广度优先遍历
     * 包装方法
     *
     * @param index 开始遍历的顶点索引
     */
    public void bfs(int index) {
        bfs(isVisited, index);
        //每次访问过后, 把记录否被访问过得数组重新置为默认值.
        isVisited = new boolean[vertexList.size()];
    }
    
    
    /**
     * 图的广度优先算法
     *
     * @param isVisited 存放顶点是否被访问过得数组.  true为访问过.
     * @param i         开始遍历的顶点的索引
     */
    private void bfs(boolean[] isVisited, int i) {
        //1. 访问初始节点i,并把初始节点i标记为已访问
        System.out.print(vertexList.get(i) + "->");
        isVisited[i] = true;
        
        //2. 当前顶点i的索引入队列
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(vertexList.size());
        arrayQueue.add(i);
        
        //3.开始重复判断
        //当前队列不为空时,就一直执行
        while (!arrayQueue.isEmpty()) {
            //4. 取出队列头元素
            int head = arrayQueue.get();
            
            //5.查找此节点的第一个邻接结点
            int neighbor = getFirstNeighbor(head);
            
            while (neighbor != -1) {
                //6. 如果当前节点的邻接结点存在,且尚未被访问
                if (!isVisited[neighbor]) {
                    //7. 访问此节点,并且标记为已访问, 入队列
                    System.out.print(vertexList.get(neighbor) + "->");
                    isVisited[neighbor] = true;
                    arrayQueue.add(neighbor);
                }
                //9. 如果当前节点的邻接结点存在,且已经被访问,
                //或者已经执行了7的步骤.. 就获取它的下一个邻接结点
                neighbor = getNextNeighbor(head, neighbor);
            }
        }
        
    }
    
    
    /**
     * 深度优先遍历
     *
     * @param isVisited 存放顶点是否被访问过得数组.  true为访问过.
     * @param index     开始遍历的顶点
     */
    private void dfs(boolean[] isVisited, int index) {
        //1. 首先打印当前顶点
        System.out.print(vertexList.get(index) + "->");
        
        //2. 将当前节点标记为已经访问过
        isVisited[index] = true;
        
        //3. 查找当前节点的第一个邻接结点
        int neighborIndex = getFirstNeighbor(index);
        
        //4.如果存在
        while (neighborIndex != -1) {
            //如果存在,并且还没有访问过. 就递归访问
            if (!isVisited[neighborIndex]) {
                dfs(isVisited, neighborIndex);
            } else {
                //如果存在,并且已经访问过. 就访问当前节点的下一个邻接结点
                neighborIndex = getNextNeighbor(index, neighborIndex);
            }
        }
        
    }
    
    /**
     * 查找当前节点的下一个邻接结点
     *
     * @param index              当前节点的索引
     * @param firstNeighborIndex 当前节点的第一个邻接结点索引
     * @return 如果存在, 返回下标. 不存在返回-1
     */
    private int getNextNeighbor(int index, int firstNeighborIndex) {
        //从当前节点的第一个邻接结点的下一个位置开始找.
        for (int i = firstNeighborIndex + 1; i < vertexList.size(); i++) {
            if (matrix[index][i] > 0) {
                return i;
            }
        }
        return -1;
        
    }
    
    
    /**
     * 查找当前节点的第一个邻接结点
     *
     * @param index 当前节点索引
     * @return 如果存在, 返回下标. 不存在返回-1
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (matrix[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }
    
    
    /**
     * 向图中,插入一个顶点
     *
     * @param value 要插入的顶点的值
     */
    public void insertVertex(T value) {
        vertexList.add(value);
    }
    
    
    /**
     * 建立两个顶点的链接. 并且指定链接的权值
     *
     * @param vertex1 需要建立链接的顶点索引(在vertexList中的索引)
     * @param vertex2 需要建立链接的顶点索引(在vertexList中的索引)
     * @param weight  需要建立链接的权值
     */
    public void setVertexEdge(int vertex1, int vertex2, int weight) {
        //由于这个图是无向图. 所以里面的边都是无向边.
        //所以要考虑两个方向.
        matrix[vertex1][vertex2] = weight;
        matrix[vertex2][vertex1] = weight;
        //每建立一个链接, 边数+1;
        numOfEdges++;
    }
    
    
    /**
     * 获取当前图有几个节点
     *
     * @return 返回顶点个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }
    
    /**
     * 获取当前图中有几条边
     *
     * @return 返回当前图中有几条边
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }
    
    /**
     * 获取两个顶点的直接链接的边的权值
     *
     * @param vertex1 需要获取链接的顶点索引1(在vertexList中的索引)
     * @param vertex2 需要获取链接的顶点索引2(在vertexList中的索引)
     * @return 两个顶点的直接链接的边的权值
     */
    public int getWeightOfEdge(int vertex1, int vertex2) {
        return matrix[vertex1][vertex2];
    }
    
    
    /**
     * 打印当前图的信息
     */
    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
