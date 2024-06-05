package com.xyzq.zh.algorithm;

import java.text.MessageFormat;

/**
 * 分治算法-汉诺塔问题
 * 思路分析：
 * 1）如果只有一个盘，A -> C
 * 2）n>=2时，可以看作两个盘：1.最下面的盘，2.上面的盘
 * step1.上面的盘，A -> B
 * step2.下面的盘，A -> C
 * step3.B塔上所有的盘，B -> C
 */
public class Hanoitower {

    //测试
    public static void main(String[] args) {
//        hanoiTower(1, 'A', 'B', 'C');
        hanoiTower(5, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔移动方法（使用分治算法）
     * <p>
     * 1）如果只有一个盘，A -> C
     * 2）n>=2时，可以看作两个盘：1.最下面的盘，2.上面的盘
     * step1.上面的盘，A -> B
     * step2.下面的盘，A -> C
     * step3.B塔上所有的盘，B -> C
     *
     * @param num 移动的盘子的个数
     * @param A
     * @param B
     * @param C
     */
    public static void hanoiTower(int num, char A, char B, char C) {

        if (num == 1) {
            System.out.println(MessageFormat.format("第{0}个盘：{1} -> {2}", num, A, C));
            return;
        }
        //n>=2
        //上面的盘（num-1个），A->B
        hanoiTower(num - 1, A, C, B);
        //下面的盘（第num个），A->C
        System.out.println(MessageFormat.format("第{0}个盘：{1} -> {2}", num, A, C));
        //B塔上的盘，B->C
        hanoiTower(num - 1, B, A, C);
    }

}
