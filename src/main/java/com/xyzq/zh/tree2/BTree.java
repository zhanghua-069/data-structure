package com.xyzq.zh.tree2;

/**
 * B树（B-tree，B-树）：多叉树（多路查找树），降低层高，减少IO
 * B树的阶：节点的最多子节点的个数。如2-3树的阶是3，,2-3-4树的阶是4
 * B树的搜索
 * 1.从根节点开始对节点内的关键字进行二分查找，找到则结束，否则进入关键字子节点查找，直至其子节点为空（叶子节点）
 * 2.关键字集合分布在整棵树中，即叶子节点和非叶子节点都存放数据
 * 3.搜索可能在非叶子节点结束
 * 4.搜索性能等价于在关键字全集中做一次二分查找
 *
 */
public class BTree {

    /**
     * 2-3树：最简单的B树
     * 条件
     * 1.所有叶子节点都在同一层（只要是B树都满足这个条件），所有节点都是二节点或三节点
     * 2.二节点：没有子节点或者有两个子节点
     * 3.三节点：没有子节点或者有三个子节点
     * 4.按照规则插入某个节点时，不能满足上面的三个要求时就需要拆，先向上拆，如果上层满，再拆本层，拆后仍需满足上面三个条件
     * 5.二三节点的子树值的大小都遵守（BST二叉排序树）的规则
     */

    /**
     * B树（B-tree，B-树）：多叉树（多路查找树），降低层高，减少IO
     * B树的阶：节点的最多子节点的个数。如2-3树的阶是3，,2-3-4树的阶是4
     * B树的搜索
     * 1.从根节点开始对节点内的关键字进行二分查找，找到则结束，否则进入关键字子节点查找，直至其子节点为空（叶子节点）
     * 2.关键字集合分布在整棵树中，即叶子节点和非叶子节点都存放数据
     * 3.搜索可能在非叶子节点结束
     * 4.搜索性能等价于在关键字全集中做一次二分查找
     */

    /**
     * B+树：B树的一种变体（结构与B树基本相同），也是一种多路查找树
     * 1.区别：数据都存放在叶子节点（查找只有达到叶子节点才会命中），其性能也等价于在关键字集合中做一次二分查找
     * 2.【稠密索引】：指叶子节点（叶子节点是存储数据的数据层），数据以链表的结构挂在叶子节点上（即数据都在叶子节点），且数据在链表中是有序的
     * 3.【稀疏索引】：指非叶子节点（真正意义上的索引），非叶子节点相当于叶子节点的索引
     * 4.B+树更适合文件索引系统
     */

    /**
     * B*树：B+树的变体，在B+树的非根和非叶子节点再增加指向兄弟的指针
     * 1.B*树分配新节点的概率比B+树要低，空间使用率更高
     */
}
