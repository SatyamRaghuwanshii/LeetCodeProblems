class Solution {
    public boolean canAliceWin(int n) {
        int prev = 10;
        int k = 0;
        while(n >= prev){
            n -= prev;
            prev--;
            k++;
        }
        return k%2 == 1;
    }
}