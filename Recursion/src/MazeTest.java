import org.junit.jupiter.api.Test;

/**
 * 迷宫回溯问题:
 * 给定一个迷宫, 设置一个出发点.
 * 使用递归找到出迷宫的路线.
 *
 * @author initial
 * @create 2021-03-25 19:48
 */
public class MazeTest {
    
    
    @Test
    public void test() {
        //初始化迷宫
        int[][] ints = setMaze();
        
        //打印我们定义好的迷宫
        System.out.print("迷宫: ");
        for (int[] anInt : ints) {
            System.out.println();
            for (int i : anInt) {
                System.out.print(i + "\t");
            }
        }
        
        System.out.println();
        setWay(ints, 1, 1);
        
        
        System.out.println("走过的迷宫:");
        for (int[] anInt : ints) {
            System.out.println();
            for (int i : anInt) {
                System.out.print(i + "\t");
            }
        }
    }
    
    
    /**
     * @param maze     给定的需要走的迷宫
     * @param startRow 给定的初始位置 行数
     * @param startCol 给定的初始位置 列数
     * @return 如果成功走出迷宫, 返回true
     */
    private boolean setWay(int[][] maze, int startRow, int startCol) {
        /*
         * 我们在这里要约定一些事情:
         *  ①: 如果小球走到了我们设置的出口位置,则返回true 这里我们设置终点为maze[6][5]
         *  ②: maze[][]中的为0的元素, 表示当前路没有走过. 可以走
         *  ③: 如果元素为1, 则表示为墙壁, 不可以走
         *  ④: 元素为2, 表示为小球走过的路径. 且不能再次走
         *  ⑤: 如果元素为3, 表示这条路已经走过,但是走不通. 往后就不能再走了
         *  ⑥: 这里我们采用的策略为:
         *          下 --> 右 --> 上 --> 左   如果全部走不通,就回溯
         *  ⑦: 也就是说,我们的小球只能走为0的路. 其余都不能走
         */
        
        
        //2. 开始找路
        //如果终点为2,证明小球已经走到了,返回true即可
        if ((maze[6][5]) == 2) {
            return true;
            
        } else {
            //如果终点不为2, 则证明还没到终点
            //我们让小球从初始地方开始走
            if (maze[startRow][startCol] == 0) {
                
                //把走过的路标识一下
                maze[startRow][startCol] = 2;
                
                // 策略设置最先向下走,所以先看看下面能走不
                if (maze[startRow + 1][startCol] == 0) {
                    //下面可以走, 就让小球向下走
                    setWay(maze, startRow + 1, startCol);
                    
                    //如果下面走不通, 小球看看右边能走不
                } else if (maze[startRow][startCol + 1] == 0) {
                    //右边可以走通,小球向右走
                    setWay(maze, startRow, startCol + 1);
                    
                    //如果右面也走不通, 小球看看上边能走不
                } else if (maze[startRow - 1][startCol] == 0) {
                    //上边可以走通,小球向上走
                    setWay(maze, startRow - 1, startCol);
                    
                    //如果上面也走不通,试试向左走
                } else if (maze[startRow][startCol - 1] == 0) {
                    //左边可以走通,小球向左走
                    setWay(maze, startRow, startCol - 1);
                } else {
                    /*
                    全部都走不通, 这是条死路, 标记一下返回
                    这里就体现了递归的回溯
                    当前小球的上下左右都走不通. 然后我们返回false.
                    这样就会返回到调用它的那个方法,回到上一个小球所在的位置.
                     */
                    maze[startRow][startCol] = 3;
                    return false;
                }
                
                
            } else {
                //如果当前这个点不为0, 那么它可能是1 , 2, 3 这三种状态.
                //这三种状态都不允许我们通行,所以返回false.
                //告诉调用我们的这个方法,这个点走不通.
                return false;
            }
        }
        
        
        return false;
    }
    
    
    /**
     * 设置一个迷宫.
     *
     * @return int[][] 返回我们设置好的迷宫
     */
    private int[][] setMaze() {
        //1. 首先设置迷宫的大小 这里设置成8*7
        int[][] maze = new int[8][7];
        
        //2. 设置迷宫的墙壁. 即不可以走的点.
        //这里规定, 如果为1, 则为墙壁
        
        int i = 0;
        while (i < 7) {
            maze[0][i] = 1;
            maze[7][i] = 1;
            i++;
        }
        
        i = 0;
        while (i < maze.length) {
            maze[i][0] = 1;
            maze[i][6] = 1;
            i++;
        }
        
        i = 0;
        while (i < 4) {
            maze[5][i] = 1;
            i++;
        }
        
        return maze;
    }
    
    
}
