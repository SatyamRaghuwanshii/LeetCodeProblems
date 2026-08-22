class Solution {
    public double myPow(double x, int n) {
      long N = n;
      if(N<0){
        x = 1/x;
        N = -N;
      }
      return power(x, N);
    }
    private double power(double x, long N){
        if(N == 0){
        return 1;
        }
        double hf = power(x,N/2);
        double sq = hf*hf;
        if(N%2 != 0){
            sq *= x;
        }
        return sq;
    }
}