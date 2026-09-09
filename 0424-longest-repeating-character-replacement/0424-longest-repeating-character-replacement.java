class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        int maxFreq = 0;
        int left = 0;
        int result = 0;
        for(int i = 0; i<n; i++){
            freq[s.charAt(i) - 'A']++;
            maxFreq = Math.max(maxFreq,freq[s.charAt(i) - 'A']);
            while(i-left+1 - maxFreq > k){
                freq[s.charAt(left)-'A']--;
                left++;
            }
            if(i-left+1 - maxFreq <= k){
                result = Math.max(result, i-left+1);
            }
        }
        return result;
    }
}