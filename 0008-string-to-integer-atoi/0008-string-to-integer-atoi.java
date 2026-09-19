class Solution {
    public int myAtoi(String s) {
        if(s.length() == 0) return 0;
        return numberString(s);
    }
    private int numberString(String s){
        s = s.strip();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                sb.append(ch);
            }else if(i == 0 && (ch == '-' || ch == '+')){
                sb.append(ch);
            }else{
                return number(sb.toString());
            }
        }
        return number(sb.toString());
    }
    private int number(String s){
        if(s.isEmpty()) return 0;
        int res = 0;
        boolean isNegative = false;
        int i = 0;
        if(s.charAt(i) == '-'){
            isNegative = true;
            i = 1;
        }else if(s.charAt(i) == '+'){
            i = 1;
        }
        while(i<s.length()){
            char ch = s.charAt(i);
            int digit = ch - '0';
            if(isNegative && (res > 214748364 || (res == 214748364 && digit >= 8))){
                res = Integer.MIN_VALUE;
                break;
            }else if(res > 214748364 || (res == 214748364 && digit > 7)){
                res = Integer.MAX_VALUE;
                break;
            }
            res = (res*10) + digit;
            i++;
        }
        if(isNegative){
            res = -res;
        }
        return res;
    }
}