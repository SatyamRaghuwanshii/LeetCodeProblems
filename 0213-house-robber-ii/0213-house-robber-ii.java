class Solution {
    public int rob(int[] nums) {
       if(nums.length<2) return nums[0];
        
        return Math.max(robRange(nums, 1,nums.length-1), robRange(nums,0,nums.length-2));
    }
    private int robRange(int[] nums, int start, int end){
        if(end<2) return nums[start];
        int n = end-start+1;
        int[] dp = new int[n];
        dp[0] = nums[start];
        if(n>1){
          dp[1]= Math.max(nums[start],nums[start+1]);  
        }
        for(int i = 2; i<n; i++){
            dp[i] = Math.max(nums[start+i] + dp[i-2] , dp[i-1]);
        }
        return dp[n-1];
    }
}