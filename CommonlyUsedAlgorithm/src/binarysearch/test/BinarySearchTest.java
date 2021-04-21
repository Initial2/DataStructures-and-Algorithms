package binarysearch.test;

/**
 * @author initial
 * @create 2021-04-14 20:05
 */
public class BinarySearchTest {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        
        int[] ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        int i = binarySearch.binarySearch(ints, 8);
        System.out.println(i);
    }
}
