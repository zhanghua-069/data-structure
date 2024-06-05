package com.xyzq.zh.algorithm;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘（骑士周游问题）
 */
public class HorseChessboard {

    private static int X;//棋盘的行数
    private static int Y;//棋盘的列数
    /**
     * 标记棋盘各个位置是否被访问过
     */
    private static boolean[] visited;
    private static boolean finished; // 如果为true,表示成功

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        X = Y = 8;
        int[][] chessboard = new int[X][Y];
        //测试耗时
        Instant start = Instant.now();
        startTraversal(chessboard, 1, 1);
        System.out.println("耗时：" + Duration.between(start, Instant.now()).toMillis());

        //输出棋盘最后的情况
        for (int rows[] : chessboard) {
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     * 开始跑路
     *
     * @param chessboard
     * @param startX     行的起始位置，从1开始
     * @param startY     列的起始位置，从1开始
     */
    public static void startTraversal(int[][] chessboard, int startX, int startY) {
        visited = new boolean[X * Y];//初始值都是false
        traversalChessboard(chessboard, startX - 1, startY - 1, 1);
    }

    /**
     * 骑士周游问题算法
     *
     * @param chessboard
     * @param row        马儿当前位置的行，从0开始
     * @param col        马儿当前位置的列，从0开始
     * @param step       当前走到第几步（从1开始）
     */
    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;//记录棋盘当前位置是哪一步
        visited[row * Y + col] = true;//标记改位置已被访问
        //获取当前位置之后可以走到的位置的集合
        List<Point> nextPoints = next(new Point(col, row));
        sort(nextPoints);//对下一步位置集合进行排序
        while (!nextPoints.isEmpty()) {
            Point point = nextPoints.remove(0);//取出第一个可以走的位置
            if (!visited[point.y * Y + point.x]) {
                //这个位置还未走过
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }

        /**
         * 重点说明：这里必须要有finished判断，否则递归调用一直不会有出口
         * 因为缺少finished判断后，当step=64时，方法会跳出递归上层，此时上层方法中的step=63，如果没有finished标识，则该层方法又会进到回溯的代码段中，
         * 这时候就会存在这样的问题，step=63时的这个点明明是正确的，但是被错误的回溯了，外层其他的step(62,61,59...)就还会以为这个点没有遍历过，会再次走到这个点，
         * 周而复始，这样代码就永远没有出口的地方
         * finished的作用就是当他是false时，表示当前正在进行回溯操作，一旦当finished=true时，就表示棋盘已经遍历完成，可以跳出了
         */
        if (step < X * Y && !finished) {
            //说明棋盘还未走完，这个点走不通，将当前位置回溯
            chessboard[row][col] = 0;
            visited[row * Y + col] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 获取马儿在当前位置下还能走哪些位置
     *
     * @param curPoint
     * @return
     */
    public static List<Point> next(Point curPoint) {
        List<Point> list = new ArrayList<>();
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            list.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            list.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            list.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            list.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            list.add(new Point(p1));
        }
        return list;
    }

    /**
     * 使用贪心算法，将下一步位置的列表进行非递减排序，减少回溯的次数
     *
     * @param list
     */
    private static void sort(List<Point> list) {
        list.sort(Comparator.comparing(point -> next(point).size()));
    }

}
