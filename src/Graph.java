import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Graph implements DirectedWeightedGraph {
    private HashMap<Integer,Node> nodes;
    private HashMap<Vector<Integer>,EdgeData> edges;
    private int count;
    private boolean flagNodeIter = false;
    private boolean flagEdgeIter = false;


    public Graph() {
        nodes =  new HashMap<>();
        edges = new HashMap<>();
        count = 0;
    }
    public Graph(HashMap nodes, HashMap edges, int mc) {
        this.nodes =  nodes;
        this.edges = edges;
        count = mc;
    }

    @Override
    public NodeData getNode(int key) {
        if (nodes.containsKey(key))
            return (NodeData) nodes.get(key);
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Vector <Integer> temp = new Vector<>();
        temp.add(src);
        temp.add(dest);
        if (edges.containsKey(temp))
            return edges.get(temp);
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (!flagNodeIter){
            nodes.put(n.getKey(), (Node) n);
            count++;
        }
        else throw new RuntimeException("the graph change after an iterator construct");
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(!flagEdgeIter && !nodes.get(src).startIter) {
            Vector <Integer> temp = new Vector<>();
            temp.add(src);
            temp.add(dest);
            Edge edge = new Edge(src, dest, w);
            if (edges.containsKey(temp))
                removeEdge(src, dest);
            edges.put(temp, edge);
            nodes.get(src).setEdgesOut(edge);
            nodes.get(dest).setEdgesIn(edge);
            count++;
        }
        else throw new RuntimeException("the graph change after an iterator construct");
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<Node> n = nodes.values().iterator();
        HashMap<Integer,NodeData> nodeIter = new HashMap();
        while ((n.hasNext())){
            Node temp = n.next();
            nodeIter.put(temp.getKey(),temp);
        }
        flagNodeIter = true;
        return nodeIter.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Iterator<EdgeData> e = edges.values().iterator();
        HashMap<Vector<Integer>,EdgeData> edgeIter = new HashMap<>();
        while (e.hasNext()) {
            Edge temp = (Edge) e.next();
            Vector<Integer> t = new Vector<>();
            t.add(temp.getSrc());
            t.add(temp.getDest());
            edgeIter.put(t, temp);
        }
        flagEdgeIter = true;
        return edgeIter.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Iterator<EdgeData> e = nodes.get(node_id).getEdgesOut().iterator();
        HashMap<Vector<Integer>,EdgeData> edgeIter = new HashMap<>();
        while (e.hasNext()) {
            Edge temp = (Edge) e.next();
            Vector<Integer> t = new Vector<>();
            t.add(temp.getSrc());
            t.add(temp.getDest());
            edgeIter.put(t, temp);
        }
        nodes.get(node_id).startIter = true;
        return edgeIter.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if (!nodes.containsKey(key))
            return null;
        Node node = (Node) getNode(key);
        int count = nodes.get(key).getEdgesIn().size();
        for (int i = count-1; i >= 0; i--) {
            int src = nodes.get(key).getEdgesIn().get(i).getSrc();
            nodes.get(src).removeEdgeOut((Edge) nodes.get(key).getEdgesIn().get(i));
            removeEdge(src,key);
        }
        count = nodes.get(key).getEdgesOut().size();
        for (int i = count-1; i >= 0; i--) {
            int dest = nodes.get(key).getEdgesOut().get(i).getDest();
            nodes.get(dest).removeEdgeIn((Edge) nodes.get(key).getEdgesOut().get(i));
            removeEdge(key,dest);

        }
        nodes.remove(key);
        count++;
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Vector<Integer> temp = new Vector<>();
        temp.add(src);
        temp.add(dest);
        Edge edge = (Edge) getEdge(src, dest);
        nodes.get(src).removeEdgeOut(edge);
        nodes.get(dest).removeEdgeIn(edge);
        nodes.get(dest).removeEdgeOut(edge);
        nodes.get(src).removeEdgeIn(edge);
        edges.remove(temp);
        count++;
        return edge;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return count;
    }
}
