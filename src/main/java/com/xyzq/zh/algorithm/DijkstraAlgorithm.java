package com.xyzq.zh.algorithm;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DijkstraAlgorithm {

    public static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };

        Graph graph = new Graph(vertex, matrix);
//        graph.showGraph();
        graph.dsj(6);
        graph.showVv(vertex);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    static class Graph {

        private char[] vertex;//顶点数组
        private int[][] matrix;//邻接矩阵
        private VisitedVertex vv;

        public Graph(char[] vertex, int[][] matrix) {
            this.vertex = vertex;
            this.matrix = matrix;
        }

        /**
         * 显示图
         */
        public void showGraph() {
            for (int[] link : matrix)
                System.out.println(Arrays.toString(link));
        }

        /**
         * 查看最后的访问情况
         *
         * @param vertex
         */
        public void showVv(char[] vertex) {
            vv.show(vertex);
        }

        /**
         * 迪杰斯特拉算法
         *
         * @param start 出发顶点对应的下标
         */
        public void dsj(int start) {
            vv = new VisitedVertex(vertex.length, start);
            update(start);//更新start到其他周围顶点的距离和周围顶点的前驱节点
            for (int i = 1; i < vertex.length; i++) {
                //获取下一个访问顶点
                int next = vv.getNextVisit();
                update(next);//更新start到其他周围顶点的距离和周围顶点的前驱节点
            }
        }

        /**
         * 更新index顶点到其他顶点的距离和到其他顶点的前驱节点
         *
         * @param index
         */
        public void update(int index) {
            int tmpDis = 0;
            //遍历index顶点所在的matrix所在行
            for (int j = 0; j < matrix[index].length; j++) {
                //出发顶点到index顶点的距离+index顶点到目标顶点的距离
                tmpDis = vv.getDistance(index) + matrix[index][j];
                if (!vv.isVisited(j) && tmpDis < vv.getDistance(j)) {
                    //如果顶点j未访问过，且经过index顶点到顶点j的距离 小于 出发顶点到顶点j的距离，
                    //更新出发顶点到顶点j的距离和顶点j的前驱节点
                    vv.updateDistance(j, tmpDis);
                    vv.updatePre(j, index);
                }
            }
        }

    }

    /**
     * 已访问顶点的集合
     */
    static class VisitedVertex {
        //已访问过的节点，访问过为1，未访问过为0
        private int[] alreadyVisited;
        //各顶点的前一个顶点的下标
        private int[] preVisited;
        //出发顶点到各个顶点的最短距离
        private int[] distance;
        //出发顶点
        private int start;

        /**
         * @param num   顶点的个数
         * @param start 出发顶点的下标
         */
        public VisitedVertex(int num, int start) {
            alreadyVisited = new int[num];
            preVisited = new int[num];
            distance = new int[num];
            this.start = start;
            //初始化各顶点间距离
            Arrays.fill(distance, N);
            distance[start] = 0;
            //设置出发顶点被访问过
            alreadyVisited[start] = 1;
        }

        /**
         * 下标对应的顶点是否被访问过
         *
         * @param index
         * @return
         */
        public boolean isVisited(int index) {
            return alreadyVisited[index] == 1;
        }

        /**
         * 更新index顶点到出发顶点的距离
         *
         * @param index
         * @param dis
         */
        public void updateDistance(int index, int dis) {
            distance[index] = dis;
        }

        /**
         * 更新index的前一个顶点
         *
         * @param index
         * @param pre
         */
        public void updatePre(int index, int pre) {
            preVisited[index] = pre;
        }

        /**
         * 获取出发顶点到index顶点的距离
         *
         * @param index
         * @return
         */
        public int getDistance(int index) {
            return distance[index];
        }

        /**
         * 获取下一个访问顶点（找到距离出发顶点路径最短且未访问过的那个顶点）
         *
         * @return
         */
        public int getNextVisit() {
            int min = N, index = 0;
            for (int i = 0; i < alreadyVisited.length; i++) {
                //这里体现了按图的广度优先遍历（出发顶点不是i还是一开始的start）
                if (!isVisited(i) && getDistance(i) < min) {
                    //顶点i未访问过，且出发顶点到顶点i的距离最小
                    min = getDistance(i);
                    index = i;
                }
            }
            //标记index被访问过
            alreadyVisited[index] = 1;
            return index;
        }

        /**
         * 查看已访问顶点的情况
         *
         * @param vertex
         */
        public void show(char[] vertex) {
            System.out.println("已访问顶点: " + Arrays.toString(alreadyVisited));
            System.out.println("各目标顶点的前驱节点: " + Arrays.toString(preVisited));
            System.out.println("出发顶点到目标顶点的距离: " + Arrays.toString(distance));
            //具体展示出发顶点到周围节点的距离
            String result = IntStream.range(0, distance.length)
                    .mapToObj(i -> MessageFormat.format("({0},{1}){2}", vertex[start], vertex[i], distance[i]))
                    .collect(Collectors.joining(", "));
            System.out.println("出发顶点到目标顶点的距离详情：" + result);
        }
    }
}
