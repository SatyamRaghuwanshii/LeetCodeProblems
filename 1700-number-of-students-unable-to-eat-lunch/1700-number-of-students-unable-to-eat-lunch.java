class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int n = students.length;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            q.offer(students[i]);
        }
        int r = 0;
        int i = 0;
        while(!q.isEmpty() && r != q.size()){
            if(q.peek() == sandwiches[i]){
                q.poll();
                i++;
                r = 0;
            }else{
                int temp = q.poll();
                q.offer(temp);
                r++;
            }
        }
        return q.size();
    }
}