import java.util.*;;
public class workbench {
    // public static int ninjaTrainingTab(int[][] activities) {
    //     int[][]dp = new int[activities.length][4];
    //     // base cases
    //     // if dp[day][task] means the value it posses is the maximum point other than that task
    //     dp[0][0] = Math.max(activities[0][1],activities[0][2]);
    //     dp[0][1] = Math.max(activities[0][0],activities[0][2]);
    //     dp[0][2] = Math.max(activities[0][0],activities[0][1]);
    //     dp[0][3] = Math.max(activities[0][0],Math.max(activities[0][1],activities[0][2]));
        

    //     // building from base case

    //     // outer loop 'n' cases
    //     // innner loop doing operations for each case
    //     int i;
    //     for(int day=1;day<activities.length;day++) {
    //         // all possible recent tasks
    //         // 0 / 1 / 2 / 3
    //         for(int last = 0;last<4;last++) {
    //             int result = Integer.MIN_VALUE;
    //             for(int task = 0;task<3;task++) {
    //                 if(task != last) {
    //                     int point = activities[day][task] + dp[day-1][task];
    //                     result = Math.max(result,point);
    //                 }
    //             }
    //             dp[day][last] = result;
    //         }
    //     }
    //     return dp[activities.length-1][3];
    // }
    public static int ninjaTrainingMemo(int day, int last, int[][] activities,int[][] dp) {
        // base case
        if(day == 0) {
            int maxi = Integer.MIN_VALUE;
            for(int task = 0; task < 3;task++)
                if(task != last)
                    maxi = Math.max(maxi,activities[day][task]);
            return maxi;
        }
        // memoize if possible
        if(dp[day][last] != -1)
            return dp[day][last];
        // other days (memoize not possible)
        int maxi = Integer.MIN_VALUE;
        for(int task = 0; task < 3;task++)
            if(task != last)
                maxi = Math.max(maxi,activities[day][task] + ninjaTrainingMemo(day-1, task, activities, dp));
        return dp[day][last] = maxi;
    }
    public static int ninjaTrainingTab(int[][] training) {
        int n = training.length, m = training[0].length;
        int[][] dp = new int[n][4];

        // fill the base case
        dp[0][0] = Math.max(training[0][1],training[0][2]);
        dp[0][1] = Math.max(training[0][0],training[0][2]);
        dp[0][2] = Math.max(training[0][0],training[0][1]);
        dp[0][3] = Math.max(training[0][0],Math.max(training[0][1],training[0][2]));
        // fill the rest of the array

        for(int day=1;day<n;day++) {
            for(int last = 0; last<=3;last++) {
                int maxi = Integer.MIN_VALUE;
                for(int task = 0;task<3;task++) {
                    if(task != last)
                        maxi = Math.max(maxi,training[day][task] + dp[day-1][task]);
                }
                dp[day][last] = maxi;
            }
        }
        // return it
        return dp[n-1][3];
    }
    public static int chePickII(int row, int bob_col, int alice_col,int[][] arr) {
        if(bob_col > arr.length-1 || bob_col < 0 || )
    }
    public static void main(String[] args) {
       int[][] training = new int[][]{
        {1,2,5},
        {3,1,1},
        {3,3,3}
       };
       int[][] dp = new int[training.length][4];
       for(int i=0;i<dp.length;i++) {
        Arrays.fill(dp[i],-1);
       }
       int res = ninjaTrainingMemo(training.length-1, 3,training,dp);
       int res1 = ninjaTrainingTab(training);
       System.out.println(res1);
    }
    
}