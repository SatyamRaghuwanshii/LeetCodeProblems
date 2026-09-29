class Solution {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '#'){
                stack.push(s.charAt(i));
            }else{
                if(!stack.isEmpty()) stack.pop();
            }
        }
        for(int i = 0; i < t.length(); i++){
            if(t.charAt(i) != '#'){
                stack2.push(t.charAt(i));
            }else{
                if(!stack2.isEmpty()) stack2.pop();
            }
        }
        if(stack.size() != stack2.size()) return false;
        while(!stack.isEmpty()){
            if(stack2.peek() == stack.peek()){
                stack.pop();
                stack2.pop();
            }else{
                return false;
            }
        }
        return true;
    }
}