class Solution {
    private static int sum;
    public int maxPathSum(TreeNode root) {
        sum = Integer.MIN_VALUE;
        getSum(root);
        return sum;
    }
    private int getSum(TreeNode root){
        if(root == null) return 0;
        int left = Math.max(0,getSum(root.left));
        int right = Math.max(0,getSum(root.right));
        sum = Math.max(sum,root.val + left + right);
        return root.val+Math.max(left,right);
    }
}