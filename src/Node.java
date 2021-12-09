import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.ArrayList;

public class Node implements NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String info = null;
    private int tag;
    private ArrayList<EdgeData> edgeIn;
    private ArrayList<EdgeData> edgeOut;
    public boolean startIter = false;

    public Node(int key){
        this.key = key;
        edgeOut = new ArrayList<>();
        edgeIn = new ArrayList<>();
    }

    public void setEdgesIn(Edge edge){
        this.edgeIn.add(edge);
    }

    public void setEdgesOut(Edge edge){
        this.edgeOut.add(edge);
    }

    public ArrayList<EdgeData> getEdgesIn(){
        return this.edgeIn;
    }

    public ArrayList<EdgeData> getEdgesOut(){
        return this.edgeOut;
    }

    public void removeEdgeIn(Edge edge){
        this.edgeIn.remove(edge);
    }

    public void removeEdgeOut(Edge edge){
        this.edgeOut.remove(edge);
    }
    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        location = p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
    @Override
    public String toString(){
        return "ID: "+this.getKey();
    }
}
