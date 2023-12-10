package FordFulkerson.Graph;

public class Edge {
    public Vertex dest;
    public int capacity;
    public int flow;

    public Edge(Vertex dest, int capacity, int flow){
        this.dest = dest;
        this.capacity = capacity;
        this.flow = flow;
    }
}
