import java.util.*;
// PATTERN OF DP : FIXED STARTING POINT AND VARIABLE ENDING POINT
public class Triangle {
    public static int triangle(int i, int j, int[][] tri) {
        if (i == tri.length - 1)
            return tri[i][j];
        // then two possible things go down or go diagonal
        int down = triangle(i + 1, j, tri);
        int diag = triangle(i + 1, j + 1, tri);
        // add the minimum of either of them with curr pos
        return tri[i][j] + Math.min(down, diag);
    }

    public static int triangleMemo(int i, int j, int[][] tri, int[][] dp) {
        if (i == tri.length - 1)
            return tri[i][j];
        if (dp[i][j] != -1)
            return dp[i][j];
        int down = triangleMemo(i + 1, j, tri, dp);
        int diag = triangleMemo(i + 1, j + 1, tri, dp);
        return dp[i][j] = tri[i][j] + Math.min(down, diag);
    }
    public static int triangleTab(int[][] tri) {
        // dp array initialization
        int[][] dp = new int[tri.length][tri[0].length];
        int n = tri.length;
        for(int i=0;i<tri.length;i++) {
            dp[i] = new int[tri[i].length];
        }
        // base case (n -1)th row
        for(int i=0;i<tri[n-1].length;i++) {
            dp[n-1][i] = tri[n-1][i];
        }
        // filling rest of the arr (n-2 -> 0)
        for(int i = n -2;i >= 0;i--) {
            for(int j = i;j>=0;j--) {
                dp[i][j] = tri[i][j] + Math.min(dp[i+1][j+1],dp[i+1][j]);
            }
        }
        // give the end result or the final value
        return dp[0][0];
    }
    public static int triangleSptOpt(int[][] tri) {
        List<Integer> prev = new ArrayList<>();
        int n = tri.length;
        // base case
        for(int i=0;i<tri[n-1].length;i++) {
            prev.add(tri[n-1][i]);
        }
        // building up from prev arr
        for(int i=n-2;i>=0;i--) {
            List<Integer> temp =  new ArrayList<>(prev);
            // utilizing prev values
            for(int j = i; j >= 0;j--) {
                temp.set(j,tri[i][j] + Math.min(prev.get(j+1),prev.get(j)));
            }
            // now prev getting updated
            prev = temp;
        }
        // final value returned
        return prev.get(0);
    }
    public static void main(String[] args) {
        int[][] arr = new int[][] {
                { 1 },
                { 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9, 10 },
        };
        System.out.println(triangleSptOpt(arr));
    }
}
