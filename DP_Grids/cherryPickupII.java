import java.util.*;

public class cherryPickupII {
    public static int chePickUp(int row, int bob_col, int alice_col, int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // I . out of bound base case
        if (bob_col < 0 || bob_col >= m || alice_col < 0 || alice_col >= m)
            return (int) Math.pow(10,-9);
        // II . ending base case
        if (row == n - 1) {
            // if bob and alice end up in same col add ** any one ** of their col
            if (alice_col == bob_col)
                return arr[n - 1][bob_col];
            // else add both of them
            else
                return arr[n - 1][bob_col] + arr[n - 1][alice_col];
        }
        // III. computer all possible path
        // -1 - leftDiag, 0 - down , 1 - rightDiag
        int[] bob_moves = { -1, 0, 1 }, alice_moves = { -1, 0, 1 };
        int res = Integer.MIN_VALUE;
        // trying out all possible moves (3 x 3 = 9 combinations)
        for (int i = 0; i < bob_moves.length; i++) {
            for (int j = 0; j < alice_moves.length; j++) {
                // if bob and alice end in same col add any of 'em
                if (alice_col == bob_col)
                    res = Math.max(res, arr[row][alice_col]
                            + chePickUp(row + 1, bob_col + bob_moves[i], alice_col + alice_moves[j], arr));
                // add both of them
                else
                    // alice here bob here combo of their moves
                    res = Math.max(res, arr[row][alice_col] + arr[row][bob_col]
                            + chePickUp(row + 1, bob_col + bob_moves[i], alice_col + alice_moves[j], arr));
            }
        }
        // IV. return the final
        return res;
    }

    public static int chePickUpMemo(int row, int bob_col, int alice_col, int[][] arr, int[][][] dp) {
        int n = arr.length, m = arr[0].length;

        // Check if the current columns are out of bounds
        if (bob_col < 0 || bob_col >= m || alice_col < 0 || alice_col >= m)
            return -100000008;

        // Base case: Last row
        if (row == n - 1) {
            if (bob_col == alice_col)
                return arr[row][bob_col];
            else
                return arr[row][bob_col] + arr[row][alice_col];
        }

        // Check if the result is already computed
        if (dp[row][bob_col][alice_col] != -1)
            return dp[row][bob_col][alice_col];

        int res = Integer.MIN_VALUE;
        int[] moves = { -1, 0, 1 }; // Possible moves: left, stay, right

        // Explore all combinations of moves for Bob and Alice
        for (int bobMove : moves) {
            for (int aliceMove : moves) {
                int nextBobCol = bob_col + bobMove;
                int nextAliceCol = alice_col + aliceMove;

                if (bob_col == alice_col) {
                    res = Math.max(res, arr[row][bob_col] + chePickUpMemo(row + 1, nextBobCol, nextAliceCol, arr, dp));
                } else {
                    res = Math.max(res, arr[row][bob_col] + arr[row][alice_col]
                            + chePickUpMemo(row + 1, nextBobCol, nextAliceCol, arr, dp));
                }
            }
        }

        // Store the result in the dp array
        return dp[row][bob_col][alice_col] = res;
    }

    public static int chePickUpTab(int[][] arr) {
        int n = arr.length, m = arr[0].length;
        // create dp array
        int[][][] dp = new int[n][m][m];
        // fill base case in dp array
        for (int bob = 0; bob < m; bob++) {
            for (int alice = 0; alice < m; alice++) {
                if (bob == alice)
                    dp[n - 1][bob][alice] = arr[n - 1][bob];
                else
                    dp[n - 1][bob][alice] = arr[n - 1][bob] + arr[n - 1][alice];
            }
        }
        // then fill the remaining array
        for (int row = n - 2; row >= 0; row--) {
            for (int bob = 0; bob < m; bob++) {
                for (int alice = 0; alice < m; alice++) {
                    int maxi = Integer.MIN_VALUE;
                    int[] actions = { -1, 0, 1 };
                    // try out all possible actions
                    for (int action1 : actions) {
                        for (int action2 : actions) {
                            int ans = 0;
                            if (bob == alice)
                                ans = arr[row][bob];
                            else
                                ans = arr[row][bob] + arr[row][alice];

                            // if actions are valid are / not
                            // invalid
                            if (bob + action1 >= m || bob + action1 < 0 || alice + action2 >= m || alice + action2 < 0)
                                ans += (int) Math.pow(10, -9);
                            // valid
                            else
                                ans += dp[row + 1][bob + action1][alice + action2];
                            maxi = Math.max(maxi, ans);
                        }
                    }

                    dp[row][bob][alice] = maxi;
                }
            }
        }
        // return the final value
        return dp[0][0][m - 1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                { 2, 3, 1, 2 },
                { 3, 4, 2, 2 },
                { 5, 6, 3, 5 }
        };
        int[][][] dp = new int[arr.length][arr[0].length][arr[0].length];
        for (int[][] d1 : dp)
            for (int[] d2 : d1)
                Arrays.fill(d2, -1);
        int res = chePickUpTab(arr);
        System.out.println(res);
    }
}
