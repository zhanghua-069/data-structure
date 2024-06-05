package com.xyzq.zh.tree2;

import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void testOrder() {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.Node root = new BinaryTree.Node(1, "宋江");
        BinaryTree.Node node1 = new BinaryTree.Node(2, "吴用");
        BinaryTree.Node node2 = new BinaryTree.Node(3, "卢俊义");
        BinaryTree.Node node3 = new BinaryTree.Node(4, "林冲");
        BinaryTree.Node node4 = new BinaryTree.Node(5, "关胜");

        binaryTree.setRoot(root);
        root.setLeft(node1);
        root.setRight(node2);
        node2.setRight(node3);
        node2.setLeft(node4);
        //前序遍历
//        binaryTree.preOrder();

        //前序查找
        BinaryTree.Node rn = binaryTree.postOrderSearch(3);
        if (rn == null) {
            System.out.println("没有找到对应节点");
        } else {
            System.out.println(rn);
        }

        System.out.println("删除前前序遍历~~");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后前序遍历~~");
        binaryTree.preOrder();
    }

    @Test
    public void testArrBinaryTree() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree binaryTree = new ArrayBinaryTree(arr);
        System.out.println("顺序存储二叉树前序遍历：");
        binaryTree.preOrder();
        System.out.println();

        System.out.println("顺序存储二叉树中序遍历：");
        binaryTree.infixOrder();
        System.out.println();

        System.out.println("顺序存储二叉树后序遍历：");
        binaryTree.postOrder();
        System.out.println();
    }

    //中序数组：[8,3,10,1,14,6]
    @Test
    public void testThreadedBinaryTree() {
        ThreadedBinaryTree.Node root = new ThreadedBinaryTree.Node(1, "Tom");
        ThreadedBinaryTree.Node node2 = new ThreadedBinaryTree.Node(3, "Jack");
        ThreadedBinaryTree.Node node3 = new ThreadedBinaryTree.Node(6, "Smith");
        ThreadedBinaryTree.Node node4 = new ThreadedBinaryTree.Node(8, "Marry");
        ThreadedBinaryTree.Node node5 = new ThreadedBinaryTree.Node(10, "Kidd");
        ThreadedBinaryTree.Node node6 = new ThreadedBinaryTree.Node(14, "Tim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        binaryTree.setRoot(root);
        //线索化
        binaryTree.threadedNode();

        //校验：10的前驱是否为3，后继是否为1
        ThreadedBinaryTree.Node lNode = node5.getLeft();
        ThreadedBinaryTree.Node rNode = node5.getRight();
        System.out.println("10号节点的前驱节点: " + lNode);
        System.out.println("10号节点的后继节点: " + rNode);

        //不可用之前的中序遍历，否则会出现死循环
        System.out.println("用线索化的方式遍历线索二叉树");
        binaryTree.threadedList();//结果与中序遍历一致
    }
}
