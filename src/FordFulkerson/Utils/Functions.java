package FordFulkerson.Utils;

import FordFulkerson.Graph.Edge;
import FordFulkerson.Graph.Graph;
import FordFulkerson.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Functions {
    ///////////////////// BREADTH-FIRST SEARCH //////////////////////////////////

    public Map<Vertex, Integer> BFS(Map<Vertex, List<Edge>> graph, Vertex source){
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);
        distances.put(source, 0);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            for( Edge edge : graph.get(u)){
                Vertex v = edge.dest;

                if(!visited.contains(v)){
                    queue.offer(v);
                    visited.add(v);
                    distances.put(v, distances.get(u) + 1);
                }
            }
        }

        return distances;
    };

    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////  /* Shortest Augmenting path :: Dijkastra Algorithm *//////////////////////

    public static List<Vertex> dijkastra(Map<Vertex, List<Edge>> graph, Vertex source, Vertex sink){

        Map<Vertex, Integer> distances = new HashMap<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Map<Vertex, Vertex> parents = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        // initialize the queue
        for(Vertex u : graph.keySet()){
            distances.put(u, Integer.MAX_VALUE);
            parents.put(u, null);
        }

        distances.put(source, 0);
        queue.offer(source);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();

            if (!graph.containsKey(u)) {
                continue;
            }

            if(visited.contains(u)){
                continue;
            }

            visited.add(u);

            for(Edge e : graph.get(u)){
                Vertex v = e.dest;
                // int capacity = e.capacity;

                // Relax edges
                if(e.flow < e.capacity){
                    if(distances.get(v) > distances.get(u) + 1){
                        distances.put(v, distances.get(u)+1);
                        parents.put(v, u);
                        queue.offer(v);
                    }
                }
            }
        }

        List<Vertex> augmentingPath = new ArrayList<>();
        Vertex temp = sink;

        while(temp != null){
            augmentingPath.add(temp);
            temp = parents.get(temp);
        }
        Collections.reverse(augmentingPath);

        return augmentingPath.size() > 1 ? augmentingPath : null;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////// GENERATE RESIDUAL GRAPH //////////////////////////////////////////

    public static Map<Vertex, List<Edge>> generateResidualGraph(Map<Vertex, List<Edge>> graph){

        // Initialize the hashmap
        Map<Vertex, List<Edge>> residualGraph = new HashMap<>();

        // putting all the vertices same as original graph
        for(Vertex u : graph.keySet()){
            residualGraph.put(u, new ArrayList<>());
        }

        // add the forward and backward edges based on remaining capacity of each edge in original graph
        for(Vertex u : graph.keySet()){
            for(Edge originalGraphEdge : graph.get(u)){
                Vertex v = originalGraphEdge.dest;
                int capacity = originalGraphEdge.capacity;
                int flow = originalGraphEdge.flow;
                // forward edge
                residualGraph.get(u).add(new Edge(v, capacity-flow,0));

                // backward edge
                residualGraph.get(v).add(new Edge(u, flow,0));
            }
        }

        return residualGraph;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    /********************* FIND MAX FLOW *****************************************/
    public static int findMaxFlow(Map<Vertex, List<Edge>> graph, Vertex source, Vertex sink){

        int maxFlow = 0;

        Map<Vertex, List<Edge>> residualGraph = generateResidualGraph(graph);
        List<Vertex> augmentingPath = dijkastra(residualGraph, source, sink);

        if (augmentingPath != null) {
            System.out.print("Augmenting path: ");
            for (Vertex v : augmentingPath) {
                System.out.print(v.id + "->");
            }
        } else {
            System.out.println("No augmenting path found.");
        }

//        System.out.println("Augmenting path : "+ augmentingPath);
        // printGraph(residualGraph);
        while(augmentingPath != null && augmentingPath.size() > 1){
            int residualCapacity = Integer.MAX_VALUE;

//            if (augmentingPath != null) {
//                System.out.print("Augmenting path: ");
//                for (Vertex v : augmentingPath) {
//                    System.out.print(v.id + "->");
//                }
//            } else {
//                System.out.println("No augmenting path found.");
//            }

            for(int i=0; i < augmentingPath.size()-1; i++){
                Vertex u = augmentingPath.get(i);
                Vertex v = augmentingPath.get(i+1);

                for(Edge e : residualGraph.get(u)){
                    if(e.dest == v){
                        residualCapacity = Math.min(residualCapacity, e.capacity-e.flow);
                        break;
                    }
                }
            }

//            System.out.println("Residual Capacity : "+ residualCapacity);

            // update capacities for residual graph based on residual capacity
            for(int i=0; i < augmentingPath.size()-1; i++){
                Vertex u = augmentingPath.get(i);
                Vertex v = augmentingPath.get(i+1);

                for(Edge e : graph.get(u)){
                    if(e.dest == v){
                        e.flow += residualCapacity;
                        break;
                    }
                }

                for(Edge e : residualGraph.get(u)){
                    if(e.dest == v){
                        e.capacity -= residualCapacity;
                        break;
                    }
                }

                for(Edge bEdge : residualGraph.get(v)){
                    if(bEdge.dest == u){
                        bEdge.capacity += residualCapacity;
                        break;
                    }
                }
            }

            maxFlow += residualCapacity;

            residualGraph = generateResidualGraph(graph);
            augmentingPath = dijkastra(residualGraph, source, sink);

        }

        return maxFlow;
    }


////////////////////////////////////////////////////////////////////////////////////////////////

    public Map<Vertex, List<Edge>> readGraphFromCSV(String filename, Graph graph){

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if(!parts[0].equals("Source") && !parts[0].equals("Sink")){
                    int u_id = Integer.parseInt(parts[0]);
                    double u_x = Double.parseDouble(parts[1]);
                    double u_y = Double.parseDouble(parts[2]);
                    int v_id = Integer.parseInt(parts[3]);
                    double v_x = Double.parseDouble(parts[4]);
                    double v_y = Double.parseDouble(parts[5]);
                    int capacity = Integer.parseInt(parts[6]);
                    int flow = Integer.parseInt(parts[7]);

                    Vertex u = new Vertex(u_id, u_x, u_y);
                    Vertex v = new Vertex(v_id, v_x, v_y);

                   if(!graph.graph.containsKey(u)){
                       graph.addVertex(u);
                   }

                    Edge edge = new Edge(v, capacity, flow);
                    if(v_id != -1){
                        graph.graph.get(u).add(edge);
                    }

                }
                else if(parts[0].equals("Source")){
                    int s_id = Integer.parseInt(parts[1]);
                    double s_x = Double.parseDouble(parts[2]);
                    double s_y = Double.parseDouble(parts[3]);
                    Vertex source = new Vertex(s_id, s_x, s_y);
                    graph.setSource(source);
                }
                else if(parts[0].equals("Sink")){
                    int sink_id = Integer.parseInt(parts[1]);
                    double sink_x = Double.parseDouble(parts[2]);
                    double sink_y = Double.parseDouble(parts[3]);
                    Vertex sink = new Vertex(sink_id, sink_x, sink_y);
                    graph.setSink(sink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph.graph;
    }

}
