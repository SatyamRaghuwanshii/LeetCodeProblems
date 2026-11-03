class Solution {
    public int minMoves(int[] nums) {
        int max = 1;
        for(int i:nums){
            max = Math.max(max,i);
        }
        int moves = 0;
        for(int i:nums){
            moves+=max-i;
        }
        return moves;
    }
}