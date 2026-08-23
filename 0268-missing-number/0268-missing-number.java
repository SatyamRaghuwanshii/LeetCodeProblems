class Solution {
    public int missingNumber(int[] nums) {
        int[] freq = new int[nums.length+1];
        int j = 0;
        for(int i = 0; i< nums.length; i++){
            int num = nums[i];
            freq[num]++;
        }
        while(j<nums.length && freq[j] != 0){
            j++;
        }
        return j;
    }
}