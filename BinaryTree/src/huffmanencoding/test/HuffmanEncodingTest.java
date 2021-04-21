package huffmanencoding.test;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码的测试
 *
 * @author initial
 * @create 2021-04-06 10:32
 */
public class HuffmanEncodingTest {
    
    /**
     * 赫夫曼编码表
     */
    private Map<Byte, String> huffManTable;
    
    @Test
    public void test() {
        File srcFile = new File("D:/1234.avi");
        File desFile = new File("D:/picture.zip");
        File resultFile = new File("D:/111.avi");
        
        zipFile(srcFile, desFile);
        unZipFile(desFile, resultFile);
        
        
    }
    
    
    /**
     * 把指定文件压缩成zip格式
     * 使用赫夫曼编码压缩
     *
     * @param srcFile  要压缩的文件
     * @param destFile 压缩后的zip文件
     */
    public void zipFile(File srcFile, File destFile) {
        FileInputStream is = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //1. 创建输入流, 把要压缩的文件读取进来
            is = new FileInputStream(srcFile);
            
            //2. 创建一个字节数组,用于存储读取进来的文件
            // 字节数组大小= is.available() 也就是读取进来的文件的大小
            byte[] bytes = new byte[is.available()];
            
            //3. 把读取进来的字节数组进行压缩
            is.read(bytes);
            
            //获取压缩后的字节数组
            byte[] resultBytes = zipData(bytes);
            
            //4. 把压缩后的数组和其对应的赫夫曼编码表一起写入目标zip文件
            //这里使用ObjectOutputStream 对象输出流. 因为比较方便,可以一次写出一个对象
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(destFile));
            //写入压缩后的数组
            objectOutputStream.writeObject(resultBytes);
            //写入其对应的赫夫曼编码表
            objectOutputStream.writeObject(huffManTable);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
    }
    
    /**
     * 把指定zip文件解压
     *
     * @param srcFile  要解压的zip文件
     * @param destFile 解压后的源文件
     */
    public void unZipFile(File srcFile, File destFile) {
        ObjectInputStream objectInputStream = null;
        FileOutputStream os = null;
        
        try {
            //1. 创建对象输入流,读取压缩文件
            objectInputStream = new ObjectInputStream(new FileInputStream(srcFile));
            
            // 第一次读进来的对象是之前写入的字节数组
            byte[] bytes = (byte[]) objectInputStream.readObject();
            
            // 第二次读进来的对象是之前写入的赫夫曼编码表
            Map<Byte, String> map = (Map<Byte, String>) objectInputStream.readObject();
            
            //2. 然后进行解压,获取源文件对应的二进制数组
            byte[] decode = decode(bytes, map);
            
            //3. 最后把这个二进制流写入解压后的文件
            os = new FileOutputStream(destFile);
            os.write(decode);
            
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
    }
    
    
    /**
     * 包装方法. 传入原始数据对应的字节数组,
     * 返回经过赫夫曼编码压缩后的数组
     *
     * @param originBytes 原始数据对应的字节数组
     * @return 返回经过赫夫曼编码压缩后的数组
     */
    public byte[] zipData(byte[] originBytes) {
        
        //1. 把原始字节数组,经过统计和包装. 打包成一个个Node,放在集合中
        ArrayList<Node> nodes = parseToList(originBytes);
        
        //2. 把已经打包好的集合,转换成一个赫夫曼树.
        Node huffmanTreeRoot = creatHuffmanTree(nodes);
        
        //3. 根据创建好的赫夫曼树,指定赫夫曼编码表
        //赫夫曼编码表,用HashMap来存储
        this.huffManTable = new HashMap<>(20);
        // 存放每个节点的路径
        StringBuilder path = new StringBuilder();
        
        //4.从根节点开始,找到所有data域不为空的节点,记录下来路径
        getPath(huffmanTreeRoot, "", path, huffManTable);
        
        
        return zip(originBytes, huffManTable);
        
        
    }
    
    
    /**
     * 把bytes数组,转成链表存储.
     *
     * @param bytes 给定的byte数组
     * @return ArrayList<Node> 返回把它封装成Node的一个集合
     */
    public ArrayList<Node> parseToList(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>(20);
        
        
        //1. 遍历数组,统计每个字符出现的次数
        for (byte aByte : bytes) {
            //如果map中已经有它了, 出现次数+1
            if (map.containsKey(aByte)) {
                map.put(aByte, map.get(aByte) + 1);
            } else {
                //如果没有,把它添加到map中.
                map.put(aByte, 1);
            }
        }
        
        //2. 开始把map中所有元素包装为Node添加到list中
        for (Map.Entry<Byte, Integer> m : map.entrySet()) {
            nodes.add(new Node(m.getKey(), m.getValue()));
        }
        
        //3. 最后返回
        return nodes;
        
    }
    
    
    /**
     * 把集合转换成赫夫曼树
     *
     * @param list 给定的集合
     * @return Node 返回赫夫曼树的根节点
     */
    public Node creatHuffmanTree(List<Node> list) {
        
        while (list.size() > 1) {
            //1. 首先进行排序
            Collections.sort(list);
            
            //1. 取出前两个最小的元素
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
           
           /*
           2. 新建一个根节点, 指向这两个node.
              注意:  这个根节点, 只有这两个子节点的权值总和(weight). 没有data.
            */
            Node newNode = new Node(leftNode.weight + rightNode.weight);
            
            //3. 重建结构
            newNode.left = leftNode;
            newNode.right = rightNode;
            
            //4. 删除这两个已经处理过的结点
            list.remove(leftNode);
            list.remove(rightNode);
            
            //5. 把新节点加入list中
            list.add(newNode);
            
        }
        return list.get(0);
        
    }
    
    /**
     * 把我们每个data域不为空的结点对应的路径记录下来.
     * 放在赫夫曼编码表中
     *
     * @param node      从根节点开始寻找.
     * @param direction 查找的方法,左边为0,右边为1
     * @param path      记录查找到的每一个非叶子结点的路径
     * @param map       用来存放路径的赫夫曼编码表
     */
    public void getPath(Node node, String direction, StringBuilder path, Map<Byte, String> map) {
        //1. 创建一个临时路径,用于拼接
        StringBuilder tempPath = new StringBuilder(path);
        //每次把走的方向记录下来
        tempPath.append(direction);
        
        if (node != null) {
            //找到所有data域不为空的节点
            
            /*
            因为data域为空的节点,是我们之前构造赫夫曼树,生成的节点. 他只有权值,没有data.
             */
            if (node.data == null) {
                //向左递归,查询其它data域不为空的节点
                getPath(node.left, "0", tempPath, map);
                
                //向右递归,查询其它data域不为空的节点
                getPath(node.right, "1", tempPath, map);
            } else {
                //找到一个,就添加一个
                map.put(node.data, tempPath.toString());
            }
            
        }
        
    }
    
    
    /**
     * 根据传进来的原始字节数组, 和对应的赫夫曼编码表,
     * 把此原始字节数组进行压缩,然后返回一个压缩后的数组
     *
     * @param originBytes 原始字符串对应的字节数组
     * @param map         原始字符串对应的赫夫曼编码表
     * @return 返回一个压缩后的数组
     */
    public byte[] zip(byte[] originBytes, Map<Byte, String> map) {
        /*
          1.首先创建一个StringBuilder,用于存放原始字节数组
            经过应的赫夫曼编码表转换后的数组.
         */
        StringBuilder stringBuilder = new StringBuilder();
        
        //2. 遍历原始数组,开始一对一转换
        for (byte aByte : originBytes) {
            stringBuilder.append(map.get(aByte));
        }
        
        /*
        3. 转换过后, 要进行压缩. 把这个字符串,每8位压缩.
           然后把压缩过后的数据,存放到新的数组中.
         */
        
        //首先,看看需要创建一个多长的byte数组
        int len = (stringBuilder.length() % 8 == 0) ? stringBuilder.length() / 8 : stringBuilder.length() / 8 + 1;
        
        byte[] resultBytes = new byte[len];
        
        //然后, 遍历这个字符串,开始进行压缩
        //每8位压缩, 所以i+=8. 每次往后跳8个
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //防止索引越界, 如果最后一次压缩不够8位,就只压缩剩下的.
            if (i + 8 < stringBuilder.length()) {
                // 先把截取的8位字符串,转换为整数, 然后再把这个整数,转换为对应的byte值.
                //由于字符串是2进制的, 所以在转换为整数的时候, 指定为2进制解析. radix=2.
                resultBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
                
            } else {
                // 先把截取的剩余字符串,转换为整数, 然后再把这个整数,转换为对应的byte值.
                resultBytes[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            }
            //每放一个,index后移
            index++;
        }
        
        
        return resultBytes;
    }
    
    
    /**
     * 把赫夫曼压缩后的数组进行解码
     *
     * @param huffmanBytes 要进行解码的赫夫曼压缩后的数组
     * @param map          赫夫曼编码表
     * @return 返回解析出来的二进制字节数组
     */
    private byte[] decode(byte[] huffmanBytes, Map<Byte, String> map) {
        //1. 首先创建一个StringBuilder 用来存储解码后的二进制数字
        StringBuilder stringBuilder = new StringBuilder();
        
        //2. 遍历这个字节数组
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte temp = huffmanBytes[i];
            if (i == huffmanBytes.length - 1) {
                //如果是最后一个元素, 则不需要补位
                stringBuilder.append(parseToString(temp, false));
            } else {
                //如果不是最后一个元素, 都给他补位
                stringBuilder.append(parseToString(temp, true));
            }
        }
        
        
        //3. 翻转赫夫曼编码表
        HashMap<String, Byte> newHashMap = new HashMap<>(20);
        for (Map.Entry<Byte, String> byteStringEntry : map.entrySet()) {
            newHashMap.put(byteStringEntry.getValue(), byteStringEntry.getKey());
        }
        
        
        //4. 开始查询
        ArrayList<Byte> bytes = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        
        for (int i = 0; i < stringBuilder.length(); i++) {
            //拼接字符
            temp.append(stringBuilder.charAt(i));
            
            //如果找到了对应的编码,就把对应的byte值,添加到list中
            if (newHashMap.containsKey(temp.toString())) {
                bytes.add(newHashMap.get(temp.toString()));
                //然后置空temp
                temp = new StringBuilder();
            }
        }
        
        //由于我们是用list存储的, 把它改成byte[] 数组返回
        byte[] bytes1 = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            bytes1[i] = bytes.get(i);
        }
        
        return bytes1;
    }
    
    
    /**
     * 把经过压缩后的赫夫曼数组中的元素,重新转换为对应的二进制字符串.
     *
     * @param huffmanBytes 压缩后的赫夫曼数组中的元素
     * @return 返回重新转换为对应的二进制字符串.
     */
    private String parseToString(byte huffmanBytes, boolean flag) {
        
        /*
        1. 由于Byte包装类没有转换为2进制字符串的方法. 所以这里用Integer包装类
           首先把huffmanBytes转成int类型,不然没法用Integer包装类
          */
        int temp = huffmanBytes;
      
        /*
        2. 转换后要考虑补位的问题.
           ①. 如果是负数,前面符号位是1, 那么就不需要补高位,如果是整数,前面符号位是0, 那么就需要补高位
               因为:Integer.toBinaryString()方法. 如果传入的是一个正数, 符号位为0.
               那么解析以后返回的二进制字符串它是不带符号位的.
               负数会显示符号位,所以补高位并不影响.
         */
        if (flag) {
            //按位与运算. 256的补码 = 1 0000 0000  相同取0,不同取1.
            temp |= 256;
        }
        
        String string = Integer.toBinaryString(temp);
        
        if (flag) {
            //如果补了高位, 就只取后8位.
            /*由于我们把huffmanBytes转成了int类型,它所占字节和byte类型不一样
             所以 Integer.toBinaryString(temp)它也是按照int型来转换二进制字符串的.
             结果非常长, 我们只取后8位,就是huffmanBytes对应的二进制字符串.
             */
            return string.substring(string.length() - 8);
        }
        
        /*
            如果是数组中最后一个元素,那么就不需要补位. 因为它可能本来就不是8位的.
            如果强行补位,可能会出错. 把它全部返回即可.
         */
        return string;
        
        
    }
    
    
}


/**
 * 赫夫曼树的Node
 */
class Node implements Comparable<Node> {
    /**
     * data是结点对应的数据
     * weight是每个结点的权值
     */
    Byte data;
    int weight;
    
    /**
     * 每个节点的左右子节点
     */
    Node left;
    Node right;
    
    public Node(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
    
    public Node(int weight) {
        this.weight = weight;
    }
    
    
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    
    
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }
}