class Solution {
    public boolean isPalindrome(int x) {
        int temp = x;
        int rev = 0;
        if(x<0){
            return false;
        }
        while(x!=0){
            int s = x%10;
            rev = rev*10+s;
            x /= 10;
        }
        return temp==rev;
    }
}