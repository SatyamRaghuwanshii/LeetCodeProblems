class Solution {
    public int rob(TreeNode root) {
        int[] option = travel(root);
        return Math.max(option[0],option[1]);
    }
    private int[] travel(TreeNode root){
        if(root == null) return new int[2];
        int[] left = travel(root.left);
        int[] right = travel(root.right);

        int[] option = new int[2];

        option[0] = root.val + left[1] + right[1];
        option[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);

        return option;
    }
}