package DP_Subsequences;
// subSets = Subsequences
public class subSetSumequaltoK {
    public static boolean subSetsumK(int i, int[] arr, int k) {
        if(k == 0) return true;
        if(i == 0) return (arr[i] == k);

        // pick only if curr elem <= k
        boolean pick = false;
        if(k >= arr[i])
            pick = subSetsumK(i-1, arr, k-arr[i]);
        // non pick
        boolean non_pick = subSetsumK(i-1, arr, k);
        return pick || non_pick;
    }
    public static void main(String[] args) {
       int[] arr = new int[]{1,3,6};
       boolean res = subSetsumK(arr.length-1, arr, 2);
       System.out.println(res);
    }
}
