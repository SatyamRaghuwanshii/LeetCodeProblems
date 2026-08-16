class Solution {
    public int maxProfit(int[] p) {
        int maxProfit = 0;
        int minP = p[0];
        for(int i = 1; i<p.length; i++){
            if( p[i] < minP ){
                minP = p[i];
            }
            int profit = p[i] - minP;
            maxProfit = Math.max(maxProfit , profit);
        }
        return maxProfit;
    }
}