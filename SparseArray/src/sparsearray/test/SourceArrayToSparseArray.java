package sparsearray.test;

import org.junit.jupiter.api.Test;

/**
 * 二维数组 --->稀疏数组
 *
 * @author initial
 * @create 2021-03-19 14:24
 */
public class SourceArrayToSparseArray {
    
    // 二维数组--->稀疏数组
    @Test
    public int[][] sourceArrayToSparseArray() {
        var startTime = System.currentTimeMillis();
        //1.  准备一个二维数组
        // 首先创建一个二维数组. 如果我们不添加值,int型默认值为0
        //所以数组中的元素默认值都是0
        int[][] sourceArray = new int[11][11];
        
        //向二维数组中添加不同的元素, 这样一来,一个原数组就创建好了
        sourceArray[2][4] = 5;
        sourceArray[4][4] = 6;
        sourceArray[6][8] = 7;
        sourceArray[7][3] = 8;
        
        //预览一下我们创建的二维数组
       /* System.out.println("二维数组为:");
        for (int[] ints : sourceArray) {
            System.out.println();
            for (var anInt : ints) {
                System.out.print(anInt + "\t\t");
            }
        }*/
        
        
        //2. 开始进行转换
        //首先遍历原数组,获取原数组中, 不同元素的个数. 用sum来统计一下个数
        var sum = 0;
        for (int[] ints : sourceArray) {
            for (var anInt : ints) {
                if (anInt != 0) {
                    sum += 1;
                }
            }
        }
        
        //然后根据元素的个数,创建稀疏数组
        //  行数为sum+1 因为第一行放的是原数组的信息  3列为固定的
        int[][] sparseArray = new int[sum + 1][3];
        
        //给稀疏数组第一行添加上原数组的信息
        sparseArray[0][0] = 11;  //源数组行数
        sparseArray[0][1] = 11;  //源数组列数
        sparseArray[0][2] = sum;  //源数组中不同元素的个数
        
        
        //然后开始向稀疏数组中添加不同的元素
        //首先要再遍历一遍源数组, 获取不同的元素的所在位置, 然后再添加
        //因为第一行已经放源数组信息了,所以从第二行开始存放 这里用count来表示行数
        var count = 1;
        for (var i = 0; i < sourceArray.length; i++) {
            for (var j = 0; j < sourceArray[i].length; j++) {
                int sourceArrayNum = sourceArray[i][j]; //源数组中的每一个元素
                //如果sourceArrayNum != 0,表示是不同元素, 所以我们把它添加到稀疏数组当中
                if (sourceArrayNum != 0) {
                    sparseArray[count][0] = i;  //记录该不同元素的行信息
                    sparseArray[count][1] = j;  //记录该不同元素的列信息
                    sparseArray[count][2] = sourceArray[i][j];  //记录该不同元素的值
                    count++;  //这一行存完了, 换下一行存
                }
                
            }
        }
        
        //然后打印sparseArray看看效果
        System.out.println("\n");
        System.out.print("转换后的稀疏数组为:");
        for (int[] ints : sparseArray) {
            System.out.println();
            for (var anInt : ints) {
                System.out.print(anInt + "\t\t");
            }
        }
        var endTime = System.currentTimeMillis();
        
        var l = endTime - startTime;
        System.out.println("转换所用时间为: " + l + "ms");
        
        return sparseArray;
    }
    
}
