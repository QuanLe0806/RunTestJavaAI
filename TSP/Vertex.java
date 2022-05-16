package TSP;
class Vertex {
    private int id; 
    private int costToReach; 
    private Vertex prev; 
    
    Vertex(int id, Vertex prev, int costToReach) {
        this.id = id;
        this.prev = prev;
        this.costToReach = costToReach;
    }
    int getId() { return id;}

    int getCostToReach() { return costToReach;}

    Vertex getPrev() { return prev;}
}
