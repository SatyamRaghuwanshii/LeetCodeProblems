class Solution {
    private int count;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        return inOrder(root, k);
    }
    private int inOrder(TreeNode root, int k){
        if(root == null) return -1;
        int left = inOrder(root.left, k);
        if(left != -1) return left;
        count++;
        if(k == count) return root.val;
        int right = inOrder(root.right, k);
        return right;
    }
}