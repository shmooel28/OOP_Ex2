OOP Assignment 2
--------
The mission was to write a Data Structure and algorithm on graph(directed and Weighted).

About the mission:
--
Graph is very important tool for programmer, graph can use in many problem such as Social Network, and Roads.
you can see also in https://en.wikipedia.org/wiki/Graph_(discrete_mathematics).

The Desing Class
--
First i creat an object of Node, Location and object of Edge.
the node object have id and possition,the possition is an Location object, and i also build list of edges that came in the node and another list of edges that came out from the node.
the edge object have sorce, dest, and wight.
Than i creat a class of Graph that creat a Graph and conntect the Nodes with the Edges.
the class of the graph contain map of nodes and map of edges, and function such as remove node/edge, add node/edge.
After this i creat a class for the algorithm and at the end i build  GUI for visual.
the class contain 5 main algorithm:
1. isConnected.

2. shortestPathDist.

3.shortestPath.

4. center.

5. tsp.

Algorithms
--
1. isConnected:
i take a random node and check if i can get from him to every another node, and also is the opposite direction,
if it visit in every node in both way, the meaning is that you can go from every node to every other node so it is connected.

2. shortestPathDist: i use Floyd–Warshall algorithm.
the idea behind the algorithm, if the from point a to point b, is faster to go from a to point c and than to point b, replace the path from a to b in a-c-b.
you can see also https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm

3.shortestPath:
the idea behind the algorithm, if the from point a to point b, is faster to go from a to point c and than to point b, replace the path from a to b in a-c-b.
evrey time the path change i update the path list, and at the end return the list.
you can see also https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm

4. center:
after i have the shortest path dist it was triviali, i just check the farest node with the smallest width and return that node
https://en.wikipedia.org/wiki/Graph_center

5. tsp:
i used in greedy algorithm, every time look for the short path dist.

UML
--

GUI
--



