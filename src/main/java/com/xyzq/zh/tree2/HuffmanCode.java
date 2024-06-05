package com.xyzq.zh.tree2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 赫夫曼编码
 * 1.将字符构建成一棵赫夫曼树（权值为字符出现的个数）
 * 2.沿字符所在路径生成编码（前缀编码：规定向左为0，向右为1）
 * 3.按上面的赫夫曼编码将要发送的字符串生成最终的编码串
 */
public class HuffmanCode {

    /**
     * 解压文件
     *
     * @param srcFile  压缩文件路径
     * @param destFile 解压后文件路径
     */
    public static void unzipFile(String srcFile, String destFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //获取解码后的字节数组
            byte[] unzipBytes = unzip(huffmanCodes, huffmanBytes);
            //写入目标文件中
            os = new FileOutputStream(destFile);
            os.write(unzipBytes);
        } catch (Exception e) {
            System.out.println("解压失败：" + e.getMessage());
        } finally {
            try {
                if (os != null)
                    os.close();

                if (ois != null)
                    ois.close();

                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param srcFile  压缩文件全路径
     * @param destFile 压缩后目标文件路径
     */
    public static void zipFile(String srcFile, String destFile) {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fis = new FileInputStream(srcFile);
            //创建一个与文件输入流同样大小的字节数组
            byte[] bytes = new byte[fis.available()];
            //读取文件
            fis.read(bytes);

            //获取赫夫曼编码表
            Map<Byte, String> huffmanCodes = getNodeCodes(getHuffmanNode(bytes));
            //直接对源文件进行压缩
            byte[] huffmanBytes = zip(bytes, huffmanCodes);
            //创建文件输入流，存放压缩文件
            fos = new FileOutputStream(destFile);
            //创建一个和文件输出流关联的ObjectOutputStream，后面用它来压缩和恢复文件
            oos = new ObjectOutputStream(fos);

            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //将赫夫曼编码表同时写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println("文件压缩失败：" + e.getMessage());
        } finally {
            try {
                if (fos != null)
                    fos.close();

                if (oos != null)
                    oos.close();

                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                System.out.println("关闭文件流失败：" + e.getMessage());
            }
        }
    }

    /**
     * 加压（转码）
     * 除最后一个字节外，需要补高位（正数（补码）可能不足8位）
     * <p>
     * 1.将压缩后的字节数组转成二进制字符串编码
     * 2.获取赫夫曼解码表
     * 3.遍历二进制字符串编码对照解码表解出原字符串
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    public static byte[] unzip(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        //最后一个字节，无需补高位
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            builder.append(byte2BitString(huffmanBytes[i], !(i == huffmanBytes.length - 1)));
        }

        String decodeStr = builder.toString();
        Map<String, Byte> decodeMap = huffmanCodes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        List<Byte> byteList = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < decodeStr.length(); ) {//++在循环体内进行，因此for循环外侧无需再++
            Byte b;
            do {
                temp.append(decodeStr.charAt(i));
                i++;
            } while ((b = decodeMap.get(temp.toString())) == null);

            //获取到解码字节
            byteList.add(b);
            temp = new StringBuilder();
        }

        byte[] decodeBytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++)
            decodeBytes[i] = byteList.get(i);

        return decodeBytes;
    }

    /**
     * 压缩重载，整合所有步骤
     *
     * @param str
     * @return
     */
    public static byte[] zip(String str) {

        byte[] bytes = str.getBytes();
        //构建赫夫曼树
        Node root = getHuffmanNode(bytes);
        //生成赫夫曼编码表
        Map<Byte, String> huffmanCodes = getNodeCodes(root);
        //压缩
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将原字符数组压缩为字符串编码后的数组
     *
     * @param bytes
     * @param huffmanCodes 赫夫曼编码map
     * @return
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder builder = new StringBuilder();
        //将原字符数组根据赫夫曼编码拼接成一个二进制字符串
        for (byte b : bytes)
            builder.append(huffmanCodes.get(b));

        String zipStr = builder.toString();
//        System.out.println("压缩后的二进制字符串编码：" + zipStr);
        int builderLen = builder.length();
        byte[] huffmanCodeBytes = new byte[(builderLen + 7) / 8];
        for (int i = 0, index = 0; i < builderLen; i += 8) {
            //每8位一个字节，因此这里要+8
            String subCode;
            if (i + 8 >= builderLen) {
                subCode = zipStr.substring(i, builderLen);
            } else {
                subCode = zipStr.substring(i, i + 8);
            }
            //转二进制编码并放入压缩数组中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(subCode, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 重载方法：获取赫夫曼树对应的赫夫曼编码（从root节点开始）
     *
     * @param root 赫夫曼树根节点
     * @return
     */
    public static Map<Byte, String> getNodeCodes(Node root) {
        Map<Byte, String> huffmanCodes = new HashMap<>();
        if (root != null) {
            if (root.left != null)
                getNodeCodes(root.left, "0", new StringBuilder(), huffmanCodes);
            if (root.right != null)
                getNodeCodes(root.right, "1", new StringBuilder(), huffmanCodes);
        }
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     *
     * @param node         传入节点
     * @param code         路径：规定左节点为0，右节点为1
     * @param builder      用于拼接节点的路径
     * @param huffmanCodes K-节点，V-节点的赫夫曼编码（节点路径）
     */
    public static void getNodeCodes(Node node, String code, StringBuilder builder, Map<Byte, String> huffmanCodes) {
        StringBuilder builder2 = new StringBuilder(builder);//将之前的路径拼接上（方法有递归）
        //将code拼接到路径上
        builder2.append(code);
        if (node.data == null) {
            //非叶子节点
            if (node.left != null) {
                //向左递归
                getNodeCodes(node.left, "0", builder2, huffmanCodes);
            }
            if (node.right != null) {
                //向右递归
                getNodeCodes(node.right, "1", builder2, huffmanCodes);
            }

        } else {
            //叶子节点（即需要编码的节点）
            huffmanCodes.put(node.data, builder2.toString());
        }
    }

    /**
     * 将字符数组组成赫夫曼树节点
     *
     * @param bytes
     * @return
     */
    public static Node getHuffmanNode(byte[] bytes) {

        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count != null) {
                map.put(b, count + 1);
            } else {
                map.put(b, 1);
            }
        }

        List<Node> nodes = map.entrySet().stream().map(entry -> new Node(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    /**
     * 前序遍历
     */
    public static void preOrder(Node root) {

        if (root == null) {
            System.out.println("空树不可遍历");
        } else {
            root.preOrder();
        }
    }

    /**
     * 将一个byte转成二进制字符串
     * <p>
     * 【重点说明】非最后一个字节需要补高位，因为正数可能不足8位（正数的补码=原码，如15：0111，需要在高位补齐0000 0111）
     *
     * @param b    目标byte
     * @param flag 是否需要补高位
     * @return b对应的二进制字符串 【注意】是按补码返回
     */
    private static String byte2BitString(byte b, boolean flag) {

        int temp = b;//将b转成int
        if (flag) {
            //如果是正数需要高位补码（8位字节通过 按位或 的方式补码）
            temp |= 256;
            //int 十进制转二进制
            String s = Integer.toBinaryString(temp);
            return s.substring(s.length() - 8);
        }
        return Integer.toBinaryString(temp);
    }

    static class Node implements Comparable<Node> {

        Byte data;//字符
        int weight;//权值：字符出现的个数
        Node left;
        Node right;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            //1.先输出父节点
            System.out.println(this);

            //2.递归左子树前序遍历
            if (this.left != null)
                this.left.preOrder();

            //3.递归右子树前序遍历
            if (this.right != null)
                this.right.preOrder();
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + ", weight=" + weight + '}';
        }
    }
}
