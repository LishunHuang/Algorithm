package com.company.linkedlist;

//定义一个Heronode,每个HeroNode对象就是一个节点
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode pre;//指向前一个节点
    public HeroNode next; //指向后一个节点

    public HeroNode2(int hNo, String hName, String hNickName) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hNickName;

    }

    //为了显示方便,我们重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}

class doubleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

}
public class DoubleLinkedListDemo {


    public static void main(String[] args) {

    }
}
