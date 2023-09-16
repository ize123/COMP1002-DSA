import java.util.*;

public class listtest {
    public static void main(String[] args)
    {
        DSAGraph graph = new DSAGraph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 4, null, null);
    }
}
