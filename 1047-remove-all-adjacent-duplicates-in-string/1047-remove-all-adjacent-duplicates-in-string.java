class Solution {
    public String removeDuplicates(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        for(char ch : s.toCharArray()){
            if(top == -1){
                stack[++top] = ch;
            }else if(stack[top] == ch){
                top--;
            }else{
                stack[++top] = ch;
            }
        }
        if(top == -1) return "";
        StringBuilder sb = new StringBuilder(top+1);
        sb.append(stack, 0, top+1);
        return sb.toString();
    }
}