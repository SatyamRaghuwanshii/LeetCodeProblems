class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int m = l + (r-l)/2;
            if(nums[m] == target){
                res[0] = m;
                r = m-1;
            }
            else if(target>nums[m]){
                l = m+1;
            }
            else{
                r = m-1;
            }
        }
        if(res[0] == -1){
            return res;
        }
        l = 0;
        r = nums.length-1;
        while(l<=r){
            int m = l + (r-l)/2;
            if(nums[m] == target){
                res[1] = m;
                l = m+1;
            }
            else if(target<nums[m]){
                r = m-1;
            }
            else{
                l = m+1;
            }
        }
        return res;
    }
}