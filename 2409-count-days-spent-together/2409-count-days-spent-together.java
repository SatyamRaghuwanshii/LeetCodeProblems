class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArrive = calDays(arriveAlice);
        int aliceLeave = calDays(leaveAlice);
        int bobArrive = calDays(arriveBob);
        int bobLeave = calDays(leaveBob);
        int start = Math.max(aliceArrive, bobArrive);
        int end = Math.min(aliceLeave, bobLeave);
        return Math.max(0,end-start+1);
    }
    private int calDays(String dates){
        int[] day = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int n = Integer.parseInt(dates.substring(0,2));
        int days = day[n-1] + Integer.parseInt(dates.substring(3,5));
        return days;
    }
}