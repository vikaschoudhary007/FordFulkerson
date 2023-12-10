package FordFulkerson.Graph;
import java.io.*;
import java.util.*;

public class Graph {
    public Map<Vertex, List<Edge>> graph;
    public Vertex source;
    public Vertex sink;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        graph.put(v, new ArrayList<>());
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getSink() {
        return sink;
    }

    public void setSink(Vertex sink) {
        this.sink = sink;
    }

    public void addEdge(Vertex u, Vertex v, int capacity, int flow) {
        Edge edge = new Edge(v, capacity, flow);
        graph.get(u).add(edge);
    }

    public Map<Vertex, List<Edge>> generateSinkSourceGraph(int n, double r, int upperCap) {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();
            Vertex vertex = new Vertex(i, x, y);
            addVertex(vertex);
        }

        for (Vertex u : graph.keySet()) {
            for (Vertex v : graph.keySet()) {
                if (!u.equals(v) && Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2) <= r * r) {
                    double rand = Math.random();
                    if (rand < 0.5) {
                        if (!containsEdge(graph, u, v) && !containsEdge(graph, v, u)) {
                            addEdge(u, v, random.nextInt(upperCap) + 1,0);
                        } else {
                            if(!containsEdge(graph, u, v) && !containsEdge(graph, v, u)) {
                                addEdge(v, u, random.nextInt(upperCap) + 1,0);
                            }
                        }
                    }
                }
            }
        }

        return graph;
    }

    public static boolean containsEdge(Map<Vertex, List<Edge>> graph, Vertex u, Vertex v) {
        List<Edge> edges = graph.get(u);
        if (edges != null) {
            for (Edge edge : edges) {
                if (edge.dest.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printGraph(Map<Vertex, List<Edge>> graph) {
        for (Map.Entry<Vertex, List<Edge>> entry : graph.entrySet()) {
            int vertex = entry.getKey().id;
            List<Edge> edges = entry.getValue();

            System.out.print("Vertex " + vertex + ": ");
            for (Edge edge : edges) {
                System.out.print("(" + edge.dest.id + ", " + edge.capacity + ", " + edge.flow + ") ");
            }
            System.out.println();
        }
    }

    public void saveToCSV(String filename, Map<Vertex, List<Edge>> graph, Vertex source, Vertex sink){
        try(PrintWriter writer = new PrintWriter(new File(filename))){
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Vertex, List<Edge>> entry : graph.entrySet()){
                Vertex u = entry.getKey();
                List<Edge> edges = entry.getValue();

                if(edges.isEmpty()){
                    sb.append(u.id).append(",").append(u.x).append(",").append(u.y).append(",").append(-1).append(",").append(-1.0).append(",").append(-1.0).append(",").append(-1).append(",").append(-1).append("\n");
                }
                else{
                    for(Edge edge : edges){
                        Vertex v = edge.dest;
                        int capacity = edge.capacity;
                        int flow = edge.flow;
                        sb.append(u.id).append(",").append(u.x).append(",").append(u.y).append(",").append(v.id).append(",").append(v.x).append(",").append(v.y).append(",").append(capacity).append(",").append(flow).append("\n");
                    }
                }
            }
            sb.append("Source").append(",").append(source.id).append(",").append(source.x).append(",").append(source.y).append("\n");
            sb.append("Sink").append(",").append(sink.id).append(",").append(sink.x).append(",").append(sink.y).append("\n");

            writer.write(sb.toString());

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

}
