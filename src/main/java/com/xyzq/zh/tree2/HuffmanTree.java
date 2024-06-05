package com.xyzq.zh.tree2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 赫夫曼树：路径加权之和（wlp）最小的树
 * 作用：压缩/解压文件
 */
public class HuffmanTree {

    /**
     * 前序遍历
     */
    public static void preOrder(Node root) {

        if (root == null) {
            System.out.println("空树不可遍历");
        } else {
            root.preOrder();
        }
    }

    /**
     * 创建一棵赫夫曼树
     *
     * @param arr
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = Arrays.stream(arr).mapToObj(val -> new Node(val)).sorted().collect(Collectors.toList());
        while (nodes.size() > 1) {
            //操作步骤：1.从小到大排序 2.开头两个节点相加组成一个parentNode 3.删除开头两个节点并将parentNode加入队列
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        //最终返回这课赫夫曼树的root节点
        return nodes.get(0);
    }

    /**
     * 赫夫曼树的节点需要支持排序
     */
    static class Node implements Comparable<Node> {
        int value;//权值
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public int compareTo(Node o) {
            return this.value - o.value;//升序排序
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            //1.先输出父节点
            System.out.println(this);

            //2.递归左子树前序遍历
            if (this.left != null)
                this.left.preOrder();

            //3.递归右子树前序遍历
            if (this.right != null)
                this.right.preOrder();
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + '}';
        }
    }
}
