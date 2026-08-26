class Solution {
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        
        rot(nums, 0, nums.length-1);
        rot(nums, 0, k-1);
        rot(nums, k, nums.length-1);
        return;
    }
    private int[] rot(int[] nums, int i, int j){
        while(i<j){
            int a = nums[i];
            nums[i] = nums[j];
            nums[j] = a;
            i++;
            j--;
        }
        return nums;
    }
}
