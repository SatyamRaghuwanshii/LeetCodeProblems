class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root.left != null){
            invert(root.left);
        }
        return isSame(root.left, root.right);
    }
    private void invert(TreeNode root){
        if(root == null) return;
        invert(root.left);
        invert(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    private boolean isSame(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        boolean left = isSame(p.left,q.left);
        boolean right = isSame(p.right,q.right);
        return left && right;
    }
}