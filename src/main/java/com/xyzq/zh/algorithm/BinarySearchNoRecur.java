package com.xyzq.zh.algorithm;

/**
 * 二分查找-非递归
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int arr[] = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 1);
        System.out.println("查找1对应下标：" + index);
        System.out.println("查找8对应下标：" + binarySearch(arr, 8));
        System.out.println("查找13对应下标：" + binarySearch(arr, 13));
    }

    /**
     * 二分查找非递归实现
     *
     * @param arr    待查找数组
     * @param target 需要查找的数
     * @return 目标值所在下标
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;

            if (arr[mid] < target) {
                //要查找的数在mid右边
                left = mid + 1;
            } else {
                //要查找的数在mid左边
                right = mid - 1;
            }
        }
        return -1;
    }
}
