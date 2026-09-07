class Solution {
    static int mod = 1000000007;
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        return (int)sub(last, 0, 1, s);
    }
    private long sub(long[] last, int n, long count, String s){
        if(n == s.length()) {
            return (count - 1 + mod) % mod;
        }
        long oldCount = count;
        if(last[s.charAt(n) - 'a'] == 0){
            count = (count*2) % mod;
        }else{
            count = (count*2 - last[s.charAt(n) - 'a'] + mod) % mod;
        }
        last[s.charAt(n) - 'a'] = oldCount;
        return sub(last, n+1, count, s);
    }
}