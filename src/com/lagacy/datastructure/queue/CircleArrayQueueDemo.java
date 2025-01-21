package com.lagacy.datastructure.queue;

//使用模拟数组模拟队列 - 编写一个ArrayQueue类
class CircleArrayQueue {
    private int maxSize;

    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]
    //front的初始值 = 0
    private int front;

    //rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个位置用来作为判断队列空还是满的约定
    //rear的初始值 = 0
    private int rear;//尾
    private int[] arr;

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; //指向队列头部，分析出front是指向队列头的前一个位置
        rear = 0; //指向队列尾，指向队列尾的数据（队列最后一个数据）
    }

    //判断队列满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = i;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保存到临时的变量
        //2.将front后移,考虑取余
        //3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
            return;
        }
        //从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);

        }
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}


//环形数组队列
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //队列有效数据是3
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        circleArrayQueue.addQueue(10);
        circleArrayQueue.addQueue(20);
        circleArrayQueue.addQueue(30);
        circleArrayQueue.showQueue();
        circleArrayQueue.addQueue(40);
        circleArrayQueue.showQueue();
        System.out.println();
        System.out.println("队列头是：" + circleArrayQueue.headQueue());
        System.out.println("取出：" + circleArrayQueue.getQueue());
        System.out.println("队列头是：" + circleArrayQueue.headQueue());
        circleArrayQueue.showQueue();
        circleArrayQueue.addQueue(40);
        circleArrayQueue.showQueue();

    }
}
