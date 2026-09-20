class Solution {
    public int reverseDegree(String s) {
        int res = 0;
        for(int i = 0; i<s.length(); i++){
            res = res + (i+1)*(123 - s.charAt(i)); // also ('z' - s.charAt(i)) + i+1;
        }
        return res;
    }
}