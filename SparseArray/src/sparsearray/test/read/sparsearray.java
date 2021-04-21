package sparsearray.test.read;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * 读取出来map.data后. 恢复此数组
 *
 * @author initial
 * @create 2021-03-20 12:16
 */
public class sparsearray {
    @Test
    public void test() throws Exception {
        
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Inital\\Desktop\\map.data");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        
        byte[] bytes = new byte[100];
        int len = bufferedInputStream.read(bytes);
        ArrayList<Integer> integers = new ArrayList<>();
        
        while (len != -1) {
            for (int i = 0; i < len; i++) {
                integers.add((int) bytes[i]);
            }
            len = bufferedInputStream.read(bytes);
        }
        
        
        //integers.get(0)放的行信息 integers.get(1)放的列信息
        int[][] sourceArray = new int[integers.get(0)][integers.get(1)];
        
        //直接从第二行开始读
        for (int i = 3; i < integers.size(); i += 3) {
            sourceArray[integers.get(i)][integers.get(i + 1)] = integers.get(i + 2);
        }
        
        for (int[] ints : sourceArray) {
            System.out.println();
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
        }
        
        
    }
}
