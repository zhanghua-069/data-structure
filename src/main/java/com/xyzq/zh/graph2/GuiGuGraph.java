package com.xyzq.zh.graph2;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
public class GuiGuGraph {

    /**
     * 顶点集合
     */
    private List<String> vertexList;
    /**
     * 图的邻接矩阵
     */
    private int[][] edges;
    /**
     * 边的个数
     */
    private int edgeNum;
    /**
     * 节点是否访问过
     */
//    private boolean[] isVisited;

    /**
     * 构造器
     *
     * @param vertexNum 顶点的个数
     */
    public GuiGuGraph(int vertexNum) {
        //初始化vertexList和edges
        vertexList = new ArrayList<>(vertexNum);
        edges = new int[vertexNum][vertexNum];
//        isVisited = new boolean[vertexNum];
    }

    /**
     * 广度优先搜索，遍历所有节点
     *
     * @return
     */
    public List<String> bfs() {
        int num = getVertexNum();
        boolean[] isVisited = new boolean[num];
        List<String> bfsList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            if (!isVisited[i]) {
                //节点未访问过，进行bfs遍历
                bfs(isVisited, i, bfsList);
            }
        }
        return bfsList;
    }

    /**
     * 重载dfs方法，对所有顶点进行dfs遍历
     *
     * @return
     */
    public List<String> dfs() {
        int num = getVertexNum();
        boolean[] isVisited = new boolean[num];
        List<String> dfsList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            if (!isVisited[i]) {
                //节点未访问过，进行dfs遍历
                dfs(isVisited, i, dfsList);
            }
        }
        return dfsList;
    }

    /**
     * 获取对应下标顶点的第一个邻接顶点下标（找不到返回-1）
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0)
                return i;
        }

        return -1;
    }

    /**
     * 根据前一个邻接节点下标获取下一个邻接节点
     *
     * @param pre   前一个邻接节点下标
     * @param index 当前节点下标
     * @return
     */
    public int getNextNeighbor(int pre, int index) {

        for (int i = index + 1; i < vertexList.size(); i++) {
            if (edges[pre][i] > 0)
                return i;
        }

        return -1;
    }

    /**
     * 加入顶点
     *
     * @param vertex
     */
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 加入边
     *
     * @param v1     顶点1下标
     * @param v2     顶点2下标
     * @param weight 权值
     */
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNum++;
    }

    /**
     * 常用方法1：返回顶点个数
     *
     * @return
     */
    public int getVertexNum() {
        return CollectionUtils.isEmpty(vertexList) ? 0 : vertexList.size();
    }

    /**
     * 常用方法2：返回边的个数
     *
     * @return
     */
    public int getEdgeNum() {
        return edgeNum;
    }

    /**
     * 常用方法3：根据下标返回顶点值
     *
     * @return
     * @Param index
     */
    public String getVertexByIndex(int index) {
        return CollectionUtils.isEmpty(vertexList) || index >= vertexList.size() ? null : vertexList.get(index);
    }

    /**
     * 常用方法4：获取顶点v1，v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 常用方法5：显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] link : edges)
            System.out.println(Arrays.toString(link));
    }

    /**
     * 深度优先遍历（Deep First Search）
     * 1）访问初始节点v，并标记节点v已访问
     * 2）查找节点v的第一个邻接节点w
     * 3）若w存在，执行4；若w不存在，回到第一步，从v的下一个邻接节点继续
     * 4）若w未被访问，对w进行深度优先遍历递归（即把w当做v，进行步骤123）
     * 5）若w已被访问，查找(v,w)的下一个邻接节点，转到步骤3
     *
     * @param isVisited 节点是否被访问过
     * @param v         遍历开始下标
     * @param dfsList   接收遍历结果的list
     */
    private void dfs(boolean[] isVisited, int v, List<String> dfsList) {
        //先遍历当前节点
        dfsList.add(getVertexByIndex(v));
        isVisited[v] = true;//标记节点已访问
        //找到v的第一个邻接节点w
        int w = getFirstNeighbor(v);
        while (w != -1) {//w存在
            if (!isVisited[w]) {
                //w未被访问，对w进行dfs递归
                dfs(isVisited, w, dfsList);
            }
            //w访问过，找到(v,w)的下一个节点
            w = getNextNeighbor(v, w);
        }
        //w不存在，跳出while循环，当前节点dfs遍历结束
    }

    /**
     * 单个节点的广度优先遍历（Broad First Search）
     * 1) 访问初始节点v，并标记节点v已访问
     * 2) 节点v入队列
     * 3）当队列非空时，继续执行，否则算法结束
     * 4）（队列非空）出队列，取得队头节点u
     * 5）查找u的第一个邻接节点w
     * 6）w不存在，则转到步骤3
     * 7）w存在，执行如下步骤：
     * 7-1）w未被访问，访问w并标记为已访问，w入队列
     * 7-2）w已被访问，查找(u,w)的下一个邻接节点w，转到步骤7
     *
     * @param isVisited
     * @param v
     * @param bfsList
     */
    private void bfs(boolean[] isVisited, int v, List<String> bfsList) {

        LinkedList<Integer> queue = new LinkedList<>();//队列，记录节点访问的顺序
        //访问节点v，并标记v已访问
        bfsList.add(getVertexByIndex(v));
        isVisited[v] = true;
        //节点v入队列
        queue.addLast(v);
        while (!queue.isEmpty()) {//步骤3：队列非空
            int u = queue.removeFirst();//获取队列头节点u
            int w = getFirstNeighbor(u);//查找u的第一个邻接节点w
            while (w != -1) {//步骤7：w存在
                if (!isVisited[w]) {
                    //w未被访问
                    bfsList.add(getVertexByIndex(w));//访问w
                    isVisited[w] = true;//标记w已访问
                    queue.addLast(w);//w入队列
                }
                //w已被访问，查找(u,w)的下一个邻接节点w
                w = getNextNeighbor(u, w);//这里体现出广度优先
            }
            //w不存在，转到步骤3
        }
        //队列为空，算法结束
    }

}
