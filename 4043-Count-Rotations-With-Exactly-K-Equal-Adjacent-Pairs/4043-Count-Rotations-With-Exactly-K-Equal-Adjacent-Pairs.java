class Solution {
    public int countRotations(String s, int k) {
        int result = 0;
        for(int i = 0; i<s.length(); i++){
            int score = 0;
            int j = i;
            while(j < s.length()+i-1) {
                if(s.charAt(j % s.length()) == s.charAt((j+1) % s.length())){
                    score++;
                }
                j++;
            }
            if(score == k){
                result++;
            }
        }
        return result;
    }
}