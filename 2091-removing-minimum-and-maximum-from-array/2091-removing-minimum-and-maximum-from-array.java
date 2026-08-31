class Solution {
    public int minimumDeletions(int[] nums) {
        int min = 0;
        int max = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] < nums[min]){
                min = i;
            }
            if(nums[i] > nums[max]){
                max = i;
            }
        }
        int left = Math.max(min,max)+1;
        int right = nums.length-Math.min(min,max);
        int bothSide = Math.min(min,max)+1 + nums.length-Math.max(min,max);
        return Math.min(bothSide,Math.min(left,right));
    }
}