class Solution {
    public boolean checkPerfectNumber(int num) {
        int total = 1;
        if(num <= 1){
            return false;
        }else{
            for(int i = 2; i*i <= num; i++){
                if(num%i == 0){
                    total += i;
                    if(i != num/i){
                        total += num/i;
                    }
                }
            }
        }
        return total == num;
    }
}