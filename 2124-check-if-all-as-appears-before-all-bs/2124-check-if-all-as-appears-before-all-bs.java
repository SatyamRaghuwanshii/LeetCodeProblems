class Solution {
    public boolean checkString(String s) {
        int a = s.lastIndexOf('a');
        int b = s.indexOf('b');
        return (b == -1 || a == -1) ? true : b > a;
    }
}