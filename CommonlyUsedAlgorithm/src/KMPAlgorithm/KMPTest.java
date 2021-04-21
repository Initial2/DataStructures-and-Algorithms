package KMPAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author initial
 * @create 2021-04-16 12:29
 */
public class KMPTest {
    
    
    @Test
    public void test() {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String targetStr = "abaabca";
        
        int[] matchArray = getMatchArray(targetStr);
        System.out.println(Arrays.toString(matchArray));
        
        int firstIndex = getFirstIndex(str, targetStr);
        System.out.println(firstIndex);
        
    }
    
    
    /**
     * kmp算法, 匹配目标串在原字符串中第一次出现的索引
     *
     * @param sourceStr 原字符串
     * @param targetStr 目标字符串
     * @return 找不到返回-1, 找到返回索引
     */
    public int getFirstIndex(String sourceStr, String targetStr) {
        
        //1. 排除特殊情况
        if (sourceStr.length() == 0 || targetStr.length() == 0) {
            return -1;
        }
        
        if (!sourceStr.contains(targetStr)) {
            return -1;
        }
        
        //2. 首先获取目标子串的部分匹配表
        int[] matchArray = getMatchArray(targetStr);
        
        //3. 开始匹配
        char[] sourceStrArray = sourceStr.toCharArray();
        char[] targetStrArray = targetStr.toCharArray();
        
        for (int i = 0, j = 0; i < sourceStrArray.length; i++) {
            //如果不匹配,考虑重定位J的位置  .
            //如果不匹配, 就让j回溯到之前某个位置, 使sourceStrArray[i] == targetStrArray[j].
            //如果回溯到头,还是不匹配, 那么j = 0;
            while (j > 0 && sourceStrArray[i] != targetStrArray[j]) {
                j = matchArray[j - 1];
            }
            
            //如果匹配
            if (sourceStrArray[i] == targetStrArray[j]) {
                //就接着匹配它俩下一位
                j++;
            }
            
            //判断是否已经匹配到了目标子串最后一位
            if (j == targetStr.length()) {
                //如果是,那么第一次出现的索引就是i-j
                return i - j + 1;
            }
        }
        return -1;
    }
    
    
    /**
     * 根据传入的字符串, 获取它的部分匹配表
     *
     * @param destStr 目标字符串
     * @return 返回它的部分匹配表
     */
    private int[] getMatchArray(String destStr) {
        
        //1. 创建一个数组保存结果
        int[] result = new int[destStr.length()];
        
        //2. 如果目标字符串只有一个, 那么它的部分匹配表就是0
        if (destStr.length() == 1) {
            return result;
        }
        
        //3. 开始遍历目标字符串
        int j = 0;
        for (int i = 1; i < destStr.length(); i++) {
            //核心步骤.
            //如果不匹配, 就让j回溯到之前某个位置, 使destStr.charAt(j) == destStr.charAt(i) 匹配.
            //如果回溯到头,还是不匹配, 那么j = 0;
            while (j > 0 && destStr.charAt(j) != destStr.charAt(i)) {
                j = result[j - 1];
            }
            //如果匹配, 部分匹配值就+1.
            if (destStr.charAt(j) == destStr.charAt(i)) {
                j++;
            }
            //同时把部分匹配值写入结果中.
            result[i] = j;
        }
        return result;
    }
    
}
