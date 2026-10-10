class Solution {
    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length()-1;
        StringBuilder sb = new StringBuilder(s);
        while(i<j){
            while(i<j && !isVowel(s.charAt(i))){
                i++;
            }
            while(i<j && !isVowel(s.charAt(j))){
                j--;
            }
            char temp = s.charAt(j);
            sb.setCharAt(j,s.charAt(i));
            sb.setCharAt(i,temp);
            i++;
            j--;
        }
        return sb.toString();
    }
    private boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) != -1;
    }
}