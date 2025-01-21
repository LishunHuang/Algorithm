package com.lagacy.datastructure.linkedlist;

import java.util.Stack;

//定义一个Heronode,每个HeroNode对象就是一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickName) {
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

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单项链表
    //思路,当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next指向 新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode temp = head;
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
        //将最后这个节点的next指向 新的节点
        temp.next = heroNode;
    }

    //第二种方式在添加时,根据排名将英雄插入到指定位置
    //(如果有这个排名,则添加失败,并给出提示)
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动,因此我们需要一个辅助节点
        //因为单链表,因为我们找的temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false; //调制添加的编号是否存在,默认为false
        while (true) {
            if(temp.next == null ){//说明temp在链表最后
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到了,就在temp的后面插入
                break;
            }
            else if (temp.next.no == heroNode.no){//说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移
        }

        //判断flag的值
        if(flag){ //说明不能添加,说明编号存在
            System.out.println("说明插入的英雄编号已经存在,不能加入");
        }else{
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点的信息,根据no编号来修改,即no编号不能改
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;

        while(true){
            if( temp == null){
                //表示已经遍历完链表
                break;
            }
            if(temp.no == newHeroNode.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.println("没找到节点,不能修改");
        }
    }

    //删除节点
    //思路:
    //1. head不能动,因此我们需要一个tmep辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较是,是temp.next.no 和需要删除的节点的no比较
    public void delete(int no){
        if(head.next == null){
            System.out.println("链表是空的");
            return;
        }
        boolean flag = false;
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }
        else{
            System.out.println("没找到节点");
        }

    }



    //显示列表[遍历]
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
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



}

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "松江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedListByOrder = new SingleLinkedList();
        //普通添加
        singleLinkedList.add(node1);
        singleLinkedList.add(node2);
        singleLinkedList.add(node3);
        singleLinkedList.add(node4);
        singleLinkedList.delete(4);
        singleLinkedList.list();
        System.out.println();
        //按照编号顺序添加
        singleLinkedListByOrder.addByOrder(node1);
        singleLinkedListByOrder.addByOrder(node4);
        singleLinkedListByOrder.addByOrder(node2);
        singleLinkedListByOrder.addByOrder(node3);
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
//        System.out.println("修改前");
//        singleLinkedList.list();
//
//        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟!");
//        singleLinkedListByOrder.update(newHeroNode);
//        System.out.println("修改后");
//        //删除一个节点
//        singleLinkedListByOrder.delete(4);
//
//        System.out.println("有效的节点个数：" + getLength(singleLinkedListByOrder.getHead()));
//
//
//
//        singleLinkedListByOrder.list();
//        System.out.println("倒数第二个:" + findLastIndexNode(singleLinkedList.getHead(),2).toString());
    }
    /**
     * 获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头节点）
     * @param head
     * @return
     */
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //没有统计头节点
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur  = cur.next;//遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第K个节点
     * 思路：双指针
     * 1. 编写一个方法，接受head节点，同时接受一个Index
     * 2. index表示是倒数第index个节点
     * 3. 先把链表从头到位遍历一次，得到链表长度
     * 4. 得到size后我们链表的第一个开始遍历（size-index）个，就可以了
     * 5. 如果找到返回该节点，否则null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        //判断链表为空，返回null
        if(head.next == null) return null;
        //第一次遍历得到链表长度（节点个数）
        int size = getLength(head);
        //第二次遍历size - index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if(index <= 0 || index>size) return null;
        //定义辅助变量
        HeroNode cur = head.next;
        //定义给辅助变量，for玄幻定位到倒数的index
        for(int i = 0; i<size-index;i++){
            cur = cur.next;

        }
        return cur;
    }

    /**
     * 反转链表
     * 思路：
     * 1.先定义一个节点reverseHead = new HeroNode();
     * 2. 从头到位遍历原来的链表，每遍历一个节点，就将其去除，并放在新的链表的最前端
     * 3.原来的链表的head.next=reverseHead.next
     */
    public static void reverseList(HeroNode head){
        //如果当前链表为空,或者只有一个节点,无需反转,直接返回
        if(head.next == null || head.next.next ==null) return;
        //定义一个辅助指针,帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前的节点(cur)的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表,并从头到位遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur!= null){
            next = cur.next;//先暂时保存当前节点的下一个节点,因为后面需要用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    public static void reversePrint(HeroNode head){
        if(head.next == null) return;
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}