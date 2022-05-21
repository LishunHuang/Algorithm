package com.company.hashtab;

import java.util.HashMap;
import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找的id");
                    int empId = scanner.nextInt();
                    hashTab.findEmpById(empId);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTab管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少条链表

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //注意需要分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工id得到该与昂影带添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[hashFun(id)].findEmpById(id);
        if (emp != null) {
            System.out.println("在第" + empLinkedListNo + "条链表找到雇员" + id);
        } else {
            System.out.println("没有找到雇员");
        }
    }


}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head;

    //添加雇员到链表
    //1.添加雇员时，id是自增长，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next; //后移
        }
        //退出时直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        System.out.print("第" + no + "链表的信息为");
        Emp curEmp = head;//辅助指针
        while (true) {
            System.out.println("=> id = " + curEmp.id + " name = " + curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    //根据id查找雇员
    //如果查找到就返回Emp如果没有找到就返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }


}
