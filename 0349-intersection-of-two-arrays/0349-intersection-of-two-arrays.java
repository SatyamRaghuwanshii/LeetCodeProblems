class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intSet = new HashSet<>();
        for(int i:nums1){
            if(!set.contains(i)){
                set.add(i);
            }
        }
        for(int i:nums2){
            if(set.contains(i)){
                intSet.add(i);
            }
        }
        int[] res = new int[intSet.size()];
        int i = 0;
        for(int num:intSet){
            res[i++] = num;
        }
        return res;
    }
}