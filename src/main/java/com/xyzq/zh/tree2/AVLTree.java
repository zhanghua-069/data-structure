package com.xyzq.zh.tree2;

import lombok.Data;

@Data
public class AVLTree {

    private Node root;

    /**
     * 获取树的高度
     *
     * @return
     */
    public int height() {
        return root == null ? 0 : root.height();
    }

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
         * 返回当前节点为根节点的树的高度
         *
         * @return
         */
        public int height() {
            //+1：节点本身高度
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }

        /**
         * 返回节点左子树的高度
         *
         * @return
         */
        public int leftHeight() {
            return left == null ? 0 : left.height();
        }

        /**
         * 返回节点右子树的高度
         *
         * @return
         */
        public int rightHeight() {
            return right == null ? 0 : right.height();
        }

        /**
         * 左旋
         * 1.创建一个新节点newNode，值等于当前根节点的值
         * 2.把新节点的左子树设置为当前节点的左子树：newNode.left=left
         * 3.把新节点的右子树设置为当前节点的右子树的左子树：newNode.right = right.left
         * 4.把当前节点的值设为其右子节点的值：value=right.value
         * 5.把当前节点的右子树设置成右子树的右子树：right = right.right
         * 6.把当前节点的左子树设置成新节点：left=newNode
         */
        public void leftRotate() {
            //1.创建一个新节点newNode，值等于当前根节点的值
            Node newNode = new Node(this.value);
            //2.把新节点的左子树设置为当前节点的左子树
            newNode.left = left;
            //3.把新节点的右子树设置为当前节点的右子树的左子树
            newNode.right = right.left;
            //4.把当前节点的值设为其右子节点的值
            value = right.value;
            //5.把当前节点的右子树设置成右子树的右子树
            right = right.right;
            //6.把当前节点的左子树设置成新节点
            left = newNode;
        }

        /**
         * 右旋
         * 1.创建一个新节点newNode，值等于当前根节点的值
         * 2.把新节点的右子树设置为当前节点的右子树：newNode.right=right
         * 3.把新节点的左子树设置为当前节点的左子树的右子树：newNode.left = left.right
         * 4.把当前节点的值设为其左子节点的值：value=left.value
         * 5.把当前节点的左子树设置成左子树的左子树：left = left.left
         * 6.把当前节点的右子树设置成新节点：right=newNode
         */
        public void rightRotate() {
            //1.创建一个新节点newNode，值等于当前根节点的值
            Node newNode = new Node(this.value);
            //2.把新节点的右子树设置为当前节点的右子树
            newNode.right = right;
            //3.把新节点的左子树设置为当前节点的左子树的右子树
            newNode.left = left.right;
            //4.把当前节点的值设为其左子节点的值
            value = left.value;
            //5.把当前节点的左子树设置成左子树的左子树
            left = left.left;
            //6.把当前节点的右子树设置成新节点
            right = newNode;
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

            //添加一个新节点后，如果（右子树的高度-左子树的高度）> 1，左旋转
            if (rightHeight() - leftHeight() > 1) {
                //节点的右节点 的左子树高于右子树：先对节点的右节点进行右旋，再对节点进行左旋
                if (right != null && (right.leftHeight() > right.rightHeight())) {
                    right.rightRotate();
                }
                leftRotate();
            } else if (leftHeight() - rightHeight() > 1) {
                //添加一个新节点后，如果（左子树的高度-右子树的高度）> 1，右旋转
                /**
                 * 双旋情况处理
                 * 节点的左节点 的右子树高于左子树：先对节点的左节点进行左旋，再对节点进行右旋
                 */
                if (this.left != null && (this.left.rightHeight() > this.left.leftHeight())) {
                    this.left.leftRotate();
                }
                rightRotate();
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
