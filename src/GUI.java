import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame implements ActionListener {
    static JButton button,button1,button2,button3,button4,button5,isConncet,shortPathDist,shortPath,center,tps,menu,enter,addNode,addEdge,removeEdge,removeNode;
    static JFrame frame;
    static Container contetpane;
    JTextField textField,tf1,tf2;
    static GraphAlgo graphAlgo;
    static List<NodeData> nodes;


    public GUI() {
        nodes=new ArrayList<>();
        graphAlgo = new GraphAlgo();
        tf1=new JTextField();
        tf2 = new JTextField();
        textField = new JTextField();
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,50,150,20);
        frame = new JFrame("DirectedWeightedGraph ");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contetpane = frame.getContentPane();
        contetpane.setLayout(null);
        button = new JButton("load");
        button1 = new JButton("save");
        button2 = new JButton("Edit graph");
        button3 = new JButton("run Algorithms");
        button4 = new JButton("Display graph");
        button5 = new JButton("enter");
        isConncet = new JButton("isConnect");
        shortPathDist = new JButton("shortPathDist");
        shortPath = new JButton("shortPath");
        center = new JButton("center");
        tps = new JButton("tps");
        menu = new JButton("back to menu");
        enter = new JButton("enter");
        menuScreen();
        frame.setLayout(null);
    }

    public void menuScreen(){
        contetpane.removeAll();
        contetpane.repaint();
        button.setLocation(0, 0);
        button.setSize(150, 200);
        contetpane.add(button);
        button1.setLocation(150, 0);
        button1.setSize(150, 200);
        contetpane.add(button1);
        button2.setLocation(300, 0);
        button2.setSize(150, 200);
        contetpane.add(button2);
        button3.setLocation(0, 200);
        button3.setSize(150, 200);
        contetpane.add(button3);
        button4.setLocation(150, 200);
        button4.setSize(150, 200);
        contetpane.add(button4);
        button.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        frame.setVisible(true);
    }

    public GUI(GraphAlgo graphAlgo) {
        nodes=new ArrayList<>();
        GUI.graphAlgo = graphAlgo;
        tf1=new JTextField();
        tf2 = new JTextField();
        textField = new JTextField();
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,50,150,20);
        frame = new JFrame("DirectedWeightedGraph ");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contetpane = frame.getContentPane();
        contetpane.setLayout(null);
        button = new JButton("load");
        button1 = new JButton("save");
        button2 = new JButton("Edit graph");
        button3 = new JButton("run Algorithms");
        button4 = new JButton("Display graph");
        button5 = new JButton("enter");
        isConncet = new JButton("isConnect");
        shortPathDist = new JButton("shortPathDist");
        shortPath = new JButton("shortPath");
        center = new JButton("center");
        tps = new JButton("tps");
        menu = new JButton("back to menu");
        enter = new JButton("enter");
        menuScreen();
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    new GUILoad();
                } else if (e.getSource() == button3) {
                    contetpane.removeAll();
                    contetpane.repaint();
                    isConncet.setLocation(0, 0);
                    isConncet.setSize(150, 200);
                    contetpane.add(isConncet);
                    shortPathDist.setLocation(150, 0);
                    shortPathDist.setSize(150, 200);
                    contetpane.add(shortPathDist);
                    shortPath.setLocation(300, 0);
                    shortPath.setSize(150, 200);
                    contetpane.add(shortPath);
                    center.setLocation(0, 200);
                    center.setSize(150, 200);
                    contetpane.add(center);
                    tps.setLocation(150, 200);
                    tps.setSize(150, 200);
                    contetpane.add(tps);
                    isConncet.addActionListener(this);
                    shortPathDist.addActionListener(this);
                    shortPath.addActionListener(this);
                    center.addActionListener(this);
                    tps.addActionListener(this);
                } else if (e.getSource() == isConncet) {
                    boolean flag = graphAlgo.isConnected();
                    contetpane.removeAll();
                    contetpane.repaint();
                    JTextField text = new JTextField();
                    text.setBounds(50, 50, 200, 30);
                    text.setText(Boolean.toString(flag));
                    menu.setLocation(150,200);
                    menu.setSize(150,200);
                    contetpane.add(menu);
                    menu.addActionListener(this);
                    frame.add(text);
                }
                else if(e.getSource()==menu){
                    menuScreen();
                }
                else if (e.getSource()==center){
                    contetpane.removeAll();
                    contetpane.repaint();
                    Node temp = (Node) graphAlgo.center();
                    JTextField text = new JTextField();
                    text.setBounds(50, 50, 200, 30);
                    if(temp!= null)text.setText(temp.toString());
                    menu.setLocation(150,200);
                    menu.setSize(150,200);
                    contetpane.add(menu);
                    menu.addActionListener(this);
                    frame.add(text);
                }
                else if(e.getSource()==tps){
                    new Guitsp();

                }
                else if(e.getSource()==shortPath) new GUIShortpath();
                else if(e.getSource()==shortPathDist) new GUIShortpathDist();
                else if(e.getSource()==button1) new GUISave();
                else if(e.getSource()==button2){
                    addNode = new JButton("add Node");
                    addEdge = new JButton("add Edge");
                    removeEdge = new JButton("remove edge");
                    removeNode = new JButton("remove Node");
                    contetpane.removeAll();
                    contetpane.repaint();
                    addEdge.setLocation(0, 0);
                    addEdge.setSize(150, 200);
                    contetpane.add(addEdge);
                    addNode.setLocation(150, 0);
                    addNode.setSize(150, 200);
                    contetpane.add(addNode);
                    removeNode.setLocation(0, 200);
                    removeNode.setSize(150, 200);
                    removeEdge.setLocation(150, 200);
                    removeEdge.setSize(150, 200);
                    contetpane.add(removeEdge);
                    contetpane.add(removeNode);
                    removeEdge.addActionListener(this);
                    removeNode.addActionListener(this);
                    menu.setLocation(300,150);
                    menu.setSize(150,200);
                    menu.addActionListener(this);
                    contetpane.add(menu);
                    addNode.addActionListener(this);
                    addEdge.addActionListener(this);

                }
                else if(e.getSource()==addNode) new GUIAddNode();
                else if(e.getSource()==addEdge) new GUIAddEdge();
                else if(e.getSource()==removeEdge)new GUIRemoveEdge();
                else if(e.getSource()==removeNode)new GUIRemoveNode();
                else if(e.getSource()==button4) new GUIDisplay(true);

            }
    public GUI(DirectedWeightedGraph graphAlgo){
        this.graphAlgo = (GraphAlgo) graphAlgo;
        new GUI();
    }
    public void setGraphAlgo(GraphAlgo g){
        this.graphAlgo = g;
    }
    public GraphAlgo getGraphAlgo(){
        return this.graphAlgo;
    }
    public static void main(String[] arg){
        new GUI();
    }
}
