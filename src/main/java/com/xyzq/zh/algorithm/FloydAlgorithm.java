package com.xyzq.zh.algorithm;

import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Arrays;

public class FloydAlgorithm {

    static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }

    @NoArgsConstructor
    static class Graph {

        private char[] vertex;//存放顶点的数组
        private int[][] dis;//保存从各个顶点出发到其他顶点的距离
        private int[][] pre;//保存到达目标顶点的前驱顶点

        public Graph(int size, int[][] matrix, char[] vertex) {
            this.vertex = vertex;
            this.dis = matrix;
            this.pre = new int[size][size];
            //初始化pre数组，存放的是前驱节点的下标
            for (int i = 0; i < size; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        /**
         * 弗洛伊德算法
         */
        public void floyd() {
            int distance = 0;//保存距离变量
            for (int k = 0; k < dis.length; k++) {
                //对中间节点进行遍历
                for (int i = 0; i < dis.length; i++) {
                    //从顶点i开始，依次去往目标顶点j
                    for (int j = 0; j < dis.length; j++) {
                        //从顶点i出发，经过中间顶点k，最后到达j
                        distance = dis[i][k] + dis[k][j];
                        if (distance < dis[i][j]) {
                            //经过中间节点的距离小于i到j的距离
                            dis[i][j] = distance;//更新距离
                            //更新前驱节点（经过中间节点k，因此pre[i][j]的前驱节点就是pre[k][j]）
                            pre[i][j] = pre[k][j];
                        }
                    }
                }
            }
        }

        /**
         * 显示pre数组和dis数组
         */
        public void show() {

            for (int i = 0; i < dis.length; i++) {
                //先将pre数组输出一行
                for (int j = 0; j < dis.length; j++) {
                    System.out.print(vertex[pre[i][j]] + " ");
                }
                //换行
                System.out.println();
                for (int j = 0; j < dis.length; j++) {
                    String d = MessageFormat.format("({0}->{1}: {2})", vertex[i], vertex[j], dis[i][j]);
                    System.out.print(d + " ");
                }
                System.out.println();
                System.out.println();
            }
        }
    }
}
