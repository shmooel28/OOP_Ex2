import api.DirectedWeightedGraphAlgorithms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class bigGraphTest {
    @Test
    void Nodes_1000_Test(){
        DirectedWeightedGraphAlgorithms graphAlgorithms= new GraphAlgo();
        graphAlgorithms.load("1000Nodes.json");
        int center = graphAlgorithms.center().getKey();
        graphAlgorithms.shortestPathDist(0,16);
        boolean flag = graphAlgorithms.isConnected();
        Assertions.assertTrue(flag);
    }

    @Test
    void Nodes_10_000_Test(){
        DirectedWeightedGraphAlgorithms graphAlgorithms= new GraphAlgo();
        graphAlgorithms.load("10000Nodes.json");
        //int center = graphAlgorithms.center().getKey();
        boolean flag = graphAlgorithms.isConnected();
        Assertions.assertTrue(flag);

    }
    @Test
    void Nodes_100_000_Test(){
        DirectedWeightedGraphAlgorithms graphAlgorithms= new GraphAlgo();
        graphAlgorithms.load("data/100000Nodes.json");
        //int center = graphAlgorithms.center().getKey();
        boolean flag = graphAlgorithms.isConnected();
        Assertions.assertTrue(flag);

    }
}

