class Solution {
    public String reversePrefix(String word, char ch) {
        int j = -1;
        for(int i = 0; i<word.length(); i++){
            if(word.charAt(i) == ch){
                j = i;
                break;
            }
        }
        if(j == -1) return word;
        char[] s = word.toCharArray();
        int i = 0;
        while(i<j){
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
        return new String(s);
    }
}