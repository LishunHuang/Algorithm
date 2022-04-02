package com.company.sort;


import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        //冒泡排序的时间复杂度O(n^2)
        System.out.println("排序前: "+ Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));


    }
    public static void  bubbleSort(int[] arr){
        //第一趟排序，就是将最大的数排在最后
        int temp = 0;
        boolean flag = false; //标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if(flag == false){//在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false;//重置fla，进行下次判断
            }
        }
    }
}
