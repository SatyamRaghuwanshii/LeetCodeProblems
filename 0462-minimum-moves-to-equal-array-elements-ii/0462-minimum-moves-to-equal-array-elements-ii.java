class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int med;
        if(n%2 != 0){
            med = nums[n/2];
        }else{
            med = (nums[n/2] + nums[(n/2)-1])/2;
        }
        int moves = 0;
        for(int i:nums){
            moves+=Math.abs(i-med);
        }
        return moves;
    }
}