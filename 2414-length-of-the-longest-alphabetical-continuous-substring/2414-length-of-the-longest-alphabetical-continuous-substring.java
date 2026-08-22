class Solution {
    public int longestContinuousSubstring(String s) {
        int cha = 97;
        int count = 0;
        int maxCount = 0;
        for(int i = 0; i<s.length(); i++){
            if((s.charAt(i) - cha) == 0){
                count++;
                cha++;
            }else{
                count = 1;
                char c = s.charAt(i);
                cha = c+1;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}