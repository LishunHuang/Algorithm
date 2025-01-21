package com.lagacy.algorithm.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "1234567";
        String s2 = "234";
        System.out.println(violenceMatch(s1, s2));
    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1len = s1.length;
        int s2len = s2.length;

        int i = 0;//索引指向s1
        int j = 0;//索引指向s2
        while (i < s1len && j < s2len) {//保证匹配时,不越界
            if (s1[i] == s2[j]) {//如果找到相同的字符串
                i++;
                j++;
            } else {//如果没有找到
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
