class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(res, root);
        return res;
    }
    private void inOrder(List<Integer> res, TreeNode root){
        if(root != null){
            inOrder(res, root.left);
            res.add(root.val);
            inOrder(res, root.right);
        }
    }
}