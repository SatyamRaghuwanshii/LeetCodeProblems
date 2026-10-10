class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> st = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;
        for(int i = 0; i<n; i++){
            while(!st.isEmpty() && heights[st.peek()] > heights[i]){
                int idx = st.pop();
                int width = 0;
                if(st.isEmpty()){
                    width = i;
                }else{
                    int left = idx;
                    int right = idx;
                    while(left >= 0 && heights[idx] <= heights[left]){
                        left--;
                    }
                    while(right < n && heights[idx] <= heights[right]){
                        right++;
                    }
                    width = right-left-1;
                }
                maxArea = Math.max(maxArea, heights[idx]*width);
            }
            st.push(i);
        }
        while(!st.isEmpty()){
            int idx = st.pop();
            int left = st.isEmpty()? -1 : st.peek();
            maxArea = Math.max(maxArea, heights[idx]*(n-left-1));
        }
        return maxArea;
    }
}