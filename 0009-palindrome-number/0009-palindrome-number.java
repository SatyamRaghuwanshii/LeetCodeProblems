class Solution {
    public boolean isPalindrome(int x) {
    String s = String.valueOf(x);
    String r = new StringBuilder(s).reverse().toString();

    return s.equals(r);
    }
}