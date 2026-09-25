class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] row = new HashSet[9];
        Set<Character>[] col = new HashSet[9];
        Set<Character>[] box = new HashSet[9];
        for(int i = 0; i < 9; i++){
            box[i] = new HashSet<>();
            row[i] = new HashSet<>();
            col[i] = new HashSet<>();
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char ch = board[j][i];
                if(ch == '.') continue;
                int idx = i/3 * 3 + j/3;
                if(row[j].contains(ch) || col[i].contains(ch) || box[idx].contains(ch)){
                    return false;
                }
                row[j].add(ch);
                col[i].add(ch);
                box[idx].add(ch);
            }
        }
        return true;
    }
}