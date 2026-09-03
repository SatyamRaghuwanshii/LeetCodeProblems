class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] pos = new int[2];
        int i = 0;
        int  j = nums.length-1;
        while(i<j){
            int sum = nums[i] + nums[j];
            if(sum == target){
                pos[0] = i+1;
                pos[1] = j+1;
                break;
            }
            if(sum>target){
                j--; 
            }
            if(sum<target){
                i++;
            }
        }
        return pos;
    }
}