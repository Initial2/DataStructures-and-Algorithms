import org.junit.jupiter.api.Test;


class Solution {
    @Test
    public void test() {
        QueenTest queenTest = new QueenTest();
        queenTest.placeQueen(0);
        
        
    }
    
}


/**
 * 八皇后问题
 * 递归实现
 * <p>
 * 这里采用的是8*8的棋盘.
 *
 * @author initial
 * @create 2021-03-26 13:19
 */
public class QueenTest {
    
    /*
    设置皇后数量为8
     */
    final int QUEEN = 8;
    
    /*
    定义一个数组,用来存放皇后放置的结果
     */
    
    int[] array = new int[8];
    
    
    /**
     * 放置皇后
     *
     * @param num 要放置的第几个皇后
     */
    public void placeQueen(int num) {
        //如果放置的数量已经=8了. 证明已经结束了,直接打印结果. 然后进行下一次递归
        if (num == QUEEN) {
            System.out.println();
            for (int i : array) {
                System.out.print(i + "  ");
            }
            return;
        }
        
        
        /*
              依次放入皇后.
             注意:  这个for循环是获取皇后从第一行的第一列,
                   一直到第一行的最后一列. 所有的可行解法.
                   也就是说获取所有的摆放方法.
                   当i = 0时, 穷举在第一个位置上所有可摆放的方法. 以此类推
                   当i= 7时. 我们就把皇后所有可摆放的方法全部穷举完毕
         */
        for (int i = 0; i < QUEEN; i++) {
            //每次都先把皇后放在每行的第一列,如果不合适则向右移一列
            array[num] = i;
            
            //然后判断,当前位置是否冲突
            if (!checkPos(num)) {
                //如果不冲突,接着进行下一个皇后的放置
                placeQueen(num + 1);
            }
        }
        
        
    }
    
    /**
     * 判断当前皇后的位置是否合适
     *
     * @param num 给定的皇后
     * @return 如果冲突返回true
     */
    private boolean checkPos(int num) {
        
        //开始遍历当前num皇后之前所有的皇后
        for (int i = 0; i < num; i++) {
            //如果列相同, 返回true
            /*
             因为我们是用一维数组来存放皇后的位置的. 例[0  4  7  5  2  6  1  3 ]
             0表示第一个皇后在第1列  4表示第2个皇后在第5列
              这个数字+1就是当前皇后的所在列, 数字所在的索引就是皇后的所在行
               所以array[i]其实就是我们每一个皇后所在的位置
               如果array[i] == array[num] 这就代表他们处于同一列
             */
            if (array[i] == array[num]) {
                return true;
            }
            
            /*
               如果在同一对角线上, 也返回true
               Math.abs(num - i) == Math.abs(array[num] - array[i])
               此方法可以检测当前皇后的两个对角线上有没有其他的皇后
             */
            if (Math.abs(num - i) == Math.abs(array[num] - array[i])) {
                return true;
            }
        }
        //如果都不冲突,那么返回false
        return false;
    }
}
