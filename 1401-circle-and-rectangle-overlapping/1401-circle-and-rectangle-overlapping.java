class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int dX = 0;
        int dY = 0;
        if (xCenter < x1) {
            dX = x1 - xCenter;
        } else if (xCenter > x2) {
            dX = xCenter - x2;
        }
        if (yCenter < y1) {
            dY = y1 - yCenter;
        } else if (yCenter > y2) {
            dY = yCenter - y2;
        }
        int distance = dX*dX + dY*dY;
        return radius*radius >= distance;
    }
}