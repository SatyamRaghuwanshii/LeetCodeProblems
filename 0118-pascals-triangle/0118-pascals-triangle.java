class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int[][] dp = new int[numRows+1][numRows+1];
        for(int i = 1; i<=numRows; i++){
            List<Integer> cur = new ArrayList<>();
            int j = 1;
            while(j<=i){
                if(i==j){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }
                j++;
            }
            for(int k = 1; k <= i; k++){
                cur.add(dp[i][k]);
            }
            result.add(cur);
        }
        return result;
    }
}