package TSP;

import java.util.ArrayList;

class PathNode implements Comparable<PathNode>{
    private int currentVertexId; 
    private double lb; 
    private boolean[] visitedVertices; 
    private int totalVisitedVertices; 
    private int pathTotalCost; 
    private ArrayList<VerticesPair> verticesPairs; 
    private PathNode previousPathNode; 

    @Override
    public int compareTo(PathNode p2){
        int lbComparison = Double.compare(this.getLb(),p2.getLb());
        if (lbComparison != 0)
            return lbComparison;
        return -1;
    }

    PathNode(int currentVertexId, ArrayList<VerticesPair> closestPairs){
        verticesPairs = closestPairs;
        visitedVertices = new boolean[closestPairs.size()];
        visitedVertices[currentVertexId] = true;
        totalVisitedVertices = 1;
        previousPathNode = null;
        pathTotalCost = 0;
        lb = calculateLowerBound();
    }

    PathNode(int vertexToVisit, int costToReachVertex, PathNode parentPathNode) {
        this.previousPathNode = parentPathNode;
        this.verticesPairs = new ArrayList<>();
        for (VerticesPair verticesPair : parentPathNode.getVerticesPairs())
        {
            VerticesPair copyVerticesPair = new VerticesPair();
            copyVerticesPair.setVerticesIds(verticesPair.getVerticesIds());
            copyVerticesPair.setCostsToReachVertices(verticesPair.getCostsToReachVertices());
            verticesPairs.add(copyVerticesPair);
        }

        this.currentVertexId = vertexToVisit;

        visitedVertices = new boolean[parentPathNode.getVisitedVertices().length];
        for (int i = 0; i < visitedVertices.length; i++)
            visitedVertices[i] = parentPathNode.getVisitedVertices()[i];
        visitedVertices[currentVertexId] = true;

        if(parentPathNode.getPreviousPathNode() != null)
            verticesPairs.get(parentPathNode.getCurrentVertexId()).replaceVertex(currentVertexId,costToReachVertex,parentPathNode.getPreviousPathNode().getCurrentVertexId());
        else
            verticesPairs.get(parentPathNode.getCurrentVertexId()).replaceVertex(currentVertexId,costToReachVertex,-1);
        verticesPairs.get(currentVertexId).replaceVertex(parentPathNode.getCurrentVertexId(),costToReachVertex,parentPathNode.getCurrentVertexId());

        totalVisitedVertices = parentPathNode.getTotalVisitedVertices() + 1;
        pathTotalCost = parentPathNode.getPathTotalCost() + costToReachVertex;
        lb = calculateLowerBound();
    }

    double calculateLowerBound(){
        double sum = 0 ;
        for (VerticesPair verticesPair : verticesPairs)
            sum += verticesPair.getCostsToReachVertices()[0] + verticesPair.getCostsToReachVertices()[1];
        return sum/2;
    }

    int getCurrentVertexId() { return currentVertexId;}

    double getLb() { return lb;}

    PathNode getPreviousPathNode() { return previousPathNode;}

    boolean[] getVisitedVertices() { return visitedVertices;}

    int getPathTotalCost() { return pathTotalCost;}

    ArrayList<VerticesPair> getVerticesPairs() { return verticesPairs;}

    int getTotalVisitedVertices(){ return totalVisitedVertices;}
}
