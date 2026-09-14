class Solution {
    public int mySqrt(int x) {
        int start = 0;
        int end = x;
        int ans = -1;
        while(start<=end){
            int mid = start + (end-start)/2;
            long sqrt = (long) mid*mid;
            if(sqrt==x){
                return mid;
            }
            if(sqrt<x){
                ans = mid;
                start = mid+1;
            }
            if(sqrt>x){
                end = mid-1;
            }
        }
        return ans;
    }
}