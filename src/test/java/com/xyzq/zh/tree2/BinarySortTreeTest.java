package com.xyzq.zh.tree2;

import org.junit.Test;

public class BinarySortTreeTest {

    @Test
    public void testAdd() {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++)
            tree.add(new BinarySortTree.Node(arr[i]));

        System.out.println("二叉排序树构建完成，进行中序遍历~~");
        tree.infixOrder();

        //测试删除叶子节点：2,5,9,12
//        tree.delNode(2);
//        tree.delNode(5);
//        tree.delNode(9);
//        tree.delNode(12);
//        System.out.println("测试删除叶子节点~~");
//        tree.infixOrder();

        //删除一棵子树的节点：1
//        tree.delNode(1);
//        System.out.println("测试删除有一棵子树的节点~~");
//        tree.infixOrder();

        //删除有两棵子树的节点：7，,3，,10
//        tree.delNode(3);
        tree.delNode(7);
//        tree.delNode(10);
        System.out.println("测试删除有两棵子树的节点~~");
        tree.infixOrder();
    }

}
