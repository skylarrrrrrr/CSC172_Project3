import java.util.*;

public class Dijkstra extends Graph{
    Map<String, ArrayList<Node>> adjList;
    Stack<Node> result;
    PriorityQueue<Node> store;
    
    //constructor
    public Dijkstra(){
        adjList = new HashMap<>();
        Comparator<Node> comparator = new NodeComparator();
        store = new PriorityQueue<>(10, comparator);
        result = new Stack<>();
    }

    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if(o1.d < o2.d){
                return -1;
            }
            if(o1.d > o2.d) {
                return 1;
            }
            return 0;
        }
    }

    //create adjacency list 
    public void addAdjList(String v1, String v2){
        ArrayList<Node> temp1 = new ArrayList<>();
        ArrayList<Node> temp2 = new ArrayList<>();

        if(adjList.containsKey(v1)) {
            ArrayList<Node> old = adjList.get(v1);
            temp1.addAll(old);
            temp1.add(vertexList.get(v2));
            adjList.replace(v1, temp1);
        }else if(!adjList.containsKey(v1)){
            temp1.add(vertexList.get(v2));
            adjList.put(v1, temp1);
        }
        if(adjList.containsKey(v2)){
            ArrayList<Node> old = adjList.get(v2);
            temp2.addAll(old);
            temp2.add(vertexList.get(v1));
            adjList.replace(v2, temp2);
        }else if(!adjList.containsKey(v2)){
            temp2.add(vertexList.get(v1));
            adjList.put(v2, temp2);
        }
    }

    //use dijkstra algorithm to find shortest path
    public void stPath(String start, String end){
        vertexList.get(start).d = 0;
        store.add(vertexList.get(start));
        int reachable = 0;
        while(!store.isEmpty()) {
            Node startN = store.poll();
            if(startN.name.equals(end)){
                reachable = 1;
                break;
            }
            startN.visit = true;
            for (Node endN: adjList.get(startN.name)) {
                if(!endN.visit) {
                    store.remove(endN);
                    double w = haversine(startN.latitude, startN.longitude, endN.latitude, endN.longitude);
                    relax(startN, endN, w);
                    store.add(endN);
                }
            }
        }
        if(reachable != 1){
            System.out.println("These two intersections are not connected.");
        }else {
            Node endN = vertexList.get(end);
            System.out.println("The shortest distance to " + endN.name + " is: " + endN.d);
            while (endN != null) {
                if (endN.name.equals(start)) {
                    break;
                }
                result.push(endN);
                addEdge2(endN, endN.parent);
                endN = endN.parent;
            }
            System.out.print(start);
            while (!result.empty()) {
                System.out.print(" --> " + result.pop().name);
            }
            System.out.println();
        }
    }
    public void relax(Node u, Node v, double w){
        if(v.d > u.d + w) {
            v.d = u.d + w;
            v.parent = u;
        }
    }

}
