package kruskal;

/**
 * 克鲁斯卡尔算法
 * 需要用到的边的类
 *
 * @author initial
 * @create 2021-04-19 17:42
 */
public class Edge implements Comparable<Edge> {
    
    /**
     * 该边的开始顶点
     */
    public final String startVertex;
    
    /**
     * 该边的截止顶点
     */
    final String endVertex;
    
    /**
     * 该边的权值
     */
    final int weight;
    
    
    public Edge(String startVertex, String endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }
    
    
    /**
     * 重写compareTo方法, 实现按照权值大小进行排序
     */
    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.weight, e.weight);
    }
    
    
    @Override
    public String toString() {
        return "Edge: " + startVertex + "-->" + endVertex + "  weight=" + weight + "\n";
    }
}
