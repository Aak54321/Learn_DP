package DP_Subsequences;

// subSets = Subsequences without ordering
import java.util.*;

public class subSetSumequaltoK {
    public static boolean subSetsumK(int i, int[] arr, int target) {
        if (i == 0)
            return (arr[i] == target);
        if (target == 0)
            return true;
        boolean pick = false;
        if (arr[i] <= target)
            pick = subSetsumK(i - 1, arr, target - arr[i]);
        boolean non_pick = subSetsumK(i - 1, arr, target);
        return pick || non_pick;
    }

    public static boolean subSetSumequaltoKMemo(int i, int[] arr, int target, int[][] dp) {
        if (i == 0)
            return arr[i] == target;
        if (target == 0)
            return true;
        if (dp[i][target] != -1)
            return dp[i][target] == 1 ? true : false;
        boolean pick = false;
        if (arr[i] <= target)
            pick = subSetSumequaltoKMemo(i - 1, arr, target - arr[i], dp);
        boolean non_pick = subSetSumequaltoKMemo(i - 1, arr, target, dp);
        boolean result = pick || non_pick;
        dp[i][target] = result ? 1 : 0;
        return result;
    }

    public static boolean subSetSumequaltoKTab(int[] arr, int target) {
        int n = arr.length;
        
        /// base cases
        boolean dp[][] = new boolean[n][target + 1];
        for(int i=0;i<n;i++)
            dp[i][0] = true;
        if(arr[0] <= target)
            dp[0][arr[0]] = true;
        // Filling the DP table
        for (int ind = 1; ind < n; ind++) {
            for (int j = 1; j <= target; j++) {
                // Non-pick the current element
                boolean nonPick = dp[ind - 1][j];
                // Pick the current element (only if j >= arr[ind])
                boolean pick = false;
                if (j >= arr[ind]) {
                    pick = dp[ind - 1][j - arr[ind]];
                }
                dp[ind][j] = pick || nonPick;
            }
        }
        // finallly return it
        return dp[n - 1][target];
    }

    public static boolean subSetSumequaltoKSptOpt(int[] arr, int target) {
        int n = arr.length;
        // Filling the DP table
        boolean prev[] = new boolean[target + 1];
        prev[0] = true;
        if(n >0 && arr[0] <= target)
            prev[arr[0]] = true;
        for (int ind = 1; ind < n; ind++) {
            boolean[] temp = new boolean[prev.length];
            for (int j = 1; j <= target; j++) {
                // Non-pick the current element
                boolean nonPick = prev[j];
                // Pick the current element (only if j >= arr[ind])
                boolean pick = false;
                if (j >= arr[ind]) {
                    pick = prev[j - arr[ind]];
                }
                temp[j] = pick || nonPick;
            }
            prev = temp;
        }
        // finallly return it
        return prev[target];
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 3, 4 };
        boolean res = subSetsumK(arr.length - 1, arr, 5);
        int target = 5;
        int[][] dp = new int[arr.length][target + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        res = subSetSumequaltoKTab(arr, target);
        System.out.println(res);
    }
}
