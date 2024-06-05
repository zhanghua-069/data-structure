package com.xyzq.zh.tree2;

public class BinaryTree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root == null) {
            System.out.println("当前二叉树为空，不可遍历");
            return;
        }

        root.preOrder();
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("当前二叉树为空，不可遍历");
            return;
        }

        root.infixOrder();
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root == null) {
            System.out.println("当前二叉树为空，不可遍历");
            return;
        }

        root.postOrder();
    }

    /**
     * 前序遍历查找
     *
     * @param no
     * @return
     */
    public Node preOrderSearch(int no) {
        if (root == null) {
            System.out.println("当前二叉树为空");
            return null;
        }

        return root.preOrderSearch(no);
    }

    /**
     * 中序遍历查找
     *
     * @param no
     * @return
     */
    public Node infixOrderSearch(int no) {
        if (root == null) {
            System.out.println("当前二叉树为空");
            return null;
        }

        return root.infixOrderSearch(no);
    }

    /**
     * 后序遍历查找
     *
     * @param no
     * @return
     */
    public Node postOrderSearch(int no) {
        if (root == null) {
            System.out.println("当前二叉树为空");
            return null;
        }

        return root.postOrderSearch(no);
    }

    public void delNode(int no) {

        if (root == null) {
            System.out.println("这是一棵空树");
            return;
        }

        if (root.no == no) {
            System.out.println("root就是要删除的节点");
            root = null;
            return;
        }

        //开始递归删除
        root.delNode(no);
    }

    static class Node {

        private int no;
        private String name;
        private Node left;
        private Node right;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
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
