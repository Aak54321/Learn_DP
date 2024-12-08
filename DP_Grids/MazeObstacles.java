import java.util.*;

public class MazeObstacles {
    // sames as uniquePath with presence of a dead cell
    // i.e arr[i][j] == -1 so we can't move to that cell

    public static int mazeObstacles(int i, int j, int[][] maze, int[][] dp) {
        if (i == 0 && j == 0 && maze[i][j] != -1)
            return 1;
        if (i < 0 && j < 0 || maze[i][j] == -1)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int up = 0, down = 0;
        if(j > 0)
            up = mazeObstacles(i, j - 1, maze, dp);
        if(i > 0)
            down = mazeObstacles(i - 1, j, maze, dp);
        return dp[i][j] = up + down;
    }
    public static int mazeObstaclesTab(int i, int j,int[][] maze) {
        int[][] dp = new int[i][j];
        dp[0][0] = 1;
        for(int r = 0; r< i;r++) {
            for(int c = 0;c < j;c++) {
                if(maze[r][c] == -1)
                    dp[r][c] = 0;
                else if(maze[r][c] == 0)
                    dp[r][c] = 1;
                else {
                    int up = dp[r-1][c];
                    int left = dp[r][c-1];
                    dp[r][c] = up + left;
                }
            }
        }
        return dp[i-1][j-1];
    }

    public static void main(String[] args) {
        int[][] maze = new int[][] {
                { 0, 0, 0 },
                { 0, -1, 0 },
                { 0, 0, 0 }
        };
        int[][] dp = new int[maze.length][maze[0].length];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        int res = mazeObstacles(maze.length-1, maze[0].length-1, maze, dp);
        System.out.println(res);
    }
}