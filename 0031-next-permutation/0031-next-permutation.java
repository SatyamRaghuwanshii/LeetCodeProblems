class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length-1;
        int j = nums.length-1;
        while(i>0 && nums[i] <= nums[i-1]){
            i--;
        }
        if(i == 0){
            Arrays.sort(nums);
            return;
        }
        int pivot = nums[i-1];
        while(nums[j] <= pivot){
            j--;
        }
        nums[i-1] = nums[j];
        nums[j] = pivot;
        int k = nums.length-1;
        while(i<k){
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
            k--;
            i++;
        }
    }
}