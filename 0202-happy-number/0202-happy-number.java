class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        return happy(n,set);
    }
    public boolean happy(int n, Set<Integer> set){
        if(set.contains(n)){
            return false;
        }else{
            set.add(n);
        }
        int sum = 0;
        while(n!=0){
            int digit = n%10;
            sum = sum + digit * digit;
            n = n/10;
        }
        if(sum == 1) return true;
        return happy(sum,set);
    }
}