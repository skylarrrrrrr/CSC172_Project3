import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GUI extends JFrame {

    public GUI(Graph graph){
        setTitle("Map");
        setSize(1000,1000);
        setVisible(true);
        Canvas canvas = new Canvas(graph);
        add(canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GUI(Graph graph, Graph Direction, String start, String end){
        setTitle("Map");
        setSize(1000,1000);
        setVisible(true);
        Canvas canvas = new Canvas(graph, Direction, start, end);
        add(canvas);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class Canvas extends JComponent {
        Graph graph;
        Graph direction;
        String start, end;

        public Canvas(Graph graph){
            this.graph = graph;
        }

        public Canvas(Graph graph, Graph direction, String start, String end){
            this.graph = graph;
            this.direction = direction;
            this.start = start;
            this.end = end;
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            //Get extreme coordinates:
            double[] coordinates = findCoordinates(graph);
            double upMost = coordinates[0];
            double leftMost = coordinates[2];

            //Get the distance on x-coordinate and y-coordinate:
            double y = coordinates[0] - coordinates[1];
            double x = coordinates[2] - coordinates[3];

            //Get the window's size
            double width = getWidth() - 25;
            double height = getHeight() - 25;

            Graphics2D g2 = (Graphics2D) g; // use Graphics2D so we can draw line with double length
            for (Edge edge : graph.edgeList) {
                double y1 = (upMost - edge.v1.latitude) / y * height;
                double x1 = (leftMost - edge.v1.longitude) / x * width;
                double y2 = (upMost - edge.v2.latitude) / y * height;
                double x2 = (leftMost - edge.v2.longitude) / x * width;
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }

            if(direction != null){
                g2.setColor(Color.MAGENTA);
                g2.setStroke(new BasicStroke(5));
                for (Edge edge : direction.edgeList) {
                    double y1 = (upMost - edge.v1.latitude) / y * height;
                    double x1 = (leftMost - edge.v1.longitude) / x * width;
                    double y2 = (upMost - edge.v2.latitude) / y * height;
                    double x2 = (leftMost - edge.v2.longitude) / x * width;
                    g2.draw(new Line2D.Double(x1, y1, x2, y2));
                }
                //draw the circle on start point and end point
                g2.setColor(Color.BLUE);
                double x1 = (leftMost - direction.vertexList.get(start).longitude) / x * width;
                double y1 = (upMost - direction.vertexList.get(start).latitude) / y * height;
                double y2 = (upMost - direction.vertexList.get(end).latitude) / y * height;
                double x2 = (leftMost - direction.vertexList.get(end).longitude) / x * width;
                Ellipse2D.Double shape=new Ellipse2D.Double(x1 - 3.5, y1 - 3.5,7,7);
                g2.fill(shape);
                Ellipse2D.Double shape2=new Ellipse2D.Double(x2 - 3.5, y2 - 3.5,7,7);
                g2.fill(shape2);

            }

        }

        private double[] findCoordinates(Graph graph) {
            // give initial value (or max/min value) for variable upMost, downMost, leftMost, and rightMost.
            double upMost = Double.NEGATIVE_INFINITY;
            double downMost = Double.POSITIVE_INFINITY;
            double leftMost = Double.POSITIVE_INFINITY;
            double rightMost = Double.NEGATIVE_INFINITY;

            //use an array to return the result:
            double[] coordinates = new double[4];

            //Compare to get the extreme coordinates:
            for (String key : graph.vertexList.keySet()) {
                Node temp = graph.vertexList.get(key);
                if (temp.latitude > upMost) {
                    upMost = temp.latitude;
                }
                if (temp.latitude < downMost) {
                    downMost = temp.latitude;
                }
                if (temp.longitude < leftMost) {
                    leftMost = temp.longitude;
                }
                if (temp.longitude > rightMost) {
                    rightMost = temp.longitude;
                }
            }
            coordinates[0] = upMost;
            coordinates[1] = downMost;
            coordinates[2] = leftMost;
            coordinates[3] = rightMost;

            return coordinates;
        }

    }

}
