class Solution {
    public String countAndSay(int n) {
        if(n == 1) return "1";
        String say = countAndSay(n-1);
        StringBuilder s = new StringBuilder();
        for(int i = 0; i<say.length(); i++){
            char ch = say.charAt(i);
            int count = 1;
            while(i<say.length()-1 && say.charAt(i) == say.charAt(i+1)){
                count++;
                i++;
            }
            s.append(count);
            s.append(ch);
        }
        return s.toString();
    }
}