class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int max = nums[0];
        for(int i = 0; i < nums.length; i++){
            int j = i;
            int min = Integer.MAX_VALUE;
            max = Math.max(max,nums[i]);
            while(j < nums.length){
                min = Math.min(min,nums[j]);
                j++;
            }
            if(max-min<=k){
                return i;
            }
        }
        return -1;
    }
}