class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int[] suffix = new int[nums.length];
        int max = nums[0];
        int min = nums[nums.length-1];
        int i = 0;
        int j = nums.length-1;
        while(j>=0){
            min = Math.min(min,nums[j]);
            suffix[j] = min;
            j--;
        }
        while(i < nums.length){
            max = Math.max(max,nums[i]);
            if(max-suffix[i]<=k){
            return i;
            }
            i++;
        }
        return -1;
    }
}