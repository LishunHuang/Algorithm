package com.datastructure.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5,6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}
//顺序存储二叉树遍历
class ArrayBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        preOrder(0);
    }

    //顺序存储二叉树的前序遍历
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        //向左递归
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归
        if((index * 2 + 2)< arr.length){
            preOrder(2* index + 2);
        }
    }
}
