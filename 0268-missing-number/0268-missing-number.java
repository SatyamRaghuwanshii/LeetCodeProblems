class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (n*(n+1))/2;
        int num = 0;
        for(int i = 0; i < n; i++){
            num += nums[i];
        }
        return sum-num;
    }
}