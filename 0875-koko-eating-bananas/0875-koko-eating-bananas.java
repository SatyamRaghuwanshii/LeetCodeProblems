class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for(int i:piles){
            high = Math.max(high,i);
        }
        while(low<high){
            int mid = low + (high-low)/2;
            int hours = calcHour(piles, mid);
            if(hours <= h){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }
    private int calcHour(int[] piles, int n){
        int hours = 0;
        for(int i:piles){
            hours +=  (i + n - 1) / n;
        }
        return hours;
    }
}