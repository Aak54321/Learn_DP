import java.util.*;

// PATTERN OF DP: VARIABLE STARTING AND ENDING POINT
public class maximumFallingPathSum {
    public static int maxFallPathSum(int i, int j, int[][] arr) {
        // out of bound handling if any of the diagonal isn't possible (always mention first)
        if (j >= arr[i].length || j < 0)
            return Integer.MIN_VALUE; // to get neglected as we use max at the end
        if (i == 0)
            return arr[i][j]; // base case handling
        int up = maxFallPathSum(i - 1, j, arr);
        int leftDiag = maxFallPathSum(i - 1, j - 1, arr);
        int rightDiag = maxFallPathSum(i - 1, j + 1, arr);
        // return the value
        return arr[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
    }

    public static int maxFallPathSumMemo(int i, int j, int[][] arr, int[][] dp) {
        if (j >= arr[i].length || j < 0)
            return Integer.MIN_VALUE;
        if (i == 0)
            return arr[i][j];
        if (dp[i][j] != -1)
            return dp[i][j];
        int up = maxFallPathSumMemo(i - 1, j, arr, dp);
        int leftDiag = maxFallPathSumMemo(i - 1, j - 1, arr, dp);
        int rightDiag = maxFallPathSumMemo(i - 1, j + 1, arr, dp);

        return dp[i][j] = arr[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
    }

    public static int maxFallPathSumTab(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[arr.length][arr[0].length];
        // fill first row
        for (int i = 0; i < arr[0].length; i++)
            dp[0][i] = arr[0][i];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                int up = dp[i - 1][j];
                int leftDiag = (j > 0) ? dp[i - 1][j - 1] : Integer.MIN_VALUE;
                int rightDiag = (j < arr[i].length - 1) ? dp[i - 1][j + 1] : Integer.MIN_VALUE;
                dp[i][j] = arr[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr[n - 1].length; i++)
            res = Math.max(res, dp[n - 1][i]);
        return res;
    }

    public static int maxFallPathSumSpt(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        int[] prev = new int[m];
        for (int i = 0; i < m; i++)
            prev[i] = arr[0][i];

        for (int i = 1; i < n; i++) {
            int[] temp = new int[prev.length];
            for (int j = 0; j < m; j++) {
                int up = prev[j];
                int leftDiag = (j > 0) ? prev[j - 1] : Integer.MIN_VALUE;
                int rightDiag = (j < arr[0].length - 1) ? prev[j + 1] : Integer.MIN_VALUE;
                temp[j] = arr[i][j] + Math.max(up, Math.max(leftDiag, rightDiag));
            }
            prev = temp;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++)
            res = Math.max(res, prev[i]);
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                { 1, 2, 10, 4 },
                { 100, 3, 2, 1 },
                { 1, 1, 20, 2 },
                { 1, 2, 2, 1 }
        };
        int[][] dp = new int[arr.length][arr[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int res = Integer.MIN_VALUE;
        // can be many starting points in the last row (variable start)
        for (int i = arr.length - 1; i >= 0; i--) {
            res = Math.max(res, maxFallPathSumMemo(arr.length - 1, i, arr, dp));
        }
        int res1 = maxFallPathSumSpt(arr);
        System.out.println(res1);
    }
}
