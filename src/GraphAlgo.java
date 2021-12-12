import api.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private double[][] dist;
    private ArrayList<NodeData>[][]short_path;
    private boolean floidFlag = false;


    @Override
    public void init(DirectedWeightedGraph g) {
        graph = g;
        dist = new double[graph.nodeSize()][graph.nodeSize()];
        short_path=new ArrayList[graph.nodeSize()][graph.nodeSize()];
        for (int i = 0; i < short_path.length; i++) {
            for (int j = 0; j < short_path.length; j++) {
                short_path[i][j] = new ArrayList<>();
            }
        }
        Graph temp = (Graph) graph;
        temp.setFlagEdgeIter(false);
        temp.setFlagEdgeIter(false);
        graph = temp;
    }
    public void floid(){
        Iterator<EdgeData> edgeDageIterator = graph.edgeIter();
        while (edgeDageIterator.hasNext()){
            EdgeData temp = edgeDageIterator.next();
            dist[temp.getSrc()][temp.getDest()] = temp.getWeight();
            short_path[temp.getSrc()][temp.getDest()].add(graph.getNode(temp.getDest()));
        }
        for (int i = 0; i < graph.nodeSize() ; i++) {
            for (int j = 0; j < graph.nodeSize() ; j++) {
                if(i==j){
                    dist[i][j] = 0;
                }
                else if(dist[i][j]==0)dist[i][j] = 2000000;
            }
        }
        for (int k = 0; k < graph.nodeSize(); k++)
        {
            for (int l = 0; l < graph.nodeSize(); l++)
            {
                for (int g = 0; g < graph.nodeSize(); g++)
                {
                    if (dist[l][g] > dist[l][k]+dist[k][g]) {
                        dist[l][g] = dist[l][k] + dist[k][g];
                        if (short_path[l][k].size() > 0) short_path[l][g].clear();
                        Iterator<NodeData> nodeDataIterator = short_path[l][k].iterator();
                        while (nodeDataIterator.hasNext()) {
                            short_path[l][g].add(nodeDataIterator.next());
                        }
                        nodeDataIterator = short_path[k][g].iterator();
                        while (nodeDataIterator.hasNext()) {
                            short_path[l][g].add(nodeDataIterator.next());
                        }
                    }
                }
            }
        }
    }
    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new Graph();
        g = graph;
        return g;
    }
    public void dfs(Graph g ,NodeData node){
        node.setTag(1);
        //Iterator<NodeData> nodeDataIterator = graph.nodeIter();
        Iterator<EdgeData> edgeDataIterator = g.edgeIter(node.getKey());
        while (edgeDataIterator.hasNext()){
            NodeData temp = g.getNode(edgeDataIterator.next().getDest());
            if (temp!= null && temp.getTag() == 0)
                dfs(g,temp);
        }
    }
    public HashMap reversEdge(){
        HashMap <int[],Edge> reversEdg = new HashMap<>();
        Iterator<EdgeData> edgeDataIterator = graph.edgeIter();
        while (edgeDataIterator.hasNext()){
            EdgeData edge = edgeDataIterator.next();
            int []temp = {edge.getDest(),edge.getSrc()};
            reversEdg.put(temp, (Edge) edge);
        }
        return reversEdg;
    }
    @Override
    public boolean isConnected() {
        Iterator<NodeData> nodeDataIterator = graph.nodeIter();
        HashMap<Integer, Node> newNode = new HashMap<>();
        NodeData n = nodeDataIterator.next();
        while (nodeDataIterator.hasNext()) {
            newNode.put(n.getKey(), (Node) n);
            n.setTag(0);
            n = nodeDataIterator.next();
        }
        nodeDataIterator = graph.nodeIter();
        dfs((Graph) graph, nodeDataIterator.next());
        while (nodeDataIterator.hasNext())
            if (nodeDataIterator.next().getTag() == 0)
                return false;
        HashMap<int[], Edge> reversEdg = reversEdge();
        Graph tempGraph = new Graph(newNode, reversEdg, graph.getMC());
        Iterator<NodeData> nodeDataIterator1 = tempGraph.nodeIter();
        while (nodeDataIterator1.hasNext()){
            n.setTag(0);
            n = nodeDataIterator1.next();
        }
        nodeDataIterator1 = tempGraph.nodeIter();
        dfs(tempGraph,nodeDataIterator1.next());
        while (nodeDataIterator.hasNext())
            if(nodeDataIterator.next().getTag()==0)
                return false;
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (floidFlag == false) {
            floid();
            floidFlag = true;
        }
        if (!isConnected())
            return -1;
        if (dist[src][dest] == 2000000) return -1;
        return dist[src][dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (floidFlag == false) {
            floid();
            floidFlag = true;
        }

        if (!isConnected())
            return null;
        return short_path[src][dest];
    }

    @Override
    public NodeData center() {
        if (!isConnected()) return null;
        if (floidFlag == false) {
            floid();
            floidFlag = true;
        }
        double dis[] = new double[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {
            dis[i] = 0;
            for (int j = 0; j < graph.nodeSize() ; j++) {
                if(i!=j && dist[i][j]>dis[i]) dis[i] = dist[i][j];
            }
        }
        int cen = 0;
        for (int i = 0; i < graph.nodeSize() ; i++) {
            if (dis[i] < dis[cen]) cen = i;
        }
        return graph.getNode(cen);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(!isConnected()||cities.isEmpty()) return null;
        int sum1 = 0, sum2 = 0;
        ArrayList<NodeData> ans = new ArrayList<>();
        ArrayList<NodeData> ans2 = new ArrayList<>();
        ArrayList<NodeData> revers_cities = new ArrayList<>();
        revers_cities = (ArrayList<NodeData>) revers_list(cities);
        ans.add(cities.get(0));
        ans2.add(revers_cities.get(0));
        cities.remove(0);
        revers_cities.remove(0);
        while(!cities.isEmpty()){
            int src = ans.get(ans.size()-1).getKey();
            ans.addAll(shortestPath(src,cities.get(0).getKey()));
            sum1 += shortestPathDist(src,cities.get(0).getKey());
            for (int i = 0; i < ans.size(); i++) {
                cities.remove(ans.get(i));
            }
        }
        while(!revers_cities.isEmpty()){
            int src = ans2.get(ans2.size()-1).getKey();
            ans2.addAll(shortestPath(src,revers_cities.get(0).getKey()));
            sum2 += shortestPathDist(src,revers_cities.get(0).getKey());
            for (int i = 0; i < ans2.size(); i++) {
                revers_cities.remove(ans2.get(i));
            }
        }
        if (sum1 > sum2) ans = ans2;
        return ans;
    }

    private List<NodeData> revers_list(List<NodeData> cities) {
        ArrayList<NodeData> temp = new ArrayList<>();
        for (int i = cities.size()-1; i >= 0 ; i--) {
            temp.add(cities.get(i));
        }
        return temp;
    }

    @Override
    public boolean save(String file) {
        Gson gson = new Gson();
        JsonArray edg = new JsonArray();
        JsonArray nod = new JsonArray();
        Iterator<NodeData> nodeDataIterator = this.getGraph().nodeIter();
        Iterator<EdgeData> edgeDataIterator = this.getGraph().edgeIter();

        while (edgeDataIterator.hasNext()) {
            EdgeData edge = edgeDataIterator.next();
            Map<String, Object> e = new HashMap<>();
            e.put("src", ((edge.getSrc())));
            e.put("w", (edge.getWeight()));
            e.put("dest", ((edge.getDest())));
            Type gsonType = new TypeToken<Map>(){}.getType();
            edg.add(gson.toJsonTree(e,gsonType).getAsJsonObject());

        }
        while (nodeDataIterator.hasNext()){
            NodeData temp =nodeDataIterator.next();
            Map<String,Object> n = new HashMap<>();
            String str = temp.getLocation().x()+","+temp.getLocation().y()+","+temp.getLocation().z();
            n.put("pos",str);
            n.put("id", (temp.getKey()));
            Type gsonType = new TypeToken<Map>(){}.getType();
            nod.add(gson.toJsonTree(n,gsonType));
        }
        Map<String,JsonElement> union = new HashMap<>();
        union.put("Edges",edg);
        union.put("Nodes",nod);
        try {
            Writer writer = new FileWriter(file);
            Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
            gson1.toJson(union, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        try {
            Graph g = new Graph();
            Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonElement obj = jsonParser.parse(new FileReader(file));
            JsonObject object = obj.getAsJsonObject();
            JsonArray nodes = object.getAsJsonArray("Nodes");
            JsonArray edges = object.getAsJsonArray("Edges");
            for (JsonElement n:nodes) {
                JsonObject temp = n.getAsJsonObject();
                Node node_temp = new Node(Integer.parseInt(String.valueOf(temp.get("id"))));
                String s = String.valueOf(temp.get("pos"));
                s = s.substring(1,s.length()-1);
                String[] str =s.split(",");
                GeoLocation location = new Location(Double.parseDouble(str[0]),Double.parseDouble(str[1]),Double.parseDouble(str[2]));
                node_temp.setLocation(location);
                g.addNode(node_temp);
            }
            for (JsonElement n:edges) {
                JsonObject temp = n.getAsJsonObject();
                int src = Integer.parseInt(String.valueOf(temp.get("src")));
                double w = Double.parseDouble(String.valueOf(temp.get("w")));
                int dest = Integer.parseInt(String.valueOf(temp.get("dest")));
                g.connect(src,dest,w);
            }
            init(g);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
