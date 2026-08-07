class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                if(!stack.empty()){
                    stack.pop();
                    if(stack.empty()){
                        stack.push(i);
                    }
                }
                if(!stack.empty()){
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}