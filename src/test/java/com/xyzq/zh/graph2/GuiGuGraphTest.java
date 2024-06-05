package com.xyzq.zh.graph2;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class GuiGuGraphTest {

    @Test
    public void testDemo() {
        int n = 5;//节点个数
        String[] vertexs = {"A", "B", "C", "D", "E"};
        //创建图对象
        GuiGuGraph graph = new GuiGuGraph(n);
        //添加节点
        for (String vertex : vertexs)
            graph.addVertex(vertex);

        //添加边：A-B，A-C，B-C，B-D，B-E
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);

        //显示邻接矩阵
        graph.showGraph();

        System.out.println("图的深度优先遍历~~");
        List<String> dfs = graph.dfs();
        System.out.println("深度优先遍历结果：" + dfs.stream().collect(Collectors.joining("->")));

        System.out.println("图的广度优先遍历~~");
        List<String> bfs = graph.bfs();
        System.out.println("广度优先遍历结果：" + bfs.stream().collect(Collectors.joining("->")));
    }
}
