class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> q = new LinkedList<>();
        boolean direction = false;
        q.offer(root);
        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> li = new LinkedList<>();
            direction = !direction;
            for(int i = 0; i<n; i++){
                TreeNode last = q.poll();
                if (direction) {
                    li.addLast(last.val);
                } else {
                    li.addFirst(last.val);
                }
                if (last.left != null) q.offer(last.left);
                if (last.right != null) q.offer(last.right);
            }
            res.add(li);
        }
        return res;
    }
}