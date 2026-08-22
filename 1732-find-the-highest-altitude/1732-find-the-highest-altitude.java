class Solution {
    public int largestAltitude(int[] gain) {
        int n=gain.length;
        int r=0;
        int c=0;
        for(int i=0;i<n;i++){
            c+=gain[i];
            r=Math.max(r,c);
        }
        return r;
    }
}