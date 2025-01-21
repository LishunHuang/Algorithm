package com.lagacy.datastructure.queue;

//使用模拟数组模拟队列 - 编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize;
    private int front;//头的前一个位置
    private int rear;//尾
    private int[] arr;

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1; //指向队列尾，指向队列尾的数据（队列最后一个数据）
    }

    //判断队列满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int i) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        rear++;
        arr[rear] = i;
    }

    //获取队列数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;//front后移
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        for (int j : arr) {
            System.out.print(j + "\t");
        }
    }

    //显示队列头
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front + 1];
    }


}

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.addQueue(10);
        arrayQueue.addQueue(20);
        arrayQueue.addQueue(30);
        arrayQueue.showQueue();
        System.out.println();
        System.out.println("队列头是：" + arrayQueue.headQueue());
        System.out.println("取出：" + arrayQueue.getQueue());
        System.out.println("队列头是：" + arrayQueue.headQueue());
        arrayQueue.showQueue();

    }
}
