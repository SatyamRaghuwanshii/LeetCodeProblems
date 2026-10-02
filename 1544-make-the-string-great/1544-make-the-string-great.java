class Solution {
    public String makeGood(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!st.isEmpty() && Math.abs(st.peek() - ch) == 32){
                st.pop();
            }else{
                st.push(ch);
            }
        }
        for(char ch : st){
            sb.append(ch);
        }
        return sb.toString();
    }
}