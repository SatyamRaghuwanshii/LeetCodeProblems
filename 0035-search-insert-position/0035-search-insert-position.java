class Solution {
    public int searchInsert(int[] nums, int target) {
        return search(nums,0,nums.length-1,target);
        
    }
    public int search(int[] nums,int l,int u,int target){
        if(l>u){
            return l;
        }
        int mid = l + (u-l)/2;
        if( nums[mid] == target){
            return mid;
        }else{
            if(nums[mid]>target){
                return search(nums,l,mid-1,target);
            }else{
                return search(nums,mid+1,u,target);
            }
        }
    }
}