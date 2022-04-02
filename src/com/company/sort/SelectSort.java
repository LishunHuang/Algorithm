package com.company.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        //时间复杂度是O(n^2)
        for(int i=0; i<arr.length -1 ;i++){
            int minIndex = i;
            int min = arr[i];
            for(int j = i + 1; j<arr.length; j++){
                if(min > arr[j]){//说明假定的最小值，并不是最小
                    min = arr[j];
                    minIndex = j;

                }
            }
            if(minIndex != i){ //将最小值放在arr[i]，即交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            System.out.println("第"+(i+1) +"轮后");
            System.out.println(Arrays.toString(arr));
        }

    }
}
