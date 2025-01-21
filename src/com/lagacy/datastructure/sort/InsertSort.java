package com.lagacy.datastructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSort(arr);
    }

    //插入排序 O(n^2)
    private static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;//即arr[1]的前面这个数的下标
            //给insertVal找到插入的位置
            //insertVal >= 0 保证在给insertVal找插入位置，不越界
            //insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            //就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //这里判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第" + (i) + "轮后");
            System.out.println(Arrays.toString(arr));
        }


    }
}
