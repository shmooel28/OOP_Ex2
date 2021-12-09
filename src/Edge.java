import api.EdgeData;

public class Edge implements EdgeData {
    private int src;
    private int dest;
    private double weight;
    private String info = null;
    private int tag = 0;

    public Edge(Node start, Node end, double weight){
        this.src = start.getKey();
        this.dest = end.getKey();
        this.weight = weight;
    }
    public Edge(int start, int end, double weight){
        this.src = start;
        this.dest = end;
        this.weight = weight;
    }
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
        return this.src+","+this.dest;
    }
}
