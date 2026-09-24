class Solution {
    public int smallestIndex(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int sum = digitSum(nums[i]);
            if(sum == i){
                min = Math.min(min,i);
            }
        }
        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }
    private int digitSum(int n){
        int sum = 0;
        while(n != 0){
            int l = n%10;
            sum+=l;
            n /= 10;
        }
        return sum;
    }
}