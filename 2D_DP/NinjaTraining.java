import java.util.*;
public class NinjaTraining{
   
    public static int ninjaTrainingMemo(int day, int[][] activities,int last,int[][]dp) {
        // no more days left
        if(day == 0) {
            int point = 0;
            for(int i=0;i<3;i++) {
                if(i != last)
                     point = Math.max(point,activities[day][i]);
            }
            return point;
        }
        if(dp[day][last] != -1)
            return dp[day][last];
        // days remaining
        int maxPoint = Integer.MIN_VALUE;
        for(int i=0;i<3;i++) {
            if(i != last) {
                maxPoint = Math.max(maxPoint,activities[day][i] + ninjaTrainingMemo(day-1, activities, i,dp));
            }
        }
        return dp[day][last]=maxPoint;
    }
    public static int ninjaTrainingTab(int[][] activities) {
        int[][]dp = new int[activities.length][4];
        // base cases
        // if dp[day][task] means the value it posses is the maximum point other than that task
        dp[0][0] = Math.max(activities[0][1],activities[0][2]);
        dp[0][1] = Math.max(activities[0][0],activities[0][2]);
        dp[0][2] = Math.max(activities[0][0],activities[0][1]);
        dp[0][3] = Math.max(activities[0][0],Math.max(activities[0][1],activities[0][2]));
        

        // building from base case

        // outer loop 'n' cases
        // innner loop doing operations for each case
        for(int day=1;day<activities.length;day++) {
            // all possible recent tasks
            // 0 / 1 / 2 / 3
            for(int last = 0;last<4;last++) {
                int result = Integer.MIN_VALUE;
                for(int task = 0;task<3;task++) {
                    if(task != last) {
                        int point = activities[day][task] + dp[day-1][task];
                        result = Math.max(result,point);
                    }
                }
                dp[day][last] = result;
            }
        }
        return dp[activities.length-1][3];
    }
    public static int ninjaTrainingSpOpt(int n, int points[][]) {
        // prev array for storing the result of all possible actions 0/1/2/3
        int[] prev = new int[4];
        prev[0] = Math.max(points[0][1],points[0][2]);
        prev[1] = Math.max(points[0][0],points[0][2]);
        prev[2] = Math.max(points[0][0],points[0][1]);
        prev[3] = Math.max(points[0][0],Math.max(points[0][1],points[0][2]));

        for(int day=1;day<n;day++) {
            int[] curr = new int[4];
            for(int last=0;last<4;last++){
                curr[last] = 0;
                for(int task=0;task<3;task++) {
                    if(task != last) {
                        curr[last] = Math.max(curr[last],points[day][task] + prev[task]);
                    }
                }
            }
            // change prev -> curr after performing tasks with all possible last tasks
            prev = curr;
        }
     // prev[3] where 3 -> refers to do all 0 , 1, 2 except 3
     return prev[3];
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
       int res = ninjaTrainingSpOpt(training.length, training);
       System.out.println(res);
    }
}