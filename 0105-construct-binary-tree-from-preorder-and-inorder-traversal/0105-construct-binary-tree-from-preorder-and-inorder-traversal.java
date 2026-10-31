class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i],i);
        }
        TreeNode root = buildTree(
            map, preorder, inorder,
            0, preorder.length-1,
            0, inorder.length-1
        );
        return root;
    }
    private TreeNode buildTree
    (
        Map<Integer,Integer> map,
        int[] preorder, int[] inorder,
        int preStart, int preEnd,
        int inStart, int inEnd
    ){
        if(inStart>inEnd || preStart>preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int middle = map.get(preorder[preStart]);
        int leftSize = middle - inStart;
        root.left = buildTree(
            map, preorder, inorder,
            preStart+1, preStart+leftSize,
            inStart,middle-1
        );
        root.right = buildTree(
            map, preorder, inorder,
            preStart + leftSize + 1, preEnd,
            middle+1, inEnd
        );
        return root;
    }
}