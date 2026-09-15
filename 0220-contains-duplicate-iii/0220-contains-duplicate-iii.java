class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i<nums.length; i++){
            int diff = valueDiff + nums[i];
            if(set.floor(diff) != null && set.floor(diff) >= nums[i] - valueDiff){
                return true;
            }
            set.add(nums[i]);
            if(set.size()>indexDiff){
                set.remove(nums[i-indexDiff]);
            }
        }
        return false;
    }
}