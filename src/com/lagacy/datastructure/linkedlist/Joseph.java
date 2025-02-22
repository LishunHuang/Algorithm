package com.lagacy.datastructure.linkedlist;


//环形链表
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBody(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    //创建小孩节点，构建成一个环形的链表
    public void addBody(int nums) {
        //nums做一个数据效验
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号： " + curBoy.getNo());
            if (curBoy.getNext() == first) {//遍历完了
                break;
            }
            curBoy = curBoy.getNext(); //后移一位
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈顺序
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示输几下
     * @param nums     表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行效验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，重新输入");
            return;
        }
        //创建要给辅助指针，帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时的移动m-1一次，然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while (true) {
            if (helper == first) { //说明圈中只一个节点
                break;
            }
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩的节点
            System.out.println("要出去小孩的编号： " + first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("最后留在圈中的小孩编号： "+first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
