package hashtable.test;

/**
 * 用于存放雇员信息的链表
 *
 * @author initial
 * @create 2021-03-30 18:17
 */
public class EmployeeList {
    
    /**
     * 头结点, 默认为空. 这里我们让头结点就代表链表第一个元素所在的位置
     * 不再是之前head.next才是第一个元素所在的位置
     */
    Employee head;
    
    
    /**
     * 添加雇员
     * 这里按照id的大小,按顺序添加,
     * 默认用小-->大的顺序
     *
     * @param e 要添加的雇员
     */
    void add(Employee e) {
        if (e == null) {
            return;
        }
        
        //如果是第一次添加, 直接让head=它即可
        if (head == null) {
            head = e;
            return;
        }
        Employee temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = e;
        
        
    }
    
    /**
     * 删除雇员的操作, 根据指定的雇员id删除对应的雇员
     *
     * @param id 指定的雇员id
     */
    void delete(int id) {
        /*
        先判断,如果要删除的是第一个节点.
        就直接让head指向他下一个元素即可
         */
        if (head.ID == id) {
            head = head.next;
        }
        
        Employee temp = head.next;
        //不是第一个,就找到要删除的雇员的前一个雇员位置
        while (temp != null && temp.ID != id) {
            temp = temp.next;
        }
        
        //如果遍历到结束,也没找到这个id对应的雇员. 就证明id不存在
        if (temp == null) {
            throw new RuntimeException("要删除的员工不存在");
        }
        
        temp.next = temp.next.next;
        
        
    }
    
    /**
     * 根据id 查找此员工的信息
     *
     * @param id 提供的id
     * @return Employee 返回查找到的员工
     */
    Employee search(int id) {
        //非空判断
        if (head == null) {
            return null;
        }
        
        Employee temp = head;
        /*
        找到此id对应的员工,
        循环终止条件: temp != null为false时, 证明我们已经遍历到尾,也没有找到此id对应的员工.
                    temp.ID != id为false时,证明我们已经找到
         */
        while (temp != null && temp.ID != id) {
            temp = temp.next;
        }
        
        //直接返回temp即可. 找到了temp就是对应的员工, 没找到temp就是null
        return temp;
        
    }
    
    /**
     * 遍历此条链表上,所有的雇员信息
     */
    void showEmployee() {
        
        //非空判断
        if (head == null) {
            System.out.println("当前链表为空,没有雇员信息");
            return;
        }
        
        //开始遍历
        Employee temp = head;
        while (temp != null) {
            System.out.println(temp.ID + "\t" + temp.NAME + "\t" + temp.ADDRESS);
            temp = temp.next;
        }
        
    }
    
    
}
