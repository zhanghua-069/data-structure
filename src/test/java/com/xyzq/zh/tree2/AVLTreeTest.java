package com.xyzq.zh.tree2;

import org.junit.Test;

public class AVLTreeTest {

    @Test
    public void testLeftRotate() {
        int arr[] = {4, 3, 6, 5, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++)
            avlTree.add(new AVLTree.Node(arr[i]));

        System.out.println("遍历avl树~");
        avlTree.infixOrder();
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("树的左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("当前树的根节点：" + avlTree.getRoot());
    }

    @Test
    public void testRightRotate() {
        int arr[] = {10, 12, 8, 9, 7, 6};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++)
            avlTree.add(new AVLTree.Node(arr[i]));

        System.out.println("遍历avl树~");
        avlTree.infixOrder();
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("树的左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("当前树的根节点：" + avlTree.getRoot());
    }

    /**
     * 双旋转测试
     */
    @Test
    public void testDoubleRotate() {
        int arr[] = {10, 11, 8, 9, 7, 6};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++)
            avlTree.add(new AVLTree.Node(arr[i]));

        System.out.println("遍历avl树~");
        avlTree.infixOrder();
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("树的左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度：" + avlTree.getRoot().rightHeight());
        System.out.println("当前树的根节点：" + avlTree.getRoot());
    }
}
