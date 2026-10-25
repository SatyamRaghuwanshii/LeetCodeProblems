class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i<n; i++){
                TreeNode last = q.poll();
                max = Math.max(max,last.val);
                if(last.left != null) q.offer(last.left);
                if(last.right != null) q.offer(last.right);
            }
            res.add(max);
        }
        return res;
    }
}