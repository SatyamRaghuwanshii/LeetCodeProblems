class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int n = s.length();
        int maxLen = 0;
        for(int i = 0; i<s.length(); i++){
            int odd = expand(s,i,i);
            int even = expand(s,i,i+1);
            int currLen = 0;
            int currStart = 0;
            if(odd>even){
                currLen = odd;
                currStart = i - odd/2;
            }else{
                currLen = even;
                currStart = i - even/2 + 1;
            }
            if(currLen>maxLen){
                maxLen = currLen;
                start = currStart;
            }
        }
        return s.substring(start,start+maxLen);
    }
    private int expand(String s, int left, int right){
        while((left>=0 && right<s.length()) && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
}