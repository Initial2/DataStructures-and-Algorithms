package KMPAlgorithm;

/**
 * 字符串匹配问题. 暴力匹配法
 *
 * @author initial
 * @create 2021-04-16 12:39
 */
public class ViolenceMatch {
    
    
    public static void main(String[] args) {
        String str1 = "abcdefg中国";
        String str2 = "中国";
        
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }
    
    
    /**
     * 字符串匹配问题的暴力算法
     *
     * @param str1 字符串1
     * @param str2 子串2
     * @return 返回子串2在字符串1中第一次出现的位置
     */
    public static int violenceMatch(String str1, String str2) {
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        
        //子串的索引
        int index = 0;
        
        //遍历字符串1
        for (int i = 0; i < str1Array.length; i++) {
            
            if (str1Array[i] == str2Array[index]) {
                /*
                    如果相等, 就要看看是否已经匹配到了子串的最后一位,
                    如果匹配到了子串的最后一位, 就证明已经匹配完成了.
                    第一次出现的索引就是i - index;
                 */
                if (index == str2Array.length - 1) {
                    return i - index;
                }
                
                //如果子串没有匹配到最后一位. index++继续匹配
                index++;
                
            } else {
                //如果当前字符不匹配, index归0.从新匹配子串第一个字符
                //str1Array回到第一个匹配到它的字符的后一个位置.
                i = i - index;
                index = 0;
            }
        }
        return -1;
    }
}
