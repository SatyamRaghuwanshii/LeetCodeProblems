class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        if(root != null) q.offer(root);
        while(!q.isEmpty()){
            List<Integer> li = new ArrayList<>();
            int n = q.size();
            for(int i = 0; i<n; i++){
                TreeNode last = q.poll();
                li.add(last.val);
                if(last.left != null){
                    q.offer(last.left);
                }
                if(last.right != null){
                    q.offer(last.right);
                }
            }
            res.addFirst(li);
        }
        return res;
    }
}