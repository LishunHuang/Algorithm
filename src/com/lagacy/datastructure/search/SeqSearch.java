package com.lagacy.datastructure.search;

public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 9, 11, -1, 34, 89};//无序数组
        int index = seqSearch(arr, 11);
        System.out.println(index);
    }

    public static int seqSearch(int[] arr, int value) {
        //线性查找就是逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
