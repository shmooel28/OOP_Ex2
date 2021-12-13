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

![Ex0 vpd](https://user-images.githubusercontent.com/93682110/145465458-4d55dddc-b1ee-4081-92cf-ed29ae171de9.png)

GUI
--
main screen, with option to load, save, eddit the graph, run algorithm and display.

![WhatsApp Image 2021-12-09 at 22 11 16](https://user-images.githubusercontent.com/93682110/145468814-1eabf2e4-8c35-429e-8321-1eede1f7cdba.jpeg)

algorithm screen, with isConnected, shortestPathDist, shortPath, tsp and center.

![WhatsApp Image 2021-12-09 at 22 10 57](https://user-images.githubusercontent.com/93682110/145468823-82f9bf69-cd5e-4d87-812a-5fcab9648c39.jpeg)

eddit screen, with option to add Node, add Edge, remove Node and remove Edge.

![WhatsApp Image 2021-12-09 at 22 10 26](https://user-images.githubusercontent.com/93682110/145468830-4310c8d6-a3d7-4847-8a15-3b1cd727bef7.jpeg)

display screen with menu to back to the main screen

![WhatsApp Image 2021-12-09 at 22 10 04](https://user-images.githubusercontent.com/93682110/145468838-82cabd25-7076-44e3-8409-a9fdb9f993c8.jpeg)

Result
--
1,000 - 

        load graph: 213ms

        center:3sec 465ms
        
        isConnected: 15ms
        
        shortPathDist: 16 ms
        
        save graph: 235ms

10,000 - 
          
         load graph: 4sc 34ms
         
         center: 7 sc
         
         isConnected: 206 ms
         
         shortPathDist: 116 ms
         
         save graph: 300 ms

100,000 - 

          load graph: 2 min
          
          center:
          
          isConnected:30 sc
          
          shortPathDist:

