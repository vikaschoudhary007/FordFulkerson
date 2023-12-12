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
