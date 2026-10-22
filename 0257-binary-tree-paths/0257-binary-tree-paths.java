class Solution {
    private static List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root,sb);
        return res;
    }
    private void dfs(TreeNode root, StringBuilder sb){
        if(root == null) return;
        int len = sb.length();
        sb.append(root.val);
        if(root.left == null && root.right == null){
            res.add(sb.toString());
        }else{
            sb.append("->");
            dfs(root.left,sb);
            dfs(root.right,sb); 
        }
        sb.setLength(len);
    }
}