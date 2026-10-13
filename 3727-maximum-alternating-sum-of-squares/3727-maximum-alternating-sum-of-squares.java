class Solution {
    public long maxAlternatingSum(int[] nums) {
        long sum = 0;
        for(int i = 0; i < nums.length; i++){
            nums[i] = nums[i]*nums[i];
        }
        Arrays.sort(nums);
        for(int i = nums.length-1; i >= 0; i--){
            if(i>=nums.length/2){
                sum+= nums[i];
            }else{
                sum-= nums[i];
            }
        }
        return sum;
    }
}