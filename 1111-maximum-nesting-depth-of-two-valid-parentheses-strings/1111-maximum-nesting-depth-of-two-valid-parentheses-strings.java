class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] res = new int[seq.length()];
        int count = 0;
        int n = seq.length();
        for(int i = 0; i < n; i++){
            char ch = seq.charAt(i);
            if (seq.charAt(i) == '(') {
                count++;
                res[i] = count % 2;
            } else {
                res[i] = count % 2;
                count--;
            }
        }
        return res;
    }
}