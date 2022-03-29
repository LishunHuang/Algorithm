package com.company.recursion;

public class Maze {
    public static void main(String[] args) {
        //2维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示强
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;


        setWay(map,1,1);
        //输出新的地图，小球走过，并标识过的递归
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }
    //使用递归回溯来给小球找路

    /**
     * 如果小球能到map[6][5]则说明通路找到
     * 1表示墙，0表示没有走过，2表示可以走，3表示走过但是走不通
     * 策略 下-》右-》上-》左 如果走不通，回溯
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路，返回true
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0 ){//如果当前这个点还没有走过
                //按照策略走
                map[i][j] = 2;//假定可以走通
                if(setWay(map, i+1, j)){ //向下走
                    return true;
                }else if(setWay(map, i, j+1)){//向右走
                    return true;
                }else if(setWay(map, i-1,j)){//向上走
                    return true;
                }else if(setWay(map, i, j-1)){//向左走
                    return true;
                }else{ //说明走不通是死路
                    map[i][j] = 3;
                    return false;
                }
            }else{ //如果map[i][j] != 0, 可能是1，2，3
                return false;
            }
        }
    }
}
