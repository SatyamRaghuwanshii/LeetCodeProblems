class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = matrix[0].length-1;
        int top = 0;
        int bottom = matrix.length-1;
        int count = 1;
        while(left <= right && top <= bottom){
            for(int i = left; i <= right; i++){
                matrix[top][i] = count;
                count++;
            }
            top++;
            for(int j = top; j <= bottom; j++){
                matrix[j][right] = count;
                count++;
            }
            right--;
            for(int i = right; i >= left; i--){
                matrix[bottom][i] = count;
                count++;
            }
            bottom--;
            for(int j = bottom; j >= top;  j--){
                matrix[j][left] = count;
                count++;
            }
            left++;
        }
        return matrix;
    }
}