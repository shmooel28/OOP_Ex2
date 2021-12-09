import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class algoTest {
    DirectedWeightedGraphAlgorithms graph;
    public static void main(String[] args) {
    }
    @BeforeEach
    void init(){
        graph = new GraphAlgo();
        graph.load("data/G1.json");
    }
    @Test
    void testGetEdge(){
        EdgeData edgeData = new Edge(0,16,1.3118716362419698);
        EdgeData expected = graph.getGraph().getEdge(0,16);
        Assertions.assertEquals(expected.getDest(),edgeData.getDest());
    }
    @Test
    void testRemoveNode(){
        graph.getGraph().removeNode(1);
        Assertions.assertEquals(graph.getGraph().getNode(1),null);
    }
    @Test
    void testRemoveNodeEdge(){
        graph.getGraph().removeNode(1);
        Assertions.assertEquals(graph.getGraph().getEdge(1,0),null);
    }
    @Test
    void testRemoveEdge(){
        graph.getGraph().removeEdge(0,16);
        Assertions.assertEquals(null,graph.getGraph().getEdge(0,16));
    }
    @Test
    void testGetNode(){
        int expectd = graph.getGraph().getNode(0).getKey();
        Assertions.assertEquals(0,expectd);
    }
    @Test
    void testCenter(){
        int center = graph.center().getKey();
        Assertions.assertEquals(8,center);

    }
    @Test
    void testShortPathDist(){
        double expected = graph.shortestPathDist(0,16);
        Assertions.assertEquals(1.3118716362419698,expected);
    }
    @Test
    void testShortPath(){
        List<NodeData> expected= graph.shortestPath(0,16);
        NodeData nodeData = graph.getGraph().getNode(16);
        List<NodeData> actual = new ArrayList<>();
        actual.add(nodeData);
        Assertions.assertEquals(actual,expected);
    }
    @Test
    void testIsConnect(){
        boolean expected = graph.isConnected();
        Assertions.assertEquals(true,expected);
    }
    }


