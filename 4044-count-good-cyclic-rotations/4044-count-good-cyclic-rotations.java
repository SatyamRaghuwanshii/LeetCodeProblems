class Solution {
    public int countGoodRotations(int[] nums) {
        int n = nums.length;
        int result = 0;
        long totalSum = 0;
        long currentSum = 0;
        for(int i:nums){
            totalSum+=i;
        }
        for(int i= 0; i < n-n/2; i++){
            currentSum += nums[i];
        }
        if(totalSum - currentSum < currentSum){
            result++;
        }
        for(int i = 1; i < nums.length; i++){
            currentSum = currentSum - nums[i-1] + nums[((n/2)-1+i) % n];
            if(totalSum - currentSum < currentSum){
                result++;
            }
        }
        return result;
    }
}