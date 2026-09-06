class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        long[][] dp = new long[m+1][n+1];
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        return ways(dp,m,n,s,t);
    }
    private int ways(long[][] dp, int m, int n, String s, String t){
        if(n == 0) return 1;
        if(m == 0) return 0;

        if(dp[m][n] != -1){
            return (int)dp[m][n];
        }
       
        if(s.charAt(m-1) == t.charAt(n-1)){
            dp[m][n] = ways(dp, m-1,n-1,s,t) + ways(dp,m-1,n,s,t);
        }else{
            dp[m][n] = ways(dp,m-1,n,s,t);
        }
        return (int)dp[m][n];
    }
}