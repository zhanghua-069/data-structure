package com.xyzq.zh.tree2;

import java.util.Arrays;

/**
 * 堆排序
 * 升序：大顶堆
 * 降序：小顶堆
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, 11, 7};
        heapSort(arr);
    }

    /**
     * 堆排序（大顶堆）
     * <p>
     * 1.将无序序列构建为一个堆，根据升降序选择大顶堆或小顶堆
     * 2.将堆顶元素与末尾元素交换， 将最大元素"沉"到数组末尾
     * 3.将除末尾的最大值外的新数组重新堆结构调整，然后继续交换堆顶元素与当前末尾元素，反复执行 调整+交换 步骤，直到整个序列有序
     *
     * @param arr
     */
    public static void heapSort(int arr[]) {
        System.out.println("堆排序！！");

        //分步完成
        /*adjustHeap(arr, 1, arr.length);
        System.out.println("第一次调整：" + Arrays.toString(arr));//[4, 9, 8, 5, 6]

        adjustHeap(arr, 0, arr.length);//调整为真正的大顶堆
        System.out.println("第二次调整：" + Arrays.toString(arr));//[9, 6, 8, 5, 4]*/

        //i=arr.length/2-1：第一个非叶子节点
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);//将数组调整成大顶堆
        }

        int temp = 0;
        //交换+调整，j：剩余调整的数组个数
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];//堆顶为最大元素
            arr[0] = temp;

            adjustHeap(arr, 0, j);
            //除首次调整从第一个非叶子节点开始，之后的调整下标都是0开始
            // （经过首次调整后，除堆顶第一个元素因交换发生变更，其他的元素（最后一个除外）都是调整好的大顶堆，因此下标从0开始即可）
        }

        System.out.println("堆排序结果：" + Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树），调整成大顶堆
     * 目的：获取到数组的最大值（最大值为父节点即数组第一个元素）
     *
     * @param arr 待调整的数组
     * @param i   非叶子节点在数组中的下标
     * @param len 对多少个元素继续调整（len随递归减少）
     */
    public static void adjustHeap(int arr[], int i, int len) {
        int temp = arr[i];//取出非叶子节点的值
        //开始调整（k：i的左子节点）
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            //比较左子节点与右子节点，找出最大的那个
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                //左子节点<右子节点，将k指向右子节点
                k++;
            }
            if (arr[k] > temp) {//子节点大于父节点
                arr[i] = arr[k];//将子节点的值赋给父节点
                i = k;//将i指向k，继续循环比较
            } else {
                break;
                //！！！这里可以直接break的原因：当前方法是按数组"从左至右"，"从下至上"调整的，
                // 因此如果当前arr[k]有子节点，那么必然在上一次调用该方法时已被调整过
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树（局部，非整棵二叉树）的最大值放到了最顶上
        arr[i] = temp;//将temp（本次调整的非叶子节点）值放到最终i所在的位置（i一直往下替换）
    }

}
