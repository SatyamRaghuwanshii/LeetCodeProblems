class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int areaA = Math.abs((ax1 - ax2) * (ay1 - ay2));
        int areaB = Math.abs((bx1 - bx2) * (by1 - by2));

        int left = Math.max(ax1,bx1);
        int right = Math.min(ax2,bx2);
        int bottom = Math.max(ay1,by1);
        int top = Math.min(ay2,by2);

        int areaO = 0;

        if(right-left>0 && top-bottom>0) areaO =(right - left) * (top - bottom);

        return areaA + areaB - areaO;
    }
}