class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int num1 = 0, num2 = 0;
        int count1 = 0, count2 = 0;

        for(int i:nums){
            if(i == num1){
                count1++;
            }else if(i == num2){
                count2++;
            }else if(count1 == 0){
                num1 = i;
                count1 = 1;
            }else if(count2 == 0){
                num2 = i;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for(int i:nums){
            if(i == num1) count1++;
            if(i == num2) count2++;
        }

        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        
        if(count1 > n/3) res.add(num1);
        if(count2 > n/3 && !res.contains(num2)) res.add(num2); 

        return res;
    }
}