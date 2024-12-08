package DP_Grids;

import java.util.*;

public class uniquePath {
    public static int uniquePathRec(int i, int j) {
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        // why left : since this rec. call is from n-1, m-1
        // moving right or down as per question will
        // lead to outofBound so invert :(
        int left = uniquePathRec(i - 1, j);
        int right = uniquePathRec(i, j - 1);
        return left + right;
    }

    public static int uniquePathMemo(int i, int j, int[][] dp) {
        if (i == 0 && j == 0)
            return 1;
        if (i < 0 || j < 0)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        int left = uniquePathMemo(i - 1, j, dp);
        int right = uniquePathMemo(i, j - 1, dp);
        return dp[i][j] = left + right;
    }

    public static int uniquePathTab(int i, int j) {
        // building dp array
        int[][] dp = new int[i][j];
        // base case
        dp[0][0] = 1;
        int r = 0, c = 0;
        for (r = 0; r < i; r++) {
            for (c = 0; c < j; c++) {
                if (r > 0)
                    dp[r][c] += dp[r - 1][c];
                if (c > 0)
                    dp[r][c] += dp[r][c - 1];
            }
        }
        return dp[i - 1][j - 1];
    }

    public static int uniquePathSpOpt(int i, int j) {
        // building dp array
        int[] prev = new int[j];
        // base case
        int r = 0, c = 0;
        for (r = 0; r < i; r++) {
            // sole focus is to fill temp with values and  
            int[] temp = new int[j];
            for (c = 0; c < j; c++) {
                if(r == 0 && c == 0)
                    temp[c] = 1;
                else {
                if( r > 0 && c > 0) {
                    int up = prev[r-1];
                    int left = temp[c-1];
                    temp[c] = up + left;
                }
                }
           }
           // refer the prev to temp
            prev = temp;
        }
        return prev[j-1];
    }

    public static void main(String[] args) {
        int[][] dp = new int[3][3];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int res = uniquePathSpOpt(3, 3);
        System.out.println(res);
    }
}
