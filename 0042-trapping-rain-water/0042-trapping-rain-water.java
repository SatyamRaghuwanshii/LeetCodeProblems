class Solution {
    public int trap(int[] height) {
        int i = 0;
        int j = height.length-1;
        int water = 0;
        int leftMax = 0;
        int rightMax = 0;
        while(i <= j-1){
            if(height[i] <= height[j]){
                leftMax = Math.max(leftMax, height[i]);
                i++;
                int temp = leftMax - height[i];
                if(temp > 0){
                    water += temp;
                }
            }else{
                rightMax = Math.max(rightMax, height[j]);
                j--;
                int temp = rightMax - height[j];
                if(temp > 0){
                    water += temp;
                }
            }
        }
        return water;
    }
}