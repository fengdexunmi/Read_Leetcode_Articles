class MaxArea {
    public int maxArea(int[] height) {
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            int maxHeight = height[l] < height[r] ? height[l] : height[r];
            while (l < r && height[l] <= maxHeight) {
                l++;
            }
            while (r > l && height[r] <= maxHeight) {
                r--;
            }
        }
        
        return max;
    }
}
