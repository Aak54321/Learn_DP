import java.util.*;

public class houseRob{
    public static int houseRobber(int i, int[] arr) {
        if (i == 0)
            return arr[i];
        if (i < 0)
            return 0;

        // pick
        int pick = arr[i] + houseRobber((i - 2), arr);
        // nonpick
        int nonPick = 0 + houseRobber((i - 1), arr);

        return Math.max(pick, nonPick);
    }

    public static int houseRobberMemo(int i, int[] arr, int[] dp) {
        if (i == 0)
            return arr[i];
        if (i < 0)
            return 0;

        if (dp[i] != -1)
            return dp[i];

        int pick = arr[i] + houseRobber(i - 2, arr);
        int nonPick = 0 + houseRobber(i - 1, arr);

        dp[i] = Math.max(pick, nonPick);

        return dp[i];
    }

    public static int houseRobberTab(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int j = 1; j < arr.length; j++) {
            int pick = arr[j];
            if (j - 2 >= 0)
                pick += arr[j - 2];
            int nonPick = arr[j - 1];
            dp[j] = Math.max(pick, nonPick);
        }
        return dp[arr.length - 1];
    }

    public static int houseRobberSpOpt(int[] arr) {
        int prev2 = 0;
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {

            // pick
            int pick = arr[i];
            if (i > 1) {
                pick += prev2;
            }

            // non pick
            int non_pick = prev;

            int curr = Math.max(pick, non_pick);

            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static int houseRobber2(int[] arr) {
        // this is the same problem instead the array/ houses are circular
        // i.e., the first and last are adjacent
        int[] arr1 = new int[arr.length - 1];
        int[] arr2 = new int[arr.length - 1];
        arr2[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i != arr.length - 1)
                arr2[i] = arr[i];
            arr1[i - 1] = arr[i];
        }
        return Math.max(houseRobberSpOpt(arr1), houseRobberSpOpt(arr2));
    }

    public static void main(String[] args) {

        int[] arr = new int[] { 2, 3, 2 };
        int res2 = houseRobber2(arr);
        System.out.println(res2);
    }
}
