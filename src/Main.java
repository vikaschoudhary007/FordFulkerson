import FordFulkerson.Graph.Edge;
import FordFulkerson.Graph.Graph;
import FordFulkerson.Graph.Result;
import FordFulkerson.Graph.Vertex;
import FordFulkerson.Utils.DFSLike;
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
        int upperCap = 50;

        Functions functions = new Functions();
        DFSLike DFSLike = new DFSLike();

        // Generate source sink graph
        /******************************** Generate Graph ***********************************************/

//        Graph graph = new Graph();
//        graph.generateSinkSourceGraph(n, r, upperCap);
//        graph.printGraph(graph.graph);
//
//        List<Vertex> vertices = graph.graph.keySet().stream().collect(Collectors.toList());
//        Vertex source = vertices.isEmpty() ? null : vertices.get(ThreadLocalRandom.current().nextInt(vertices.size()));
//        Vertex sink = null;
//        int bfsLength = 0;
//
//        if(source != null){
//            Map<Vertex, Integer> distances = functions.BFS(graph.graph, source);
//            sink = Collections.max(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
//            bfsLength = Collections.max(distances.entrySet(), Map.Entry.comparingByValue()).getValue();
//            System.out.println("Source node : "+ source.id);
//            System.out.println("Sink node : "+ sink.id);
//            System.out.println("BFS Length : "+ bfsLength);
//        }
//
//        graph.setSource(source);
//        graph.setSink(sink);
//        graph.setBFSLength(bfsLength);
//
//        graph.saveToCSV("graph.csv", graph.graph, source, sink, bfsLength);


        /******************************** Read Graph ***********************************************/
        Graph graph = new Graph();
        graph.graph = functions.readGraphFromCSV("graph.csv", graph);

        Vertex source = graph.getSource();
        Vertex sink = graph.getSink();
        int bfsLength = graph.getBFSLength();

        System.out.println("Source : "+ source.id+" Sink : "+ sink.id);
        graph.saveToCSV("graph2.csv", graph.graph, source, sink, bfsLength);
        System.out.println("BFSLength from CSV Graph : " + bfsLength);

        int numberOfEdgesInGraph = graph.getNumberOfEdges(graph.graph);

//        Result result1 = findMaxFlow(graph.graph, source, sink, bfsLength);
//        result1.setTotalEdges(numberOfEdgesInGraph);
//        Result.printResult(result1);


        /*****************************************************************************************/
        System.out.println("**********************************************************************");
        Result result2 = DFSLike.dfsLikeMaxFlow(graph.graph, source, sink, bfsLength);
        result2.setTotalEdges(numberOfEdgesInGraph);
        Result.printResult(result2);



       /******************************************************************************************/
//        List<Vertex> augmentingPath = DFSLike.dijkastraDFS(graph.graph, source, sink);
//        if (augmentingPath != null) {
//            System.out.print("Augmenting path: ");
//            for (Vertex v : augmentingPath) {
//                System.out.print(v.id + "->");
//            }
//        } else {
//            System.out.println("No augmenting path found.");
//        }

    }
}