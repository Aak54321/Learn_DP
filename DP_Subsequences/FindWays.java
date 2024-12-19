package DP_Subsequences;

public class FindWays {
      public static int findWays(int i, int target, int[] arr) {
        if (i == 0)
            return (arr[i] == target) ? 1 : 0;
        if (target == 0)
            return 1;

        // pick only if the curr. element is equal or lesser than target
        int pick = 0;
        if (arr[i] <= target)
            pick += findWays(i - 1, target - arr[i], arr);
        // non pick
        int non_pick = findWays(i - 1, target, arr);

        return pick + non_pick;
    }

    public static int findWaysTab(int[] num, int k) {
        int n = num.length;

        // Create a 2D DP array to store the number of ways to achieve each target sum
        int[][] dp = new int[n][k + 1];

        // Initialize the first row of the DP array
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // Initialize the first column of the DP array
        if (num[0] <= k) {
            dp[0][num[0]] = 1;
        }

        // Fill in the DP array using bottom-up dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                // Calculate the number of ways when the current element is not taken
                int notTaken = dp[ind - 1][target];

                // Calculate the number of ways when the current element is taken
                int taken = 0;
                if (num[ind] <= target) {
                    taken = dp[ind - 1][target - num[ind]];
                }

                // Update the DP array for the current element and target sum
                dp[ind][target] = notTaken + taken;
            }
        }

        // The result is stored in the last cell of the DP array
        return dp[n - 1][k];
    }

    public static int findWaysSptOpt(int[] arr, int target) {
        int[] prev = new int[target + 1];
        prev[0] = 1;
        if (arr[0] <= target)
            prev[arr[0]] = 1;
        for (int i = 1; i < arr.length; i++) {
            int[] temp = new int[prev.length];
            for (int j = 0; j <= target; j++) {
                // pick
                int pick = 0;
                if (j >= arr[i])
                    pick = prev[j - arr[i]];
                // non pick
                int non_pick = prev[j];
                temp[j] = pick + non_pick;
            }
            prev = temp;
        }
        return prev[target];
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 4, 5 };
        int res = findWaysSptOpt(arr, 5);
        System.out.println(res);
    }
  
}
