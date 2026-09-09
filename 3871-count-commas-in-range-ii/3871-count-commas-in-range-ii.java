class Solution {
    public long countCommas(long n) {
        if(n<1000) return 0;
        long count = 0;
        long t = 1000;
        while(t<=n){
            long ans = (n-t+1);
            count += ans;
            t *= 1000;
        }
        return count;
    }
}