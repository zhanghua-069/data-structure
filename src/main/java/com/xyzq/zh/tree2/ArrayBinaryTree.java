package com.xyzq.zh.tree2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 顺序存储二叉树
 * 1.顺序存储二叉树一般为完全二叉树
 * 2.第n个元素的左子节点为 2*n+1
 * 3.第n个元素的右子节点为 2*n+2
 * 3.第n个元素的父节点为 (n-1)/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrayBinaryTree {

    private int[] arr;//存储数据节点的数组

    /**
     * 重做前序遍历，index从0开始
     */
    public void preOrder() {
        preOrder(0);
    }

    /**
     * 重做前序遍历，index从0开始
     */
    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * 重做前序遍历，index从0开始
     */
    public void postOrder() {
        postOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {

        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行二叉树遍历");
        }
        //输出当前元素
        System.out.print(arr[index] + " ");

        int lIdx;
        int rIdx;
        //向左递归
        if ((lIdx = 2 * index + 1) < arr.length)
            preOrder(lIdx);

        //向右递归
        if ((rIdx = 2 * index + 2) < arr.length)
            preOrder(rIdx);
    }

    /**
     * 顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index) {

        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行二叉树遍历");
        }
        int lIdx;
        int rIdx;

        //向左递归
        if ((lIdx = 2 * index + 1) < arr.length)
            infixOrder(lIdx);

        //输出当前元素
        System.out.print(arr[index] + " ");

        //向右递归
        if ((rIdx = 2 * index + 2) < arr.length)
            infixOrder(rIdx);
    }

    /**
     * 顺序存储二叉树的后序遍历
     *
     * @param index 数组的下标
     */
    public void postOrder(int index) {

        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行二叉树遍历");
        }

        int lIdx;
        int rIdx;
        //向左递归
        if ((lIdx = 2 * index + 1) < arr.length)
            postOrder(lIdx);

        //向右递归
        if ((rIdx = 2 * index + 2) < arr.length)
            postOrder(rIdx);

        //输出当前元素
        System.out.print(arr[index] + " ");
    }
}
