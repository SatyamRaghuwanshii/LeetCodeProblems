class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int[] arr = new int[nums1.length + nums2.length];
        double median = 0;
        for(int k = 0; k < arr.length; k++){
            if(nums1.length == i){
                arr[k] = nums2[j];
                j++;
            }
            else if(nums2.length == j){
                arr[k] = nums1[i];
                i++;
            }
            else{
                if(nums1[i] < nums2[j]){
                    arr[k] = nums1[i];
                    i++;
                }else{
                    arr[k] = nums2[j];
                    j++;
                }
            }
        }
        if(arr.length%2 == 0){
            int mid1 = (arr.length/2) - 1;
            int mid2 = (arr.length/2);
            median = (double)(arr[mid1] + arr[mid2])/2;
        }else{
            median = arr[arr.length/2];
        }
        return median;
    }
}