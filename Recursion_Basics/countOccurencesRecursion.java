// when ever the base case satisfies return 1 else 0
// initialize both the left and right calls to a variable 
// then aggregate them (incase of counting it's  '+')

import java.util.*;

public class countOccurencesRecursion {
    static ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
    public static int sumInsubseq(int i,ArrayList<Integer> list, int sum, int[] arr, int k) {
        if(i == arr.length)  {
            if(sum == k) {
                pairs.add(new ArrayList<>(list));
                return 1;
            }
            return 0;
        }
        list.add(arr[i]);
        int left = sumInsubseq(i+1, list, sum+arr[i], arr, k);
        list.remove(list.size()-1);
        int right = sumInsubseq(i+1, list, sum, arr, k);

        return left + right;
    }
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 1 };
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(sumInsubseq(0, list, 0, arr, 2));
    }
}
