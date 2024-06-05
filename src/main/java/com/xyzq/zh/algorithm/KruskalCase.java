package com.xyzq.zh.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalCase {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        //输出构建的带权图
        kruskalCase.printMatix();
        System.out.println("图中边的个数：" + kruskalCase.getEdgeNum());

        System.out.println("边的数据：" + Arrays.toString(kruskalCase.getEdges()));
        List<EData> eDataList = kruskalCase.sortEdges(kruskalCase.getEdges());
        System.out.println("排序后的边：" + eDataList);

        //kruskal获取最小生成树并打印
        List<EData> kruskal = kruskalCase.kruskal();
        System.out.println("kruskal最小生成树：" + kruskal);
    }

    private int edgeNum;//边的个数
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两点不能连通
    public static final int INF = Integer.MAX_VALUE;

    public KruskalCase(char[] vertex, int[][] matrix) {

        int vLen = vertex.length;
        this.vertex = new char[vLen];
        for (int i = 0; i < vLen; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (matrix[i][j] > 0 && matrix[i][j] != INF)
                    edgeNum++;
            }
        }
        this.edgeNum /= 2;//统计边的个数
    }

    /**
     * 显示图的邻接矩阵
     */
    public void printMatix() {
        System.out.println("打印邻接矩阵：");
        for (int[] link : this.matrix)
            System.out.println(Arrays.toString(link));
    }

    /**
     * kruskal获取最短路径
     *
     * @return
     */
    public List<EData> kruskal() {
//        int index = 0;
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中每个顶点在最小生成树中对应的终点（下标）
        List<EData> resList = new ArrayList<>();//用于保存最后的"最小生成树"

        //获取图中的边并排序
        List<EData> eDataList = this.sortEdges(this.getEdges());

        for (EData eData : eDataList) {
            //遍历所有的边，获取边的起点与终点对应的下标
            int start = getPosition(eData.start);
            int end = getPosition(eData.end);

            //获取start和end在"已有最小生成树"中各自对应的终点
            int m = getEnd(ends, start);
            int n = getEnd(ends, end);
            if (m != n) {
                //m和n未形成回路
                ends[m] = n;//设置在最小生成树中m对应的终点为n
                //将这条边加入到最后的"最小生成树"中
                resList.add(eData);
            }
        }
        return resList;
    }

    /**
     * 获取图中的边，形式：[['A','B',12],['B','F',7],...]
     *
     * @return
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] > 0 && matrix[i][j] != INF)
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
            }
        }
        return edges;
    }

    /**
     * 获取顶点对应的下标，找不到返回-1
     *
     * @param c
     * @return
     */
    public int getPosition(char c) {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == c)
                return i;
        }
        return -1;
    }

    /**
     * 对边进行排序处理
     *
     * @param edges
     * @return
     */
    public List<EData> sortEdges(EData[] edges) {
        return Stream.of(edges).sorted(Comparator.comparing(EData::getWeight)).collect(Collectors.toList());
    }

    /**
     * 获取顶点i对应的终点下标
     *
     * @param ends 各个顶点对应的终点下标
     * @param i    传入的顶点对应的下标
     * @return 返回下标为i的顶点对应的终点下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            //这里while的意义在于，只要ends[i]不为0，即表示有连通的边，就可以一直遍历连通的边找到最后的终点下标
            i = ends[i];
        }
        return i;//找不到终点（即当前顶点还未加入最小生成树时），返回他自己的下标（后面设置ends[]时会用到该下标）
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    /**
     * 图的边
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class EData {

        //边的开始节点
        private char start;
        //边的终止节点
        private char end;
        //边的权值
        private int weight;

        @Override
        public String toString() {
            return MessageFormat.format("({0}, {1}) -> {2}", start, end, weight);
        }
    }
}
