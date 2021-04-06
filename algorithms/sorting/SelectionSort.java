class SelectionSort{
    public static int[] sort(int[] inArray) {
        for (int i=0; i<inArray.length; i++) {
            int minValueIndex = i;
            for (int j=i+1; j<inArray.length; j++) {
                if (inArray[j] < inArray[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            // swap with minValue
            int temp = inArray[i];
            inArray[i] = inArray[minValueIndex];
            inArray[minValueIndex] = temp;
        }
        return inArray;
    }
}
