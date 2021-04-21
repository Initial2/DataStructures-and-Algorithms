package hashtable.test;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 用来测试我们自定义哈希表的类
 *
 * @author initial
 * @create 2021-03-30 19:33
 */
public class HashTableTest {
    @Test
    public void test() {
        MyHashTable myHashTable = new MyHashTable();
        
        for (int i = 1; i < 100; i++) {
            
            myHashTable.add(new Employee(new Random().nextInt(100), "i", "i"));
        }
        
        myHashTable.showHashTable();
    }
}
