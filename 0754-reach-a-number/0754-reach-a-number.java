class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int moves = 0;
        int num = 0;
        while(num <= target){
            moves++;
            num += moves;
            if(num == target){
                break;
            }
            if(num>target){
               int diff = num-target;
               if(diff%2 == 0){
                break;
               }else{
                num = num - (diff*2);
               }
            }
        }
        return moves;
    }
}