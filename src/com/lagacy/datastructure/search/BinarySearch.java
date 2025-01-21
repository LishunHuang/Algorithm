package com.lagacy.datastructure.search;

import java.util.ArrayList;

//二分查找前提必须是有序数组
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length, 1);
        System.out.println(index);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left > right时， 说明递归整个数组，但是没有找到
        if (left > right) return -1;

        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findVal > midValue) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 找到所有相同的数值
     * 在找到mid索引值，不要马上返回
     * 向mid索引值的左边扫描，将所有满足1000的元素下标加入到集合里面
     * 向mid索引值的右边扫描，将所有满足1000的元素下标加入到Arrayliist
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static ArrayList<Integer> binarySearchAll(int[] arr, int left, int right, int findVal) {
        //当left > right时， 说明递归整个数组，但是没有找到
        if (left > right) return new ArrayList<>();

        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findVal > midValue) {//向右递归
            return binarySearchAll(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return binarySearchAll(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则就把temp放入到ArrayList
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            temp = mid + 1;

            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //否则就把temp放入到ArrayList
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
