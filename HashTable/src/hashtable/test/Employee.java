package hashtable.test;

/**
 * 雇员类, 用来存放雇员的相关信息.
 *
 * @author initial
 * @create 2021-03-30 18:12
 */
public class Employee {
    
    /**
     * 雇员ID, 不可改变
     */
    final int ID;
    
    /**
     * 雇员姓名
     */
    final String NAME;
    
    
    /**
     * 雇员地址
     */
    final String ADDRESS;
    
    /**
     * 存放下一个雇员的节点地址
     */
    Employee next = null;
    
    
    Employee(int ID, String NAME, String ADDRESS) {
        this.ID = ID;
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
    }
    
    
    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                '}';
    }
}
