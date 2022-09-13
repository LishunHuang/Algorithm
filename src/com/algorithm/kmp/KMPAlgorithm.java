package com.algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String stt1 = "1234567890";
        String str2 = "5678";
        System.out.println(Arrays.toString(kmpNext("ABCDABD")));
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要考虑str1.charAt(i) != str2.charAt(j)
            //核心
            while( j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {//找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串(子串)部分匹配值
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //dest.charAt(i) != dest.charAt(j)时 我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立时推出
            //核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当这个条件满足时,部分匹配值就是要加1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
