
public class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid -1;
            }
            if (nums[mid] == target) {
                return mid;
            }

        }
        return -1;
    }

    public int firstOccurence(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n -1;

        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) /2;

            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target)  {
                right = mid -1;
            }
            if (nums[mid] == target) {
                ans = mid;
                right = mid -1;
            }
        }

        return ans;
    }

    public int lastOccurence(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n -1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] == target) {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    public int leastGreater(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n -1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) /2;

            if (nums[mid] <= target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                ans = mid;
                right = mid -1;
            }
        }
        return ans;
    }

    public int greatestLeast(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int left = 0;
        int right = n -1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] < target) {
                ans = mid;
                right = mid +1;
            }
            if (nums[mid] >= target) {
                right = mid -1;
            }
        }

        return ans;
    }


}
