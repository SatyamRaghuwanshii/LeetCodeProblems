class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> li = new LinkedList<>();
            for(int i = 0; i<n; i++){
                TreeNode last = q.poll();
                li.add(last.val);
                if(last.left != null) q.offer(last.left);
                if(last.right != null) q.offer(last.right);
            }
            res.add(li.getLast());
        }
        return res;
    }
}