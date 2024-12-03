import java.util.*;


// this wraps the function with boolean
// return either true / false in the base
// and if any of the recursive calls satisfies it
// return it immediately this stops further recursion calls


public class firstOneSatisfyRecursion {
    static ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

    public static boolean sumInsubseq(int i, ArrayList<Integer> list, int sum, int[] arr, int k) {
        if (i == arr.length) {
            if (sum == k) {
                pairs.add(new ArrayList<>(list));
                return true;
            } else {
                return false;
            }
        }

        // picking up
        list.add(arr[i]);
        // check if it satisfies return true
        if (sumInsubseq(i + 1, list, sum + arr[i], arr, k) == true)
            return true;
        
        list.remove(list.size() - 1);
        // check if this satisfies
        if (sumInsubseq(i + 1, list, sum, arr, k) == true)
            return true;
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 1 };
        ArrayList<Integer> list = new ArrayList<>();
        sumInsubseq(0, list, 0, arr, 2);
        for (ArrayList<Integer> pair : pairs) {
            System.out.println(pair);
        }
    }
}
