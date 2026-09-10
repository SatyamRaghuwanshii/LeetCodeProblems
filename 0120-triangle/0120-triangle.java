class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = triangle.get(0).get(0);
        for(int i = 0; i<n-1; i++){
            int j = 0;
            while(j<=i){
                dp[i+1][j] = Math.min(dp[i+1][j] , dp[i][j] + triangle.get(i+1).get(j));
                dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + triangle.get(i+1).get(j+1));
                j++;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            res = Math.min(res,dp[n-1][i]);
        }
        return res;
    }
}