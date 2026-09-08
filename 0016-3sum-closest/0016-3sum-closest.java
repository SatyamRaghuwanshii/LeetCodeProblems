class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int closestSum = 0;
        for(int i = 0; i < nums.length-1; i++){
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if(sum == target){
                    return sum;
                }
                if(Math.abs(sum-target) < closest){
                    closestSum = sum;
                    closest = Math.abs(sum-target);
                }
                if(sum<target){
                    l++;
                }else{
                    r--;
                }
            }
        }
        return closestSum;
    }
}