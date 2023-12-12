# Project Description 

This project implements multiple algorithms to find an augmenting path for the Ford-Fulkerson maximum flow algorithm and study their performances on random source-sink networks. The algorithms used here for finding augmenting paths are mostly different variants of Dijkastra’s Algorithm.
The project involves creating a random source-sink graph generator and implementing four augmenting path algorithms.

## Augmenting Path Algorithms 
1. [Shortest Augmenting Path (SAP)](#For this a modified version of Dijkstra algorithm is used which implements the algorithm using unit capacity for each edge, effectively the same as running BFS.)

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

