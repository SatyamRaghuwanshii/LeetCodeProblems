class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> st = new ArrayDeque<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums2.length; i++){
            while(!st.isEmpty() && nums2[i] > st.peek()){
                map.put(st.pop(),nums2[i]);
            }
            st.push(nums2[i]);
        }
        while (!st.isEmpty()) {
            map.put(st.pop(), -1);
        }
        for(int i = 0; i < nums1.length; i++){
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }
}