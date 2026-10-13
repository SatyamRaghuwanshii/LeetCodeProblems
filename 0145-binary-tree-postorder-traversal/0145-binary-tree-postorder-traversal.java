class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(res,root);
        return res;
    }
    private void postOrder(List<Integer> res,TreeNode root){
        if(root != null){
            postOrder(res, root.left);
            postOrder(res, root.right);
            res.add(root.val);
        }
    }
}