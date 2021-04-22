package horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 马踏棋盘问题
 * 使用递归+回溯算法实现
 *
 * @author initial
 * @create 2021-04-22 14:15
 */
public class HorseChessBoard {
    
    /**
     * 棋盘的行数
     */
    private final int row;
    
    /**
     * 棋盘的列数
     */
    private final int col;
    
    /**
     * 需要走的棋盘
     */
    private final int[][] chessBoard;
    
    /**
     * 判断棋盘中各个顶点是否被访问过
     */
    private final boolean[][] isVisited;
    
    
    /**
     * 标记当前马儿是否已经完成任务
     */
    private boolean isFinished;
    
    
    /**
     * 根据传入的参数,初始化棋盘
     *
     * @param row 棋盘的行数
     * @param col 棋盘的列数
     */
    public HorseChessBoard(int row, int col) {
        this.row = row;
        this.col = col;
        this.chessBoard = new int[row][col];
        isVisited = new boolean[row][col];
    }
    
    
    public void start(int row, int col) {
        System.out.println("开始计算");
        start(row, col, 1);
        
        //打印结果
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }
    
    
    /**
     * @param row  马儿的起始行
     * @param col  马儿的起始列
     * @param step 马儿已经走了几步
     */
    private void start(int row, int col, int step) {
        //1. 首先把当前马儿的位置设置为已走过
        isVisited[row][col] = true;
        //其次在棋盘中,标记当前马儿是第几步走到该点的
        chessBoard[row][col] = step;
        
        //2. 获取当前马儿所在的位置,还能走哪些点
        ArrayList<Point> reachablePoint = getReachablePoint(new Point(row, col));
        
        //然后使用贪心算法,对这些点进行排序. 每次都走尽可能产生最小回溯的那一个点
        sortPoints(reachablePoint);
        
        //3. 遍历这些点
        while (!reachablePoint.isEmpty()) {
            //4. 每次取出第一个点,开始判断
            Point point = reachablePoint.remove(0);
            //判断该点是否已经走过了
            if (!isVisited[point.x][point.y]) {
                //如果没有走过, 就递归走这个点.
                start(point.x, point.y, step + 1);
            }
        }
        
        //5. 最后判断
        /*
        如果当我们把马儿以(row,col)这个点开始,所有能走的点,都走了一遍,
        然后发现, step < (this.row * this.col) 所走的步数,小于棋盘的点的个数
                并且 isFinished 是false.
        就证明马尔从(row,col)这个点, 开始周游,是不可能完成任务的.
         所以回溯上到一个点,去从别的点开始
         */
        if (step < (this.row * this.col) && !isFinished) {
            isVisited[row][col] = false;
            chessBoard[row][col] = 0;
        } else {
            //如果完成了任务. isFinished就是true
            isFinished = true;
        }
        
    }
    
    
    /**
     * 根据传入的马的当前位置, 计算马还能走哪些点.
     *
     * @param curPoint 马的当前位置
     * @return 返回马能走的所有位置的集合
     */
    private ArrayList<Point> getReachablePoint(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        
        //看看能不能向上走两格
        if (curPoint.x - 2 >= 0) {
            if (curPoint.y - 1 >= 0) {
                points.add(new Point(curPoint.x - 2, curPoint.y - 1));
            }
            
            if (curPoint.y + 1 < col) {
                points.add(new Point(curPoint.x - 2, curPoint.y + 1));
            }
        }
        
        //看看能不能向上走一格
        if (curPoint.x - 1 >= 0) {
            if (curPoint.y - 2 >= 0) {
                points.add(new Point(curPoint.x - 1, curPoint.y - 2));
            }
            
            if (curPoint.y + 2 < col) {
                points.add(new Point(curPoint.x - 1, curPoint.y + 2));
            }
        }
        
        //看看能不能向下走一格
        if (curPoint.x + 1 < row) {
            if (curPoint.y - 2 >= 0) {
                points.add(new Point(curPoint.x + 1, curPoint.y - 2));
            }
            
            if (curPoint.y + 2 < col) {
                points.add(new Point(curPoint.x + 1, curPoint.y + 2));
            }
        }
        
        //看看能不能向下走2格
        if (curPoint.x + 2 < row) {
            if (curPoint.y - 1 >= 0) {
                points.add(new Point(curPoint.x + 2, curPoint.y - 1));
            }
            
            if (curPoint.y + 1 < col) {
                points.add(new Point(curPoint.x + 2, curPoint.y + 1));
            }
        }
        
        return points;
        
    }
    
    
    /**
     * 使用贪心算法,进行优化.  尽可能减少回溯
     * 原理:
     * 每次再获取到马儿的所有下一步可走的点时, 再进行一次排序.
     * 把马儿的所有下一步可走的点, 再分别往下走一步.
     * 然后分别计算出 所有下一步可走的点再分别往下走一步, 可以走的顶点个数.
     * 然后按照非递增的顺序排序.
     */
    private void sortPoints(ArrayList<Point> points) {
        points.sort((o1, o2) -> {
            int num1 = getReachablePoint(o1).size();
            int num2 = getReachablePoint(o2).size();
            return Integer.compare(num1, num2);
            
        });
    }
    
    
}
