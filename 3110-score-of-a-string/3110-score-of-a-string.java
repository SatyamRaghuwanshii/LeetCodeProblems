class Solution {
    public int scoreOfString(String s) {
        int sum = 0;
        for(int i = 0; i < s.length()-1; i++){
            int curr = Math.abs(s.charAt(i) - s.charAt(i+1));
            sum += curr;
        }
        return sum;
    }
}