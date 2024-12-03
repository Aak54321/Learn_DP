import java.util.*;

public class frogJump {
    public static int frogJumpMemo(int[] arr, int k, int i, int[] dp) {
        if (i == 0)
            return 0;
        if (dp[i] != -1)
            return dp[i];
        int minJumps = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i >= j) {
                int currJump = frogJumpMemo(arr, k, i - j, dp) + Math.abs(arr[i] - arr[i - j]);
                minJumps = Math.min(minJumps, currJump);
            }
        }
        return dp[i] = minJumps;

    }

    public static int frogJumpTabulation(int[] arr, int k) {
        int[] dp = new int[arr.length];
        dp[0] = 0;

        for(int i=1;i<arr.length;i++) {
            int minJumps = Integer.MAX_VALUE;

            // perform 1 -> k jumps starting from ith element
            for(int j=1;j<=k;j++) {
                if(i>=j) {
                    int currJump = dp[i-j] + Math.abs(arr[i] - arr[i-j]);
                    minJumps = Math.min(minJumps, currJump);
                }
            }
            dp[i] = minJumps;
        }
        return dp[arr.length-1];
    }

    public static void main(String[] args) {
        int[] arr = { 10, 40, 30, 10 };
        int[] dp = new int[arr.length]; // Fix size to arr.length
        Arrays.fill(dp, -1);

        int min_steps = frogJumpMemo(arr, 2, arr.length - 1, dp);
        int min_steps2 = frogJumpTabulation(arr, 2);
        System.out.println(min_steps2);
    }
}
