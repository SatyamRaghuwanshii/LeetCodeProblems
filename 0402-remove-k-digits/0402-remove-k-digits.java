class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = num.length();
        int count = 0;
        for(char ch : num.toCharArray()){
            int digit = ch - '0';
            while(!st.isEmpty() && count < k && st.peek() > digit){
                st.pop();
                count++;
            }
            st.push(digit);
        }
        while(count < k){
            st.pop();
            count++;
        }
        while(!st.isEmpty() && st.peekLast() == 0){
            st.removeLast();
        }
        StringBuilder ans = new StringBuilder();
        while(!st.isEmpty()){
            ans.append(st.removeLast());
        }
        if(ans.length() == 0){
            return "0";
        }
        return ans.toString();
    }
}