# Project Description 

This project implements multiple algorithms to find an augmenting path for the Ford-Fulkerson maximum flow algorithm and study their performances on random source-sink networks. The algorithms used here for finding augmenting paths are mostly different variants of Dijkastra’s Algorithm.
The project involves creating a random source-sink graph generator and implementing four augmenting path algorithms.


## Augmenting Path Algorithms 
1. Shortest Augmenting Path (SAP) :For this a modified version of Dijkstra algorithm is used which implements the algorithm using unit capacity for each edge, effectively the same as running BFS.
2. DFS-like : In this algorithm a depth-first search version of Dijkstra algorithm is used which explores the nodes in depth-first manner in order to find the augmenting path.
3. Maximum Capacity (MaxCap): Augmenting paths are calculated with the maximum capacity for this version of Dijkstra. The capacity of critical edge (cf(p) of augmenting path p) will be the value t.d when this algorithm converges.
4. Random : This modification is similar to the one for DFS-like except that a random value is used in order to relax any edge associated with a given vertex. 


## Folder Structure

* [FordFulkerson/](./src/FordFulkerson)
  * [Graph/](./src/FordFulkerson/Graph)
    * [Edge.java](./src/FordFulkerson/Graph/Edge.java)
    * [Graph.java](./src/FordFulkerson/Graph/Graph.java)
    * [Result.java](./src/FordFulkerson/Graph/Result.java)
    * [Vertex.java](./src/FordFulkerson/Graph/Vertex.java)
  * [Utils/](./src/FordFulkerson/Utils)
    * [DFSLike.java](./src/FordFulkerson/Utils/DFSLike.java)
    * [Functions.java](./src/FordFulkerson/Utils/Functions.java)
    * [MaxCap.java](./src/FordFulkerson/Utils/MaxCap.java)
    * [RandomSearch.java](./src/FordFulkerson/Utils/RandomSearch.java)
* [Main.java](./src/Main.java)


## Compile & Run

1. You can specify the path where the graphs are stored
```
        graph.saveToCSV("graph_"+n+"_"+r+"_"+upperCap+".csv", graph.graph, source, sink, bfsLength);
        graph.graph = functions.readGraphFromCSV("graph_"+n+"_"+r+"_"+upperCap+".csv", graph);
```
3. To run this program we need to execute the Main.java file, this will start the command line execution of program. The first step is to choose wether you want to generate     a graph or run the Ford-Fulkerson algorithm on a already generated graph.
   * Enter 1 for Graph Generation and 2 for executing Ford-Fulkerson.
   * For Graph Generation : Enter the graph parameters n, r, upperCap and a CSV file will be generated where ASCII encodings of source-sink network is stored.
   * Name of this file will be - <span style="color:green;">graph_n_r_upperCap.csv</span>
4. For Simulation of program(ford-Fulkerson) from already created CSV file, the graph_n_r_upperCap.csv file should be present beforehand.
   * After choosing the appropriate parameters n, r, upperCap and then choose the augmenting path algorithm for execution.
   * Enter 1 for SAP, 2 for DFS-Like, 3 for MaxCap and 4 for Random.
5. The output will be a object of Result class which contains the following 4 parameters :-
   * Paths : the number of augmenting paths required until Ford-Fulkerson completes.
   * Mean length (ML) : average length (i.e., number of edges) of the augmenting paths.
   * Mean proportional length (MPL) : the average length of the augmenting path as a fraction of the longest acyclic path from source to sink.
   * Total edges : the total number of edges in the graph.

## Sample Output

<img width="617" alt="outputSS" src="https://github.com/vikaschoudhary007/FordFulkerson/assets/52380490/72d1794e-b26b-4d90-9080-42c712308d9c">

# Note - I have created the project in IntelliJ IDE. If possible run the project in IntelliJ IDE so that the compile and run process will be easy.
