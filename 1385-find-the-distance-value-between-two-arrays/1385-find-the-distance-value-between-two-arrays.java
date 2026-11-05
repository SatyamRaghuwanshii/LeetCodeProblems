class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int dis = 0;
        boolean violate = false;
        for(int i = 0; i<arr1.length; i++){
            for(int j = 0; j<arr2.length; j++){
                if(Math.abs(arr1[i] - arr2[j]) <= d){
                    violate = !violate;
                    break;
                }
            }
            if(violate){
                violate = !violate;
                continue;
            }
            dis++;
        }
        return dis;
    }
}