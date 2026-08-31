class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0; i<=haystack.length()-needle.length(); i++){
            int j = 0;
            while(j<needle.length()){
                if(haystack.charAt(i+j) == needle.charAt(j)){
                    j++;
                }else{
                    break;
                }
            }
            if(j > needle.length()-1){
                return i;
            }
        }
        return -1;
    }
}