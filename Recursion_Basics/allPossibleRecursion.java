import java.util.ArrayList;

public class allPossibleRecursion {

    // pick not pick pattern (REcursion)
    // Problem return all subsequences to sum up to K
    static ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

    public static void sumInsubseq(int i, ArrayList<Integer> list, int sum, int[] arr, int k) {
        if (i == arr.length) {
            if (sum == k) {
                pairs.add(new ArrayList<>(list));
            }
            return;
        }

        // pick
        list.add(arr[i]);
        sumInsubseq(i + 1, list, sum + arr[i], arr, k);
        // not pick
        list.remove(list.size() - 1);
        sumInsubseq(i + 1, list, sum, arr, k);

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
