package com.xyzq.zh.tree2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 线索化二叉树
 * 1.顺序存储二叉树一般为完全二叉树
 * 2.left指向的是左子树，也可能指向前驱节点
 * 3.right指向的是右子树，也可能指向后继节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadedBinaryTree {

    private Node root;

    //为了线索化，需要一个获取前驱节点的指针（线索化时用来保存前一个节点）
    //默认为空（第一个节点）
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    //重载线索化
    public void threadedNode() {
        this.threadedNode(root);
    }

    /**
     * 使用中序遍历，对目标节点进行线索化
     * <p>
     * 1.先线索化左子树
     * 2.线索化当前节点（当前节点处理left节点和上一个节点的right节点：只存储了pre，因此当前节点只能处理left（left指向pre））
     * 3.线索化右子树
     *
     * @param node
     */
    public void threadedNode(Node node) {

        if (node == null)
            return;

        //线索化左子树
        threadedNode(node.left);

        //线索化当前节点
        //处理前驱节点（当前节点的左子节点为空）
        if (node.left == null) {
            //当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //并修改左指针的类型（指向前驱节点）
            node.setLeftType(1);
        }

        //因为是单向的，一个节点不能同时处理左右
        //因此，当前节点处理上一个节点的right，而当前节点的right交给下一个节点处理（通过递归）
        if (pre != null && pre.right == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点成为下一个节点的前驱节点
        pre = node;

        //线索化右子树
        threadedNode(node.right);

    }

    /**
     * 遍历线索二叉树
     */
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        Node node = root;
        while (node != null) {
            //循环找到leftType=1的节点（说明该节点是线索化后的有效节点而非子树）
            while (node.leftType == 0) {
                node = node.left;
            }
            //打印该节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            //跳出循环后，替换这个遍历的节点
            node = node.right;
        }
    }

    @Data
    static class Node {

        private int no;
        private String name;
        private Node left;
        private Node right;

        //0：指向左子树，1：指向前驱节点
        private int leftType;
        private int rightType;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            String s = "Node {no=" + no + ", name=" + name + "}";
            return s;
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

        /**
         * 中序遍历
         */
        public void infixOrder() {
            //1.递归左子树中序遍历
            if (this.left != null)
                this.left.infixOrder();

            //2.输出当前节点
            System.out.println(this);

            //3.递归右子树中序遍历
            if (this.right != null)
                this.right.infixOrder();
        }

        /**
         * 后序遍历
         */
        public void postOrder() {
            //1.递归左子树后序遍历
            if (this.left != null)
                this.left.postOrder();

            //2.递归右子树后序遍历
            if (this.right != null)
                this.right.postOrder();

            //3.输出当前节点
            System.out.println(this);
        }

        /**
         * 中序遍历查找
         *
         * @return
         */
        public Node preOrderSearch(int no) {

            if (this.no == no)
                return this;

            Node res = null;
            if (this.left != null)
                res = this.left.preOrderSearch(no);

            if (res != null)
                return res;

            if (this.right != null)
                res = this.right.preOrderSearch(no);

            return res;
        }

        /**
         * 前序遍历查找
         *
         * @return
         */
        public Node infixOrderSearch(int no) {

            Node res = null;
            if (this.left != null)
                res = this.left.infixOrderSearch(no);

            if (res != null)
                return res;

            if (this.no == no)
                return this;

            if (this.right != null)
                res = this.right.infixOrderSearch(no);

            return res;
        }

        /**
         * 后序遍历查找
         *
         * @return
         */
        public Node postOrderSearch(int no) {

            Node res = null;
            if (this.left != null)
                res = this.left.postOrderSearch(no);

            if (res != null)
                return res;

            if (this.right != null)
                res = this.right.postOrderSearch(no);

            if (res != null)
                return res;

            if (this.no == no)
                return this;

            return null;
        }

        /**
         * 删除节点
         * 1.节点为叶子节点，则删除该节点
         * 2.节点非叶子节点，则删除删除该子树
         *
         * @param no
         */
        public void delNode(int no) {

            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
            }

            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
            }

            //递归左子树进行删除
            if (this.left != null)
                this.left.delNode(no);

            //递归右子树进行删除
            if (this.right != null)
                this.right.delNode(no);

        }

    }
}
