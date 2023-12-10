import FordFulkerson.Graph.Edge;
import FordFulkerson.Graph.Graph;
import FordFulkerson.Graph.Vertex;
import FordFulkerson.Utils.Functions;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static FordFulkerson.Utils.Functions.findMaxFlow;

public class Main {

    public static void main(String[] args) {
        int n = 100;
        double r = 0.2;
        int upperCap = 10;

        Functions functions = new Functions();

        // Generate source sink graph
        /******************************** Generate Graph ***********************************************/

//        Graph graph = new Graph();
//        graph.generateSinkSourceGraph(n, r, upperCap);
//        graph.printGraph(graph.graph);
//
//        List<Vertex> vertices = graph.graph.keySet().stream().collect(Collectors.toList());
//        Vertex source = vertices.isEmpty() ? null : vertices.get(ThreadLocalRandom.current().nextInt(vertices.size()));
//        Vertex sink = null;
//
//        if(source != null){
//            Map<Vertex, Integer> distances = functions.BFS(graph.graph, source);
//            sink = Collections.max(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
//            System.out.println("Source node : "+ source.id);
//            System.out.println("Sink node : "+ sink.id);
//        }
//
//        graph.setSource(source);
//        graph.setSink(sink);
//
//        graph.saveToCSV("graph.csv", graph.graph, source, sink);


        /******************************** Read Graph ***********************************************/
        Graph graph = new Graph();
        graph.graph = functions.readGraphFromCSV("graph.csv", graph);
        graph.printGraph(graph.graph);

        Vertex source = graph.getSource();
        Vertex sink = graph.getSink();

        System.out.println("Source : "+ source.id+" Sink : "+ sink.id);
        graph.saveToCSV("graph2.csv", graph.graph, source, sink);

        /******************************************************************************************/
        List<Vertex> augmentingPath = functions.dijkastra(graph.graph, source, sink);
        if (augmentingPath != null) {
            System.out.print("Augmenting path: ");
            for (Vertex v : augmentingPath) {
                System.out.print(v.id + "->");
            }
        } else {
            System.out.println("No augmenting path found.");
        }

         int maxFlow = findMaxFlow(graph.graph, source, sink);
         System.out.println("Maximum flow is : "+ maxFlow);

    }
}