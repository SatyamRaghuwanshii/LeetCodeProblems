class Solution {
    public int sumNumbers(TreeNode root) {
        if(root.left == null && root.right == null) return root.val;
        int left = 0, right = 0;
        if(root.left != null){
            root.left.val = root.left.val + root.val*10;
            left = sumNumbers(root.left);
        }
        if(root.right != null){
            root.right.val = root.right.val + root.val*10;
            right = sumNumbers(root.right);
        }
        return left + right;
    }
}