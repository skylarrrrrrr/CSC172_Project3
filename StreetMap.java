import java.io.*;
import java.util.ArrayList;

public class StreetMap {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        Dijkstra dijkstra = new Dijkstra();

        ArrayList<String> command = new ArrayList<>();
        for(String temp:args){
            command.add(temp);
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line = null;
        while ((line = br.readLine())!=null) {
            String[] part = line.split("\\s+" );
            if (part[0].equals("i")) {
                graph.addVertex(part[1], Double.parseDouble(part[2]), Double.parseDouble(part[3]));
            } else if (part[0].equals("r")) {
                graph.addEdge(part[1], part[2], part[3]);
            }
        }

        if(command.contains("--directions")){
            if(command.contains("--show")){
                command.remove("--show");
            }
            command.remove("--directions");

            if(command.get(1) == null){
                System.out.println("The two intersections are not connected.");
            }

            String start = command.get(1);
            String end = command.get(2);

            if(!graph.vertexList.containsKey(start) || !graph.vertexList.containsKey(end)){
                System.out.println("Start point or end point doesn't exist.");
            }else {
                BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
                String line2 = null;
                while ((line2 = br2.readLine()) != null) {
                    String[] part2 = line2.split("\\s+");
                    if (part2[0].equals("i")) {
                        dijkstra.addVertex(part2[1], Double.parseDouble(part2[2]), Double.parseDouble(part2[3]));
                    } else if (part2[0].equals("r")) {
                        dijkstra.addAdjList(part2[2], part2[3]);
                    }
                }
                dijkstra.stPath(start, end);
                GUI g = new GUI(graph, dijkstra, start, end);
            }
        }else{
            GUI g = new GUI(graph);
        }


    }
}
