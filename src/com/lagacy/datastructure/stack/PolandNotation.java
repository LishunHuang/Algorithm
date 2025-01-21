package com.lagacy.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //将一个中缀表达式转成后缀表达式
        //1 初始化两个栈：运算符s1和初春中间结果的栈s2
        //2 从左到右扫描中缀表达式
        //3 遇到操作数，入栈s2
        //4 遇到运算符时，比较其与s1栈顶运算符的优先级
        //  4.1如果s1为空，或者栈顶运算符为左括号，直接将运算符入栈
        //  4.2否则，若优先级比栈顶运算符的高，也将运算符压入s1
        //  4.3否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4。1与s1中新的栈顶运算符相比较
        //5遇到括号时
        //  5.1如果是左括号，入栈s1
        //  5.2如果是右括号，则依次弹出s1栈顶的运算符，并入栈s2,直到遇到左括号为止，此时将这一对括号丢弃
        //6重复步骤2到5，直到表达式的最右边
        //7将s1中剩余的运算符依次弹出并入栈s2
        //8依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

        //1+（（2+3）*4）-5 =》 转成1 2 3 + 4 * + 5 -
        //将得到的中缀表达式对应的List =》后缀表达式对应的List
        //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] =》 [1,2,3,+,4,*,5,-]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressiontoList = toInfixExpressionList(expression);
        System.out.println("中缀表达式 = " + infixExpressiontoList); //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressiontoList);
        System.out.println("后缀表达式 = " + parseSuffixExpressionList);
        System.out.println("expression = " + calculate(parseSuffixExpressionList));


//        //先定义一个逆波兰表达式
//        //（3+4）X 5 - 6 对应的是 3 4 + 5 X 6 -
//        String suffixExpression = "3 4 + 5 * 6 - ";
//        //思路：
//        //1。先将suffix expression放到一个ArrayList中
//        //2。将Array list传给一个方法，遍历ArrayList配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        System.out.println("rpnList=" + list);
//        int res = calculate(list);
//        System.out.println("计算的结果是=" + res);
    }


    //方法：将中缀表达式专程对应的list
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历到一个字符就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls去
            if (((c = s.charAt(i)) < 48) || ((c = s.charAt(i)) > 57)) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数，需要考虑多位数
                str = "";//先将str置成""
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>(); //符号栈
        //因为s2这个栈，在整个转换过程中没有pop操作，而且后面我们还需要逆序输出
        //我们不需要用Stack<String>,直接用List<String> s2
        List<String> s2 = new ArrayList<>(); // 储存中间结果的栈s2
        //遍历ls
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1）与s1中新的栈项运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);

            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //因为是存放到List，因此按顺序输出就是对应的后缀表达式对应的List

    }


    //完成对逆波兰表达式的运算

    /**
     * 1。从左往右扫描，将3和4压入堆栈
     * 2。遇到+运算符，因此弹出4和3（4是顶）计算出3+4的值，将7入栈
     * 3。将5入栈
     * 4。弹出5和7，计算出7x5=35，将35入栈
     * 5。将6入栈
     * 6。35-6的值，得29
     */
    public static int calculate(List<String> ls) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");


            }
        }
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类Operation,可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}