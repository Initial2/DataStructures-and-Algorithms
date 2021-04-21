package hashtable.test;


import java.util.Arrays;

/**
 * 哈希表的实现
 * 用来管理我们多条存储雇员的链表
 *
 * @author initial
 * @create 2021-03-30 18:17
 */
public class MyHashTable {
    
    
    /**
     * 默认的临界因子, 用于判断数组是否已到临界值
     */
    private final double thresholdFactor = 0.75;
    
    
    /**
     * 默认设置数组长度为10.
     * 后续可以通过扩容修改.
     */
    private int maxSize = 10;
    
    
    /**
     * 设置我们哈希表的数组长度为maxSize;
     * 也就是可以存放maxSize条链表
     */
    EmployeeList[] array = new EmployeeList[maxSize];
    
    
    /**
     * 当前hash表的数组中已经占用了多少
     */
    private int currentNumOfArray = 0;
    
    /**
     * 判断是否需要进行扩容.
     * 判断机制: 如果当前占用数量已经 >= 临界值了
     * maxSize * thresholdFactor 表示当前数组的临界值.
     * 如果超过所有位置的75%, 就要开始扩容了
     */
    private boolean reSize() {
        return currentNumOfArray >= maxSize * thresholdFactor;
    }
    
    /**
     * 定义一个散列函数, 用来确定我们往里添加雇员时,存放在数组哪个链表中
     * 这里我们采用的方法是 id % maxSize.
     * 也就是雇员id % 数组长度,来确定存放位置
     *
     * @return int 返回应存放的位置
     */
    private int hashFunction(int id) {
        return id % maxSize;
        
    }
    
    /**
     * 添加数据到哈希表中
     *
     * @param employee 要添加的雇员
     */
    public void add(Employee employee) {
        //1. 首先确定放的位置
        int index = hashFunction(employee.ID);
        
        /*
          2. 再进行判断,如果这个位置还没有放过元素, 那么这个位置就是空的
             我们就new一个链表放进去. 预防空指针异常.
         */
        if (array[index] == null) {
            array[index] = new EmployeeList();
            //每占用一个位置, 它就+1;
            currentNumOfArray++;
        }
        
        //3. 然后就把他放进去
        array[index].add(employee);
        
        
        //4. 最后看看占用量是否要扩容
        if (reSize()) {
            //如果满足,就进行扩容. 每次扩容为原来的2倍
            maxSize *= 2;
            //把扩容后的数组,再赋值给array
            this.array = Arrays.copyOf(array, maxSize);
            
        }
    }
    
    /**
     * 根据提供的id删除对应的雇员
     *
     * @param id 提供的id
     */
    public void delete(int id) {
        int index = hashFunction(id);
        
        if (array[index] == null) {
            throw new RuntimeException("要删除的员工不存在");
        }
        
        array[index].delete(index);
        currentNumOfArray--;
    }
    
    /**
     * 根据提供的id,查找此员工的信息
     *
     * @param id 提供的id
     */
    public Employee findEmployeeById(int id) {
        //找到此id对应的存放位置
        int index = hashFunction(id);
        
        //如果此位置为空,则查找员工不存在
        if (array[index] == null) {
            return null;
        } else {
            return array[index].search(id);
        }
        
    }
    
    /**
     * 输出当前哈希表中的所有信息
     */
    public void showHashTable() {
        //有几条链表,就打印几条
        for (int i = 0; i < maxSize; i++) {
            //如果array[i] == null, 证明这个位置我们还没放过元素. 直接跳过
            if (array[i] == null) {
                continue;
            } else {
                //把每条有信息的链表都打印
                System.out.println("第:" + i + "条链表信息为:");
                array[i].showEmployee();
            }
            
        }
    }
    
    
}
