class Solution {
    public int removeAlmostEqualCharacters(String word) {
        int n = word.length();
        int count = 0;
        int i = 0;
        while(i<n-1){
            char left = word.charAt(i);
            char right = word.charAt(i + 1);
            if(Math.abs(left - right) <= 1){
                count++;
                i+=2;
            }else{
                i++;
            }
        }
        return count;
    }
}