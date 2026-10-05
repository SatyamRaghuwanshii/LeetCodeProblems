class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        int score = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(score);
                score = 0;
            }else{
                if(score == 0){
                    score = st.pop() + 1;
                }else{
                    score = st.pop() + score*2;
                }
            }
            
        }
        return score;
    }
}