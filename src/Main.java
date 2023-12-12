import FordFulkerson.Graph.Edge;
import FordFulkerson.Graph.Graph;
import FordFulkerson.Graph.Result;
import FordFulkerson.Graph.Vertex;
import FordFulkerson.Utils.DFSLike;
import FordFulkerson.Utils.Functions;
import FordFulkerson.Utils.MaxCap;
import FordFulkerson.Utils.RandomSearch;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {

    public static void createAndSaveGraph(int n, double r, int upperCap){


        Graph graph = new Graph();
        Functions functions = new Functions();
        graph.generateSinkSourceGraph(n, r, upperCap);
//        graph.printGraph(graph.graph);

        List<Vertex> vertices = graph.graph.keySet().stream().collect(Collectors.toList());
        Vertex source = vertices.isEmpty() ? null : vertices.get(ThreadLocalRandom.current().nextInt(vertices.size()));
        Vertex sink = null;
        int bfsLength = 0;

        if(source != null){
            Map<Vertex, Integer> distances = functions.BFS(graph.graph, source);
            sink = Collections.max(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
            bfsLength = Collections.max(distances.entrySet(), Map.Entry.comparingByValue()).getValue();
            System.out.println("Source node : "+ source.id);
            System.out.println("Sink node : "+ sink.id);
            System.out.println("BFS Length : "+ bfsLength);
        }

        graph.setSource(source);
        graph.setSink(sink);
        graph.setBFSLength(bfsLength);

        graph.saveToCSV("/graph_"+n+"_"+r+"_"+upperCap+".csv", graph.graph, source, sink, bfsLength);
    }

    public static void readGraphAndCallMax(int n, double r, int upperCap){

        Functions functions = new Functions();
        DFSLike DFSLike = new DFSLike();
        MaxCap MaxCap = new MaxCap();
        RandomSearch RandomSearch = new RandomSearch();

        Graph graph = new Graph();
        graph.graph = functions.readGraphFromCSV("graph_"+n+"_"+r+"_"+upperCap+".csv", graph);

        Vertex source = graph.getSource();
        Vertex sink = graph.getSink();
        int bfsLength = graph.getBFSLength();

        System.out.println("Source : "+ source.id+" Sink : "+ sink.id);
        int numberOfEdgesInGraph = graph.getNumberOfEdges(graph.graph);

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Algorithm for Augmenting path : Enter 1 for SAP, 2 for DFS-Like, 3 for MaxCap, 4 for Random");
        int algo = Integer.parseInt(sc.nextLine());

        switch (algo){
            case 1 :
                Result result1 = Functions.findMaxFlow(graph.graph, source, sink, bfsLength);
                result1.setTotalEdges(numberOfEdgesInGraph);
                Result.printResult(result1);
                break;
            case 2 :
                Result result2 = DFSLike.dfsLikeMaxFlow(graph.graph, source, sink, bfsLength);
                result2.setTotalEdges(numberOfEdgesInGraph);
                Result.printResult(result2);
                break;
            case 3 :
                Result result3 = MaxCap.maxCapMaxFlow(graph.graph, source, sink, bfsLength);
                result3.setTotalEdges(numberOfEdgesInGraph);
                Result.printResult(result3);
                break;
            case 4 :
                Result result4 = RandomSearch.randomMaxFlow(graph.graph, source, sink, bfsLength);
                result4.setTotalEdges(numberOfEdgesInGraph);
                Result.printResult(result4);
                break;
        }
    }

    public static void main(String[] args) {
//        int n = 100;
//        double r = 0.2;
//        int upperCap = 50;

        Functions functions = new Functions();
        DFSLike DFSLike = new DFSLike();
        MaxCap MaxCap = new MaxCap();
        RandomSearch RandomSearch = new RandomSearch();

        // Generate source sink graph
        /******************************** Generate Graph ***********************************************/

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n**********************************************************");

            System.out.println("Enter the operation You want to : 1 for graphGeneration, 2 for running Ford-Fulkerson");
            int operation = Integer.parseInt(sc.nextLine());

            switch (operation){

                case 1 :
                    System.out.println("Enter the parameters for generating graph");
                    System.out.println("Enter value of n : ");
                    int n = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter value of r : ");
                    double r = Double.parseDouble(sc.nextLine());
                    System.out.println("Enter value of upperCap : ");
                    int upperCap = Integer.parseInt(sc.nextLine());
                    createAndSaveGraph(n,r,upperCap);
                    break;

                case 2 :
                    System.out.println("Enter the parameters of graph to choose appropriate CSV file");
                    System.out.println("Enter value of n : ");
                    int n2 = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter value of r : ");
                    double r2 = Double.parseDouble(sc.nextLine());
                    System.out.println("Enter value of upperCap : ");
                    int upperCap2 = Integer.parseInt(sc.nextLine());
                    readGraphAndCallMax(n2,r2,upperCap2);
            }

        }

    }
}