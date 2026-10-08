class Solution {
    public String removeOuterParentheses(String s) {
        char[] st = new char[s.length()];
        int top = -1;
        int open = 0;
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(') open++;
            if(open>1){
                st[++top] = ch;
            }
            if(ch == ')') open--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(st, 0, top+1);
        return sb.toString();
    }
}