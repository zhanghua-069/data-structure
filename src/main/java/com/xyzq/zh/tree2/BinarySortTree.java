package com.xyzq.zh.tree2;

/**
 * 二叉排序树
 */
public class BinarySortTree {

    private Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 删除节点：
     * 【情况1】删除叶子节点
     * 1.找到需要删除的节点：targetNode
     * 2.找到待删除节点的父节点parentNode
     * 3.确定targetNode是左子节点还是右子节点
     * 4.左子节点：parentNode.left=null，右子节点：parentNode.right=null
     * <p>
     * 【情况2】删除只有一棵子树的节点
     * 1.找到需要删除的节点：targetNode
     * 2.找到待删除节点的父节点parentNode
     * 3.确定targetNode的子树是左子节点还是右子节点
     * 4.确定targetNode是parentNode的左子节点还是右子节点
     * 5.有4种情况：
     * 1）target是parent的左子节点
     * target的子节点是左子节点：parent.left = target.left
     * target的子节点是右子节点：parent.left = target.right
     * 2）target是parent的右子节点
     * target的子节点是左子节点：parent.right = target.left
     * target的子节点是右子节点：parent.right = target.right
     * <p>
     * 【情况3】删除节点有两棵子树
     * 1.找到需要删除的节点：targetNode
     * 2.找到待删除节点的父节点parentNode
     * 3.从targetNode的右子树找到最小的节点（或者找到左子树的最大节点）
     * 4.使用一个临时变量temp保存右子树最小节点（左子树最大节点）的值
     * 5.删除最小右子树节点（左子树最大节点）
     * 6.targetNode.value=temp
     *
     * @param value
     */
    public void delNode(int value) {
        if (root == null)
            return;

        //查找要删除的节点
        Node target = search(value);
        if (target == null)
            return;

        //特殊情况：要删除的节点为root（只有root一个节点，且找到了要删除的节点target!=null）
        if (root.left == null && root.right == null) {
            //删除root

        }

        //查找要删除节点的父节点
        Node parent = searchParent(value);
        //说明：parent不可能为空，为空只有仅root一个节点的情况，以上已进行处理
        if (target.left == null && target.right == null) {
            //要删除的节点是叶子节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }

        } else if (target.left != null && target.right != null) {
            //要删除的节点有两棵子树
            int temp = delRightTreeMin(target.right);
            target.value = temp;
        } else {
            //要删除的节点只有一棵子树（需要考虑删除节点的父节点是否为空的情况）
            if (target.left != null) {
                //要删除的节点只有左子树
                if (parent == null) {
                    root = target.left;
                } else {
                    if (parent.left.value == value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                }
            } else {
                //要删除的节点只有右子树
                if (parent == null) {
                    root = target.right;
                } else {
                    if (parent.left.value == value) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                }
            }
        }
    }

    /**
     * 查找要删除的节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null)
            return null;

        return root.search(value);
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null)
            return null;

        return root.searchParent(value);
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
     * 从要删除的节点的右子树开始找到最小的那个节点并删除，并将该节点的值返回
     *
     * @param node
     * @return
     */
    private int delRightTreeMin(Node node) {
        Node temp = node;
        while (temp.left != null)
            temp = temp.left;

        delNode(temp.value);
        return temp.value;
    }

    static class Node {

        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 查找要删除的节点
         *
         * @param value
         * @return
         */
        public Node search(int value) {

            if (value == this.value)
                return this;

            if (value < this.value) {
                if (this.left == null)
                    return null;

                //向左子树递归
                return this.left.search(value);
            } else {
                if (this.right == null)
                    return null;

                //向右子树递归
                return this.right.search(value);
            }
        }

        /**
         * 查找要删除节点的父节点
         *
         * @param value
         * @return
         */
        public Node searchParent(int value) {
            //当前节点是要删除节点的父节点
            if ((this.left != null && this.left.value == value)
                    || (this.right != null && this.right.value == value))
                return this;

            if (this.left != null && value < this.value) {
                //向左子树递归
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                //这里等于的情况说明：this的right节点的值也可能等于value，此时要删除节点的parent就是this或其右子节点（有继续往右递归的可能）
                return this.right.searchParent(value);
            } else {
                //没有找到
                return null;
            }
        }

        /**
         * 使用递归的方式添加节点，需要满足二叉排序树的要求
         *
         * @param node
         */
        public void add(Node node) {

            if (node == null)
                return;

            if (this.value > node.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
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

        @Override
        public String toString() {
            return "Node{" + "value=" + value + '}';
        }
    }
}
