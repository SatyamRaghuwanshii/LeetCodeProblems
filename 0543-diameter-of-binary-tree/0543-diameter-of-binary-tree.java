class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int diameter = height(root.left)+height(root.right);
        int left = diameterOfBinaryTree(root.left);
        int right = diameterOfBinaryTree(root.right);
        return Math.max(diameter,Math.max(left,right));
    }
    private int height(TreeNode root){
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return 1 + Math.max(left,right);
    }
}