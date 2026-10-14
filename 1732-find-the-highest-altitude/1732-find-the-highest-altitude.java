class Solution {
    public int largestAltitude(int[] gain) {
        int res = 0;
        int curr = 0;
        for(int i : gain){
            curr+=i;
            if(curr>res){
                res=curr;
            }
        }
        return res;
    }
}