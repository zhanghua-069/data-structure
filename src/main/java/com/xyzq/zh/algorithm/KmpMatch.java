package com.xyzq.zh.algorithm;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * kmp算法
 */
public class KmpMatch {

    public static void main(String[] args) {
        String orgin = "BBC ABCDAB ABCDABCDABDE";
        String match = "ABCDABD";

        int index = violenceMatch(orgin, match);
        System.out.println("暴力匹配结果，对应下标：" + index);

        System.out.println("最佳匹配表：" + Arrays.toString(kmpNext(match)));

        System.out.println("kmp搜索结果：" + kmpSearch(orgin, match));
    }

    /**
     * 暴力匹配算法代码实现
     *
     * @param orgin
     * @param match
     * @return
     */
    public static int violenceMatch(String orgin, String match) {
        char[] c1 = orgin.toCharArray();
        char[] c2 = match.toCharArray();
        int i = 0;
        int j = 0;
        while (i < orgin.length() && j < match.length()) {
            if (c1[i] == c2[j]) {//匹配成功
                i++;
                j++;
            } else {//匹配失败：让i回退到j-1的位置（开始匹配时的下一个元素），j=0
                i = i - (j - 1);
                j = 0;
            }
        }

        //根据j的值判断匹配结果
        if (j == match.length())
            return i;

        return -1;
    }

    /**
     * kmp搜索算法
     *
     * @param orgin
     * @param match
     * @return 如果没有匹配到返回-1，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String orgin, String match) {

        int[] next = kmpNext(match);
        for (int i = 0, j = 0; i < orgin.length(); i++) {
            while (j > 0 && orgin.charAt(i) != match.charAt(j)) {
                j = next[j - 1];
            }

            if (orgin.charAt(i) == match.charAt(j)) {
                j++;
            }

            if (j == match.length())
                return i - j + 1;
        }

        return -1;
    }

    /**
     * 获取一个字符串（子串）的部分匹配值表
     *
     * @param dest "ABCDABD"
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//字符串长度是1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }

    public static int[] getKmpMatchTable(String match) {

        if (StrUtil.isBlank(match))
            return new int[]{};

        int matchLen = match.length();
        int[] matchTable = new int[matchLen];
        for (int i = 0; i < matchLen; i++) {
            matchTable[i] = matchNum(match.substring(0, i + 1));
        }
        return matchTable;
    }

    private static int matchNum(String str) {

        if (str.length() < 2)
            return 0;

        List<String> preList = new ArrayList<>();//前缀表
        List<String> postList = new ArrayList<>();//后缀表
        for (int i = 0; i < str.length() - 1; i++) {
            preList.add(str.substring(0, i + 1));
            postList.add(str.substring(i + 1));
        }
        List<String> list = preList.stream().filter(s -> postList.contains(s)).sorted(Comparator.comparing(s -> s.length())).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(list))
            return 0;

        return list.get(list.size() - 1).length();
    }
}
