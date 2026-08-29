class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int l = 0;
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == ' '){
                reverse(chars,l,i-1);
                l = i+1;
            }
        }
        reverse(chars,l,chars.length-1);
        return new String(chars);
    }
    private void reverse(char[] chars, int l,int r){
        while(l<r){
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}