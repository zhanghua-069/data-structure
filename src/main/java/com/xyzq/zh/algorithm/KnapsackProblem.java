package com.xyzq.zh.algorithm;

/**
 * 动态规划--背包问题
 * 在容量为j的背包中放入物品，使得放入物品的价值最大（01背包：每样物品只能放一次）
 * 解题思路：
 * 每次遍历到第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入背包中
 * （w[i]：物品i的重量，v[i]：物品i的价值，C：背包的容量）。
 * 再令v[i][j]表示前i个物品能够装入容量为j的背包中的最大价值。
 * 可以得到如下的结果：
 * 1）v[i][0]=v[0][j]=0；
 * 表示填入表的第一行（物品价值为0）和第一列（物品重量为0）为0
 * 2）当w[i]>j时：v[i][j] = v[i-1][j]；
 * 当准备加入新增的商品的容量（w[i]）大于当前背包的容量（j）时，就直接使用上一个单元格的装入策略
 * 3）当j>=w[i]时：v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}；
 * 当准备加入的新增商品的容量小于等于当前背吧的容量，装入的方式：
 * v[i-1][j]：上一个单元格装入的最大值
 * v[i]：当前商品的价值
 * v[i-1][j-w[i]]：装入i-1个商品，到剩余空间j-w[i]的最大值
 */
public class KnapsackProblem {

    public static void main(String[] args) {

        int[] w = {1, 4, 3};//物品的重量
        int val[] = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //v[i][j]：表示前i的物品中能够装入容量为j的背包的最大价值
        int v[][] = new int[n + 1][m + 1];

        //初始化第一行和第一列
        for (int i = 0; i < v[0].length; i++)
            v[0][i] = 0;
        for (int i = 0; i < v.length; i++)
            v[i][0] = 0;

        //根据前面的公式，动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }

        //输出背包中物品放入情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

    }
}
