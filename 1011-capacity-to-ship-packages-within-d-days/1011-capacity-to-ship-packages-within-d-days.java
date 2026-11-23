class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;
        for(int i:weights){
            low = Math.max(low,i);
            high += i;
        }
        while(low<high){
            int mid = low + (high-low)/2;
            int d = calcDays(weights, mid);
            if(days >= d){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }
    private int calcDays(int[] weights, int capacity) {
        int days = 1;
        int total = 0;
        for (int i : weights) {
            if (total + i > capacity) {
                days++;
                total = i;
            } else {
                total += i;
            }
        }
        return days;
    }
}