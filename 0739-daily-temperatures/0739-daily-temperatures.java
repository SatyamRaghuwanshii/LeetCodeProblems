class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] st = new int[n];
        int top = -1;
        // Deque<Integer> st = new ArrayDeque<>();
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int curr = temperatures[i];
            while(top != -1 && curr > temperatures[st[top]]){
                int prev = st[top--];
                res[prev] = i - prev;
            }
            st[++top] = i;
        }
        return res;
    }
}