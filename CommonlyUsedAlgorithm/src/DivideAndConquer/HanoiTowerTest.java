package DivideAndConquer;

import org.junit.Test;

/**
 * 使用分治算法, 解决汉诺塔问题
 *
 * @author initial
 * @create 2021-04-15 13:41
 */
public class HanoiTowerTest {
    
    
    @Test
    public void test() {
        hanoi(5, 'A', 'B', 'C');
    }
    
    
    /**
     * 汉诺塔问题实现.
     * 使用分治算法
     *
     * @param num 汉诺塔的盘子数量
     * @param a   A柱子
     * @param b   B柱子
     * @param c   C柱子
     */
    public void hanoi(int num, char a, char b, char c) {
        //1. 如果只有一个盘,直接A->C
        if (num == 1) {
            System.out.println("第" + num + "个盘子,从: " + a + "->" + c);
        } else {
            //如果大于1个
            // 首先把最下面那个上面的所有盘子移动到B, A->B, 移动过程需要借助C
            hanoi(num - 1, a, c, b);
            //然后再把最后一个盘子移动到C
            System.out.println("第" + num + "个盘子,从: " + a + "->" + c);
            
            //最后把B柱子上所有盘子移动到C B->C, 移动过程需要借助A
            hanoi(num - 1, b, a, c);
        }
        
    }
    
}
