import java.util.*;

public class FibonacciDP {
  // memoization(top - down dp)
  public static int fibMemo(int n, int[] dp) {
    if (n <= 1)
      return n;

    // check if it's in the cache
    if (dp[n] != -1)
      return dp[n];
    // otherwise calculate via recursion and then store it
    return dp[n] = fibMemo(n - 1, dp) + fibMemo(n - 2, dp);
  }

  // tabulation (bottom - up dp)
  public static int fibTable(int n, int[] dp) {
    // initialize or take the base cases
    dp[0] = 0;
    dp[1] = 1;

    // build the future ones using the existing ones

    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  // space optimized tabulation (bottom-up dp with less space)
  public static int fibOpt(int n) {
    // this is without using a extra space by performing the common sub-operation using variables

    int p1 = 0, p2 = 1;
    for(int i=2;i<=n;i++) {
      int sum = p1 + p2;
      p1 = p2;
      p2 = sum;
    }
    return p2;
  }

  public static void main(String[] args) {
    int n = 6;
    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    System.out.println(fibMemo(n, dp));
    Arrays.fill(dp, -1);
    System.out.println(fibTable(n, dp));
    Arrays.fill(dp, -1);
    System.out.println(fibOpt(n));
  }
}