class Solution {
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        int i = 0;
        int j = nums.length-1;
        while(i<j){
            int a = nums[i];
            nums[i] = nums[j];
            nums[j] = a;
            i++;
            j--;
        }
        i = 0;
        j = k-1;
        while(i<j){
            int a = nums[i];
            nums[i] = nums[j];
            nums[j] = a;
            i++;
            j--;
        }
        i = k;
        j = nums.length-1;
        while(i<j){
            int a = nums[i];
            nums[i] = nums[j];
            nums[j] = a;
            i++;
            j--;
        }
        return;
    }
}