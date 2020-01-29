{\rtf1\ansi\ansicpg1252\cocoartf1561\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset0 HelveticaNeue;}
{\colortbl;\red255\green255\blue255;\red27\green31\blue34;\red255\green255\blue255;}
{\*\expandedcolortbl;;\cssrgb\c14118\c16078\c18039;\cssrgb\c100000\c100000\c100000;}
\margl1440\margr1440\vieww16860\viewh11020\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs26 \cf0 Ningyuan Xiong\
NetID: nxiong\
Email: nxiong@u.rochester.edu\
Partner: Xinyu Cai\
Lab section: MW 4:50-6:05\
\
Our main method is in StreetMap class. \cf2 \cb3 \expnd0\expndtw0\kerning0
StreetMap is a class to input commands and to scan files. It\'92s also a program to distribute tasks for GUI and Graph. Node and Edge class is in Graph class. We created a priority queue called edgeList to store edges according to their weight and another HashMap called vertexList to store the name and vertices. \
\
The main difficulty that we met is that when we implementing Dijkstra algorithm, we failed to change the state of visited vertices to true after visiting it at the very beginning. We debugged it for quite a long time. \
\
Ningyuan Xiong designed and wrote the code of Dijkstra and Graph class. Xinyu Cai wrote the GUI class. We debugged together. \
\
\pard\pardeftab720\sl360\sa320\partightenfactor0
\cf2 Let the number of edges be E, the number of nodes be n.\
For basic map: The runtime for nodes is O(n).\
\pard\pardeftab720\sl360\partightenfactor0
\cf2 For Dijkstra, each time, I extra min from priority queue  and update distance. The worst case is to add all the edges to the priority queue and delete until no edge can be processed. O(E)\
\
Extra credit: My map is clear and in good shape and you can resize the window. Basic map is in black lines and it will highlight the line that between the start point and end point in pink color. What\'92s more, we draw two small blue circles on both start point and end point even when two intersections are not connected. Based on these things, we think we deserve the extra credit. \
\
P.S We checked the test case. The route from start point to end point is correct but the distance is different from the test case. I think this is because our formula is different. The earth radius is different. 
\f1\fs32 \
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0
\cf2   }