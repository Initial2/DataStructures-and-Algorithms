package DynamicProgramming;

/**
 * 使用动态规划算法, 解决01背包问题
 *
 * @author initial
 * @create 2021-04-15 14:43
 */
public class PackageProblem {
    
    /**
     * 存放商品重量
     */
    private final int[] goodsWeight = new int[]{1, 4, 3};
    
    /**
     * 存放商品价格
     */
    private final int[] goodsPrice = new int[]{1500, 3000, 2000};
    
    /**
     * 背包重量
     */
    private final int packageWeight = 4;
    
    
    /**
     * 存放推算结果的表
     */
    private final int[][] table = new int[goodsWeight.length + 1][packageWeight + 1];
    
    
    /**
     * 用于存放, 每一种解法都放入了那些商品.
     */
    private final int[][] path = new int[goodsWeight.length + 1][packageWeight + 1];
    
    
    public int getResult() {
      
        /*
        第一行第一列.我们默认都为0 ,所以不做处理.
         */
        
        //跳过table第一行 i从1开始, 表示从第一个商品开始
        for (int i = 1; i < table.length; i++) {
            //跳过table第一列
            for (int weight = 1; weight < table[i].length; weight++) {
                
                //如果当前商品重量 > 包裹当前最大容量. 那么当前商品就放不进去.
                if (goodsWeight[i - 1] > weight) {
                    //就把最优解设置为,还没有这个商品时,在此包裹当前最大容量时的最优解.
                    table[i][weight] = table[i - 1][weight];
                    
                } else {
                    /*
                        如果当前商品重量 <= 包裹当前最大容量
                        需要进行判断, 是把它放进去好, 还是不放进去好.
                        也就是求: (当前商品的价值+ 背包空余重量的最优解) 和 没有该商品时,背包在当前重量下的最优解 的最大值
                        
                       ①:  goodsPrice[i - 1] 就是当前商品的价值.
                       ②:  table[i - 1][weight - goodsWeight[i - 1]] 就是当我们放入当前商品时, 如果还有空余空间.
                                     那么就找到没有此商品时, 此空余空间的最优解.  因为商品只能放一次,所以向上一行找.
                       ③: table[i - 1][weight]  就是没有该商品时,背包在当前重量下的最优解.
                       
                       对他们求最大值,就能取得该商品在此重量下的最优解
                     */
                    
                    //如果: 没有该商品时,背包在当前重量下的最优解. <  当前商品的价值 + 放入当前商品时,剩余空间的最优解
                    if (table[i - 1][weight] < (goodsPrice[i - 1] + table[i - 1][weight - goodsWeight[i - 1]])) {
                        //那么当前重量的最优解为 当前商品的价值 + 放入当前商品时,剩余空间的最优解.
                        table[i][weight] = goodsPrice[i - 1] + table[i - 1][weight - goodsWeight[i - 1]];
                        
                        //同时记录下来, 第i个商品, 在重量为weight的情况下, 是最优解,被加入背包
                        path[i][weight] = 1;
                    } else {
                        
                        //反之 当前重量的最优解为 就是没有该商品时,背包在当前重量下的最优解.
                        table[i][weight] = table[i - 1][weight];
                    }
                    
                }
                
            }
        }
        
        //倒序遍历path数组, 找出最优解都加入了什么商品
        //最大行索引, 这个行就对应的我们再添加时的商品索引, 初始值是最后一个商品
        int maxRowIndex = path.length - 1;
        //最大列索引,  这个列就对应的我们再添加时的当前背包重量, 初始值是背包最大重量
        int maxColIndex = path[0].length - 1;
        
        while (maxColIndex > 0 && maxRowIndex > 0) {
            //如果值为1, 证明 maxRowIndex 这个商品在背包重量为 maxColIndex 时被加入到背包了
            if (path[maxRowIndex][maxColIndex] == 1) {
                System.out.println("第" + maxRowIndex + "个商品,被加入到背包.");
                //当此商品被加入到背包时, 背包的剩余空间为:
                // goodsWeight[maxRowIndex - 1] 代表, 第maxRowIndex商品的重量.
                // 由于goodsWeight[]下标从0开始存放. 所以索引-1.
                //maxColIndex - goodsWeight[maxRowIndex - 1] 就是剩余重量
                maxColIndex = maxColIndex - goodsWeight[maxRowIndex - 1];
            }
            
            /*
             然后maxRowIndex--. 向上一行找, 也就是
             去找添加了当前商品后, 剩余背包重量对应的最优解都添加了什么商品.
             */
            maxRowIndex--;
        }
        
        
        //此时, table中最后一个元素,一定是最优解.
        return table[goodsWeight.length][packageWeight];
        
        
    }
    
}
