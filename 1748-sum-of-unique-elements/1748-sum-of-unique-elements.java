class Solution {
    public int sumOfUnique(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            if(map.get(nums[i])<2){
                sum+=nums[i];
            }
        }
        return sum;
    }
}