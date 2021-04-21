package greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用贪心算法, 解决广播覆盖问题
 *
 * @author initial
 * @create 2021-04-17 16:18
 */
public class BoardCast {
    
    
    @Test
    public void test() {
        
        ArrayList<String> result = getResult();
        System.out.println(result);
        
    }
    
    
    public ArrayList<String> getResult() {
        //1. 获取所有广播
        HashMap<String, HashSet<String>> boardCast = getBoardCast();
        
        //2. 创建一个allAreas, 存放需要覆盖的所有地区
        HashSet<String> allAreas = new HashSet<>();
        
        //以及存放选择的结果
        ArrayList<String> selects = new ArrayList<>();
        
        //遍历boardCast 把所有地区都添加进 allAreas. hashSet可以去重,不用担心地区重复
        for (String key : boardCast.keySet()) {
            allAreas.addAll(boardCast.get(key));
        }
        
        //3. 开始遍历所有的广播电台, 找到最优解
        
        //需要借助一个临时Set, 用来存放每个电台的覆盖区域.
        Set<String> tempSet = new HashSet<>();
        
        //4. 定义一个MaxKey 用于存放每一轮比较中, 能够覆盖最多未覆盖地区的电台名.
        String maxKey = null;
        
        //range用来存放, maxKey所能覆盖的未覆盖地区的个数
        int range = 0;
        
        //只要allAreas还没为空, 就一直找.
        while (allAreas.size() > 0) {
            
            //每次找出,最好的选择
            for (String key : boardCast.keySet()) {
                //把每个电台覆盖区域取出来
                tempSet.addAll(boardCast.get(key));
                //看看当前电台的覆盖区域 和 需要覆盖的地区的交集. 也就是看看他有没有包含,我们需要覆盖的地区.
                tempSet.retainAll(allAreas);
        
                /*
                    如果tempSet.size() > 0, 证明有交集. 也就是有我们需要覆盖的地区.
                    此时需要判断, 如果当前电台覆盖的未覆盖区域个数, 大于之前的电台能够覆盖最大的未覆盖区域个数.
                    就把maxKey指向当前电台.  并且更新最大覆盖区域个数.
                 */
                if (tempSet.size() > 0 && tempSet.size() > range) {
                    maxKey = key;
                    range = tempSet.size();
                }
                
                //然后把tempSet清空, 比较下一个电台的覆盖区域.
                tempSet.clear();
            }
            
            //5. 最后, 如果maxKey不为空, 证明我们已经选择了一个最佳电台
            if (maxKey != null) {
                //就把maxKey对应的电台加入到选择中.
                selects.add(maxKey);
                //同时更新需要覆盖的总地区.  把需要覆盖的总地区 - maxKey对应的电台所覆盖的地区
                allAreas.removeAll(boardCast.get(maxKey));
            }
            
            //然后range置0 , 开始进行选择下一个电台,
            range = 0;
        }
        
        return selects;
        
    }
    
    
    private HashMap<String, HashSet<String>> getBoardCast() {
        //1. 先把所有广播和对应的覆盖地区,放到HashMap中
        HashMap<String, HashSet<String>> boardCast = new HashMap<>(20);
        HashSet<String> k1area = new HashSet<>();
        k1area.add("北京");
        k1area.add("上海");
        k1area.add("天津");
        boardCast.put("K1", k1area);
        
        
        HashSet<String> k2area = new HashSet<>();
        k2area.add("北京");
        k2area.add("广州");
        k2area.add("深圳");
        boardCast.put("K2", k2area);
        
        HashSet<String> k3area = new HashSet<>();
        k3area.add("成都");
        k3area.add("上海");
        k3area.add("杭州");
        boardCast.put("K3", k3area);
        
        HashSet<String> k4area = new HashSet<>();
        k4area.add("上海");
        k4area.add("天津");
        boardCast.put("K4", k4area);
        
        
        HashSet<String> k5area = new HashSet<>();
        k5area.add("杭州");
        k5area.add("大连");
        boardCast.put("K5", k5area);
        
        return boardCast;
    }
}
