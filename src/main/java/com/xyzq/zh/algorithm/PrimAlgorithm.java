package com.xyzq.zh.algorithm;

import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weigth = new int[][]{
                {-1, 5, 7, -1, -1, -1, 2},
                {5, -1, -1, 9, -1, -1, 3},
                {7, -1, -1, -1, 8, -1, -1},
                {-1, 9, -1, -1, -1, 4, -1},
                {-1, -1, 8, -1, -1, 5, 4},
                {-1, -1, -1, 4, 5, -1, 6},
                {2, 3, -1, -1, 4, 6, -1}};
        MinSpanningTree mst = new MinSpanningTree();
        //生成图的邻接矩阵
        MGraph graph = mst.createGraph(data, weigth);

        //显示图的邻接矩阵
        mst.showGraph(graph);

        //prim算法输出最小路径
        mst.prim(graph);
    }
}

/**
 * 最小生成树
 */
class MinSpanningTree {

    /**
     * 创建图的邻接矩阵
     *
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     * @return
     */
    public MGraph createGraph(char[] data, int[][] weight) {
        int verxs = data.length;
        MGraph graph = new MGraph(verxs);
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
        return graph;
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight)
            System.out.println(Arrays.toString(link));
    }

    /**
     * 重载prim算法
     *
     * @param graph
     */
    public void prim(MGraph graph) {
        prim(graph, 0, getGraphWeightLimit(graph.weight));
    }

    /**
     * prim算法
     *
     * @param graph
     * @param start       开始遍历的节点下标
     * @param weightLimit 边的权值上限
     */
    public void prim(MGraph graph, int start, int weightLimit) {
        int[] visited = new int[graph.verxs];//访问过的节点下标
        //把当前节点标记为已访问
        visited[start] = 1;
        int h1 = -1, h2 = -1;//遍历时用来指向相邻边的两个顶点
        for (int k = 1; k < graph.verxs; k++) {
            int minWeight = weightLimit;//初始的最小边界的长度，在后续的遍历中会被替换掉

            //遍历获取相邻最近的两个节点
            for (int i = 0; i < graph.verxs; i++) {//i表示已访问过的节点
                for (int j = 0; j < graph.verxs; j++) {//j表示尚未访问节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] > 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];//替换minWeight
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到距离最近的一条边
            System.out.println(MessageFormat.format("边<{0}, {1}> 权值: {2}", graph.data[h1], graph.data[h2], minWeight));
            visited[h2] = 1;//将当前节点标记为已访问
        }
    }

    /**
     * 获取最长的那个边的权值
     *
     * @param weight
     * @return
     */
    private int getGraphWeightLimit(int[][] weight) {
        int max = 0;
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[0].length; j++) {
                if (weight[i][j] > 0 && weight[i][j] > max)
                    max = weight[i][j];
            }
        }

        return max;
    }
}

@NoArgsConstructor
class MGraph {
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，即邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}
