package com.company.graph;

import java.util.ArrayList;

public class Graph {
    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; //存储图对应的邻结矩阵
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {

    }

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    //得到第一个邻接结点的下标w

    /**
     *
     * @param index
     * @return 如果存在就返回对饮的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j =0; j<vertexList.size();j++){
            if(edges[index][j]>0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2){
        for(int j = v2 + 1; j< vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度有限遍历算法
    //i 第一次就是0
    public void dfs(boolean[] isVisited, int i ){
        //首先我们访问该结点，输出
        System.out.println();
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        while(w != -1){//说明有
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过了
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行一个重载，遍历我们所有的结点，并进行dfs
    public void dfs(){
        //遍历所有的结点，进行dfs
        for(int i =0; i<getNumOfVertex(); i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    //图中常用方法
    //返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
}
