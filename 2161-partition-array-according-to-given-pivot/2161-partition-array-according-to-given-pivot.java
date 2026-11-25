class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();
        for(int i = 0; i<n; i++){
            int curr = nums[i];
            if(curr < pivot){
                left.add(curr);
            }else if(curr == pivot){
                right.addFirst(curr);
            }else{
                right.add(curr);
            }
        }
        int[] res = new int[n];
        int ptr = 0;
        for(int i:left){
            res[ptr++] = i;
        }
        for(int i:right){
            res[ptr++] = i;
        }
        return res;
    }
}