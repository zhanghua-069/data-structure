package com.xyzq.zh.tree2;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class HuffmanTreeTest {

    @Test
    public void testCreateHuffmanTree() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        HuffmanTree.Node root = HuffmanTree.createHuffmanTree(arr);
        HuffmanTree.preOrder(root);
    }

    @Test
    public void testHuffmanEncode() {
        String code = "i like like like java do you like a java";
        System.out.println("创建赫夫曼树");
        HuffmanCode.Node root = HuffmanCode.getHuffmanNode(code.getBytes());
        System.out.println("前序遍历");
        HuffmanCode.preOrder(root);

        System.out.println("生成赫夫曼编码");
        Map<Byte, String> huffmanCodes = HuffmanCode.getNodeCodes(root);
        System.out.println("赫夫曼编码结果：" + huffmanCodes);

        System.out.println("原字符串数组：" + Arrays.toString(code.getBytes()));
        System.out.println("原字符串数组长度：" + code.getBytes().length);
//        byte[] zip = HuffmanCode.zip(code.getBytes(), huffmanCodes);
        byte[] zip = HuffmanCode.zip(code);
        System.out.println("压缩后字符串数组：" + Arrays.toString(zip));
        System.out.println("压缩后字符串数组长度：" + zip.length + "，压缩率：" + new BigDecimal(code.getBytes().length - zip.length).divide(new BigDecimal(code.getBytes().length)).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN) + "%");

        System.out.println("解压缩");
        byte[] unzip = HuffmanCode.unzip(huffmanCodes, zip);
        System.out.println("解码后获得原字符串数组：" + new String(unzip));
    }

    @Test
    public void testHuffmanFileZip() {
        String srcFile = "/Users/zhanghua/Desktop/Files/gcfp.jpg";
        String destFile = "/Users/zhanghua/Desktop/Files/gcfp.zip";
        HuffmanCode.zipFile(srcFile, destFile);
        System.out.println("文件压缩成功~");
    }

    @Test
    public void testHuffmanFileUnZip() {
        String srcFile = "/Users/zhanghua/Desktop/Files/gcfp.zip";
        String destFile = "/Users/zhanghua/Desktop/Files/gcfp2.jpg";
        HuffmanCode.unzipFile(srcFile, destFile);
        System.out.println("文件解压成功~");
    }
}
