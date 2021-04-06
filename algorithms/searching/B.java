

public class B {
    public static int search(int[] arr, int value) {
        int low = 0;
        int high = arr.length-1;

        while (low <= high) {
           int mid = low + (high - low) /2;
           if (value == arr[mid])
               return mid;
           
           if (value > arr[mid]) // value lies in right side
               low = mid + 1;
           else                 // value lies in left side 
               high = mid -1;
        }
        // if not found return -1;
        return -1;
    }

}
