public class minimumPathSum {
    public static int minPathSum(int i, int j, int[][] grids) {
        // we've reached the proper end
        if(i == 0 && j == 0)
            return grids[0][0];
        // no more path to go
        if(i <0 && j < 0)
            return Integer.MAX_VALUE; // reason why i give this is it never gets select as we use min at the end
        int up =  minPathSum(i-1,j,grids);
        int left = minPathSum(i,j-1,grids);
        return grids[i][j] + Math.min(up,left); // here
   }
   public static int minPathSumMemo(int i, int j, int[][] grids,int[][] dp) {
        if(i == 0 && j == 0)
            return grids[0][0];
        if(i < 0 && j < 0)
            return Integer.MAX_VALUE;
        if(dp[i][j] != -1)
            return dp[i][j];
        int up = minPathSumMemo(i-1, j, grids,dp);
        int left = minPathSumMemo(i, j-1, grids,dp);
        return dp[i][j] = grids[i][j] + Math.min(up,left);
   }
   public static int minPathSumTab(int[][] grids) {
      int n = grids.length;      // Number of rows
        int m = grids[0].length;   // Number of columns

        // DP table
        int[][] dp = new int[n][m];
        dp[0][0] = grids[0][0];

        // Fill the first row (only right moves are possible)
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grids[0][j];
        }

        // Fill the first column (only down moves are possible)
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grids[i][0];
        }

        // Fill the rest of the DP table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = grids[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        // The bottom-right cell contains the minimum sum path
        return dp[n - 1][m - 1];  
   }
}
