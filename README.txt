
Our main method is in StreetMap class. 
StreetMap is a class to input commands and to scan files. It\'92s also a program to distribute tasks for GUI and Graph. Node and Edge class is in Graph class. We created a priority queue called edgeList to store edges according to their weight and another HashMap called vertexList to store the name and vertices. 

The main difficulty that we met is that when we implementing Dijkstra algorithm, we failed to change the state of visited vertices to true after visiting it at the very beginning. We debugged it for quite a long time. 

Ningyuan Xiong designed and wrote the code of Dijkstra and Graph class. Xinyu Cai wrote the GUI class. We debugged together. 

Let the number of edges be E, the number of nodes be n.
For basic map: The runtime for nodes is O(n).
For Dijkstra, each time, I extra min from priority queue  and update distance. The worst case is to add all the edges to the priority queue and delete until no edge can be processed. O(E)

Extra credit: My map is clear and in good shape and you can resize the window. Basic map is in black lines and it will highlight the line that between the start point and end point in pink color. What\'92s more, we draw two small blue circles on both start point and end point even when two intersections are not connected. Based on these things, we think we deserve the extra credit. 

P.S We checked the test case. The route from start point to end point is correct but the distance is different from the test case. I think this is because our formula is different. The earth radius is different. 
