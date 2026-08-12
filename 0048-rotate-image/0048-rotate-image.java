class Solution {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        int n = N - 1;
        for(int j = 0; j < N/2; j++){
            for(int i = j; i < n - j; i++){
                int a = matrix[i][n - j];
                int b = matrix[n - j][n - i];
                int c = matrix[n - i][j];

                matrix[i][n - j] = matrix[j][i];
                matrix[n - j][n - i] = a;
                matrix[n - i][j] = b;
                matrix[j][i] = c;
            }
        }
        return;
    }
}