package DP_Subsequences;

public class subSequenceSum {
    public static boolean subSeqSum(int i, int rem, int sum, int[] arr) {
        if (rem > sum)
            return false;
        if (i == 0)
            return rem == sum;
        if (rem == sum)
            return true;
        boolean pick = subSeqSum(i - 1, rem + arr[i], sum - arr[i], arr);
        boolean non_pick = subSeqSum(i - 1, rem, sum, arr);
        return pick || non_pick;
    }

    public static boolean subSeqSumMemo(int i, int rem, int sum, int[] arr, int[][] dp) {
        if (rem > sum)
            return false;
        if (i == 0)
            return rem == sum;
        if (rem == sum)
            return true;
        if (dp[i][rem] != 1)
            return dp[sum][rem] == 1 ? true : false;
        boolean pick = false;
        if(rem >= arr[i])
            pick = subSeqSum(i - 1, rem + arr[i], sum - arr[i], arr);
        boolean non_pick = subSeqSum(i - 1, rem, sum, arr);
        boolean res = pick || non_pick;
        dp[i][rem] = res ? 1 : 0;
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 0, 1, 2 };
        int n = arr.length;
        boolean res = subSeqSum(arr.length - 1, 0, 3, arr);
        int sum = 0;
        for (int i : arr)
            sum += i;
        int[][] dp = new int[n][sum+1];
        boolean res2 = subSeqSumMemo(arr.length - 1, 0, 3, arr,dp);
        System.out.println(res2);
    }
}
