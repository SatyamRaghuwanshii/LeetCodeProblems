class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;
        int first = -1;
        while(i<=j){
            int mid = i + (j - i) / 2;
            if(nums[mid] == target){
                first = mid;
                while (first > 0 && nums[first - 1] == target) {
                    first--;
                }
                break;
            }
            if(nums[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }
        while(first != -1 && first<nums.length && nums[first] == target){
            res.add(first);
            first++;
        }
        return res;
    }
}