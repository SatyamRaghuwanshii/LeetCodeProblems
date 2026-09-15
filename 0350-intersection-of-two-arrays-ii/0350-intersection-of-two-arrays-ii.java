class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:nums1){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        int[] res = new int[Math.min(nums1.length,nums2.length)];
        int idx = 0;
        for(int i:nums2){
            if(map.containsKey(i) && map.get(i)>0){
                res[idx++] = i;
                map.put(i,map.get(i)-1);
            }
        }
        return Arrays.copyOf(res,idx);
    }
}