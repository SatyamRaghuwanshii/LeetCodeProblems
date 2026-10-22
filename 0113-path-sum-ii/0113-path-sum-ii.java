class Solution {
    private static List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        dfs(root, targetSum, li);
        return res;
    }
    private void dfs(TreeNode root, int targetSum, List<Integer> li){
       if(root == null) return;
        li.add(root.val);
        if(root.left == null && root.right == null){
            if(root.val == targetSum){
                res.add(li);
            }
        }
        dfs(root.left, targetSum-root.val, new ArrayList<>(li));
        dfs(root.right, targetSum-root.val, new ArrayList<>(li));
    }
}