class InsersionSort{

    public static int[] sort(int[] inArray) {
        for (int i=1; i<inArray.length; i++) {
            int key = inArray[i];
            int j = i-1;
            for (; j>=0 && inArray[j]>key; j--){
                // System.out.println(i+" "+j+" "+key);
                inArray[j+1] = inArray[j];
            }
            inArray[j+1] = key;
        }
        return inArray;
    }
} 
