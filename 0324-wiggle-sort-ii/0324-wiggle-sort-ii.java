class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] arr = new int[nums.length];
        int j = (nums.length-1)/2;
        int k = nums.length-1;
        for(int i = 0; i <nums.length; i++){
            if(i%2 == 0){
                arr[i] = nums[j];
                j--;
            }else{
                arr[i] = nums[k];
                k--;
            }
        }
        for(int i = 0; i <nums.length; i++){
            nums[i] = arr[i];
        }
    }
}