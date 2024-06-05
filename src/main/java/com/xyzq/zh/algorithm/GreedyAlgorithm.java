package com.xyzq.zh.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建所有电台
        Map<String, List<String>> broadCasts = new HashMap<>();
        broadCasts.put("K1", Arrays.asList("北京", "上海", "天津"));
        broadCasts.put("K2", Arrays.asList("广州", "北京", "深圳"));
        broadCasts.put("K3", Arrays.asList("成都", "上海", "深圳"));
        broadCasts.put("K4", Arrays.asList("上海", "天津"));
        broadCasts.put("K5", Arrays.asList("杭州", "大连"));

        //存放所有地区
        List<String> allAreas = broadCasts.values().stream().flatMap(val -> val.stream()).collect(Collectors.toList());
//        System.out.println(allAreas);

        //存放选中的电台的集合
        List<String> selects = new ArrayList<>();
        //定义maxKey，保存在一次遍历中，能够获得最大未覆盖地区对应的电台key，若maxKey!=null，则加入到selects中
        String maxKey = null;
        while (allAreas.size() > 0) {
            //每进行一次while循环，需要将maxKey置空
            maxKey = null;

            for (String broad : broadCasts.keySet()) {
                List<String> areas = broadCasts.get(broad);
                //存放遍历过程中电台覆盖的地区和当前还未覆盖地区的交集
                Set<String> tempSet = getInterSet(areas, allAreas);
                //如果当前这个集合包含的未覆盖地区的数量比maxKey指向的未覆盖地区的数量还要多，则将maxKey指向当前的broad
                //这里就体现贪心算法的特点：每次都选择最优的结果
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > getInterSet(broadCasts.get(maxKey), allAreas).size())) {
                    maxKey = broad;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的电台覆盖的地区从allAreas中去掉
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果：" + selects);
    }

    private static Set<String> getInterSet(List<String> list1, List<String> list2) {
        return list1.stream().filter(area -> list2.contains(area)).collect(Collectors.toSet());
    }
}
