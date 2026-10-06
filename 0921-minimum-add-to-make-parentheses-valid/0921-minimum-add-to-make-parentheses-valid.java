class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int close = 0;
        for(int i = 0; i < s.length(); i++){
            int ch  = s.charAt(i);
            if(ch == '('){
                open++;
            }else if(ch == ')' && open > 0){
                open--;
            }else{
                close++;
            }
        }
        return open + close;
    }
}