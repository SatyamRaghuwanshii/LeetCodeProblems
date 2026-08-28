class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> total = new ArrayList<>();
        for(int i = 0; i<nums.length-1; i++){
            int l = i+1;
            int r = nums.length-1;
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if(sum>0){
                    r--;
                }else if(sum<0){
                    l++;
                }else{
                    total.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while(l<r && nums[l] == nums[l-1]){
                        l++;
                    }
                    while(l<r && nums[r] == nums[r+1]){
                        r--;
                    }
                }
            }
        }
        
        return total;
    }
}