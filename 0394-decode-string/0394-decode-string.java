class Solution {
    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<StringBuilder> string = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(char ch:s.toCharArray()){
            if(Character.isDigit(ch)){
                num = num*10 + ch - '0';
            }else if(ch == '['){
                count.push(num);
                string.push(sb);
                sb = new StringBuilder();
                num = 0;
            } else if( ch == ']'){
                int repeat = count.pop();
                StringBuilder prev = string.pop();

                for(int i = 0; i < repeat; i++){
                    prev.append(sb);
                }
                sb = prev;
            }else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}