
class MergeSort{
    
    public int[] sort(int[] inArray) {
        this.divide(inArray, 0, inArray.length -1);
        return inArray;
    }

    private void divide(int[] inArray, int left, int right) {
        if (left < right) {
            int mid = (int)(left + right)/2;
            this.divide(inArray, left, mid);
            this.divide(inArray, mid+1, right);
            this.merge(inArray, left, mid, right);
        }
    }

    private void merge(int[] inArray, int left, int mid, int right){
        int[] arrayL = new int[mid-left+1];
        int[] arrayR = new int[right-mid];

        // copy data to left and right array
        for (int i=0; i<arrayL.length; i++)
            arrayL[i] = inArray[left+i];
        for (int i=0; i<arrayR.length; i++)
            arrayR[i] = inArray[mid+1+i];

        int i = 0;
        int j = 0;
        int k = left;

        while (i < arrayL.length && j < arrayR.length) {
            if (arrayL[i] <= arrayR[j]){
                inArray[k] = arrayL[i];
                i++;
            }
            else {
                inArray[k] = arrayR[j];
                j++;
            }
            k++;
        }

        while (i < arrayL.length) {
            inArray[k] = arrayL[i];
            i++;
            k++;
        }
        while (j < arrayR.length) {
            inArray[k] = arrayR[j];
            j++;
            k++;
        }
    }
}
