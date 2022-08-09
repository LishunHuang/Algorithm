package com.datastructure.linkedlist;

//定义一个Heronode,每个HeroNode对象就是一个节点
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre;//指向前一个节点
    public HeroNode2 next; //指向后一个节点

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

class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链遍的方法
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动,因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp.toString());
            //将temp后移
            temp = temp.next;
        }
    }

    //添加
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表,找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没找到往后移
            temp = temp.next;
        }
        //当推出while循环时,temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
    //只是把节点改成HeroNode2
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;

        while (true) {
            if (temp == null) {
                //表示已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("没找到节点,不能修改");
        }
    }


    //删除节点
    //对于双向链遍，我们可以直接找到删除的这个节点
    //找到后自我删除即可
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表是空的");
            return;
        }
        boolean flag = false; //辅助指针
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果这个节点是最后一个节点，加判断是否为空指针
            if(temp.next == null){
                return;
            }else{
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.println("没找到节点");
        }

    }

}

public class DoubleLinkedListDemo {


    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 node1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表的对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();
        HeroNode2 node5 = new HeroNode2(4, "公孙胜","入云龙");
        doubleLinkedList.update(node5);
        System.out.println("修改后的链表");
        doubleLinkedList.list();

        //删除
        System.out.println("删除后的链表");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();

    }
}
