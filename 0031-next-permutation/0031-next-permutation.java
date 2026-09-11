class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length-1;
        int j = i;
        while(i>0 && nums[i] <= nums[i-1]){
            i--;
        }
        if(i == 0){
            reverse(nums,0);
        }else{
            while(nums[j] <= nums[i-1]){
                j--;
            }
            swap(nums,i-1,j);
            reverse(nums,i);
        }
    }
    void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    void reverse(int[] arr, int start){
        int l = start;
        int r = arr.length-1;
        while(l<r){
            swap(arr,l,r);
            l++;
            r--;
        }
    }
}
