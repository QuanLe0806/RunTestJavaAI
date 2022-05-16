/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TSP;

import java.util.Stack;



class DFS {
    private int numberGraphlength; 
    private int [][] graph; 
    private boolean[] visitedVertices; 
    private int shortestPathCost; 
    private Vertex lastVertex;

    DFS(int[][] graph){
        this.numberGraphlength = graph.length;
        this.graph = graph;
        this.visitedVertices = new boolean[numberGraphlength];
        this.shortestPathCost = Integer.MAX_VALUE;
        this.lastVertex = null;
        visitedVertices[0] = true;
    }

    int getShortestPathCost(){ 
        dfs(new Vertex(0,null,0),  1);
        return shortestPathCost;
    }


    void dfs(Vertex currentVertex, int verticesVisited){
        if (verticesVisited == numberGraphlength && graph[currentVertex.getId()][0] > 0){
            shortestPathCost = currentVertex.getCostToReach() + graph[currentVertex.getId()][0];
            lastVertex = currentVertex;
            return;
        }
        int closestVertexToReach = -1; 
        int costToReachClosestVertex = Integer.MAX_VALUE; 
        for (int i = 0; i < numberGraphlength; i++){
            if (!visitedVertices[i] && graph[currentVertex.getId()][i] > 0){
                Vertex neighbourVertex = new Vertex(i, currentVertex, currentVertex.getCostToReach() + graph[currentVertex.getId()][i]);
                if (neighbourVertex.getCostToReach() < costToReachClosestVertex){
                    closestVertexToReach = i;
                    costToReachClosestVertex = neighbourVertex.getCostToReach();
                }
            }
        }
        Vertex nextVertex = new Vertex(closestVertexToReach, currentVertex, currentVertex.getCostToReach() + graph[currentVertex.getId()][closestVertexToReach]);
        visitedVertices[closestVertexToReach] = true;
        dfs(nextVertex, verticesVisited + 1);
    }

    Stack<Integer> getShortestPath(){
        Stack<Integer> shortestPath = new Stack<>();
        shortestPath.push(0);
        Vertex currentVertex  = lastVertex;
        while(currentVertex != null){
            shortestPath.push(currentVertex.getId());
            currentVertex = currentVertex.getPrev();
        }
        return shortestPath;
    }
}

