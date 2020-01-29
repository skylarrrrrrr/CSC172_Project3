import java.util.*;

public class Graph {
    PriorityQueue<Edge> edgeList;
    Map<String, Node> vertexList;

    public Graph(){
        edgeList = new PriorityQueue<>();
        vertexList = new HashMap<>();
    }

    public void addEdge(String name, String v1, String v2){
        Node V1 = vertexList.get(v1);
        Node V2 = vertexList.get(v2);
        double w = haversine(V1.latitude, V1.longitude, V2.latitude, V2.longitude);
        Edge edge = new Edge(name, V1, V2, w);
        edgeList.add(edge);
    }

    public void addEdge2(Node v1, Node v2){
        Edge edge = new Edge(v1, v2);
        edgeList.add(edge);
    }

    public void addVertex(String name, double x, double y) {
        Node vertex = new Node(name, x, y);
        vertexList.put(name, vertex);
    }

    /**
     * This method is used to get the distance between two points on the map
     * Taken from: https://rosettacode.org/wiki/Haversine_formula#Java
     *
     * @param lat1 point one's latitude
     * @param lon1 point one's longitude
     * @param lat2 point two's latitude
     * @param lon2 point two's longitude
     * @return the distance between two points
     */
    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return 6356.752 * c;
    }
}


class Node{
    String name;
    double latitude, longitude;
    double d = Double.MAX_VALUE;
    boolean visit;
    Node parent;

    public Node(String name, double latitude, double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        visit =false;
    }

}

class Edge implements Comparable<Edge>{
    Node v1, v2;
    String name;
    double w;

    public Edge(Node v1, Node v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public Edge(String road, Node v1, Node v2, double w) {
        name = road;
        this.w = w;
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public int compareTo(Edge o) {
        return (int)(this.w - o.w);
    }
}
