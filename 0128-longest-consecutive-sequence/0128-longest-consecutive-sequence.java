class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i:nums){
            set.add(i);
        }
        int max = 0;
        for(int i : set){
            if(!set.contains(i - 1)) {
                int j = 0;
                while(set.contains(i + j)){
                    j++;
                }
                max = Math.max(max, j);
            }
        }
        return max;
    }
}