class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = m-1;
        while(i<=j){
            int oMid = i + (j-i)/2;
            if(matrix[oMid][0] <= target && matrix[oMid][n-1] >= target){
                int k = 0;
                int l = n-1;
                while(k<=l){
                    int iMid = k + (l-k)/2;
                    if(matrix[oMid][iMid] == target){
                        return true;
                    }else if(matrix[oMid][iMid] < target){
                        k = iMid+1;
                    }else{
                        l = iMid - 1;
                    }
                }
                break;
            }else if(matrix[oMid][0] > target){
                j = oMid-1;
            }else{
                i = oMid+1;
            }
        }
        return false;
    }
}