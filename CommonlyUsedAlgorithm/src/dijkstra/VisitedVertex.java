package dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 已访问的顶点类
 *
 * @author initial
 * @create 2021-04-20 16:22
 */
public class VisitedVertex {
    
    /**
     * 记录各个顶点是否访问过, 1表示访问过, 0表示没有访问
     */
    public final int[] isVisited;
    
    /**
     * 记录每一个访问过的顶点的前驱顶点.
     */
    public final int[] pre_Visited;
    
    /**
     * 记录每一个顶点到其它顶点所需要走的距离. (最短距离)
     * 会在运行过程中, 动态更新
     */
    public final int[] distance;
    
    /**
     * @param length     总共有几个顶点.
     * @param startIndex 从哪个顶点开始计算最短路径.
     */
    public VisitedVertex(int length, int startIndex) {
        // 初始化已访问数组, 默认所有顶点都没访问, 将startIndex设置为已访问
        this.isVisited = new int[length];
        this.isVisited[startIndex] = 1;
        
        // 初始化已访问顶点的前驱顶点数组, 默认所有顶点都还没前驱
        this.pre_Visited = new int[length];
        
        // 初始化startIndex顶点到其它顶点所需要走的距离, 默认距离都为最大值.
        //这个距离将会在后续计算中,不断更新
        this.distance = new int[length];
        Arrays.fill(this.distance, Integer.MAX_VALUE);
        
        
        //该顶点到自身的距离为0
        this.distance[startIndex] = 0;
        
    }
    
    
    /**
     * 判断当前顶点是否已经访问过
     *
     * @param index 当前顶点的索引
     * @return 访问过返回true, 否则返回false.
     */
    public boolean isVisited(int index) {
        return isVisited[index] == 1;
    }
    
    
    /**
     * 更新从出发顶点到index 这个顶点的距离
     *
     * @param index index索引所对应的顶点
     * @param len   从出发顶点到index索引所对应的顶点的距离
     */
    public void updateDistance(int index, int len) {
        distance[index] = len;
    }
    
    
    /**
     * 更新当前index索引所对应的顶点的前驱顶点为 pre索引对应的顶点.
     *
     * @param index 前index索引所对应的顶点
     * @param pre   pre索引对应的顶点
     */
    public void updatePreVertex(int index, int pre) {
        pre_Visited[index] = pre;
        
        
    }
    
    
    /**
     * 返回从当前顶点到 index索引所对应的顶点的距离
     *
     * @param index index索引所对应的顶点
     * @return 返回从当前顶点到 index索引所对应的顶点的距离
     */
    public int getDistance(int index) {
        return distance[index];
    }
    
    /**
     * 获取下一个开始处理的顶点
     *
     * @return 下一个开始处理的顶点的索引
     */
    public int getNextIndex() {
        int minDistance = Integer.MAX_VALUE;
        int index = 0;
        
        // 遍历存放已访问节点的数组,找到下一个顶点的索引
        for (int i = 0; i < this.isVisited.length; i++) {
            if (!this.isVisited(i) && this.getDistance(i) < minDistance) {
                minDistance = this.getDistance(i);
                index = i;
            }
        }
        //把该顶点标记为已访问
        this.isVisited[index] = 1;
        return index;
        
    }
    
    
}
