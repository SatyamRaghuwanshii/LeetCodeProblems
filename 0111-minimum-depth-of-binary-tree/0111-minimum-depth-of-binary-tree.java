class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int count = 1;
        if(root.left != null && root.right != null) {
            count += Math.min(minDepth(root.left),minDepth(root.right));
        }else{
            count += Math.max(minDepth(root.left),minDepth(root.right));
        }
        return count;
    }
}