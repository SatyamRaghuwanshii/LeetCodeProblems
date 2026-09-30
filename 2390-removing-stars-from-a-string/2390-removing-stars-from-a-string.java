class Solution {
    public String removeStars(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '*' && top != -1){
                top--;
            }else{
                top++;
                stack[top] = s.charAt(i);
            }
        }
        if(top == -1) return "";
        return new String(stack, 0, top+1);
    }
}