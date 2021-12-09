import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Guitsp extends JFrame implements ActionListener {
    JTextField tf1,tf2;
    JButton enter , back;
    public Guitsp(){
        GUI.contetpane.removeAll();
        GUI.contetpane.repaint();
        tf1 = new JTextField("enter list of node with space");
        tf2 = new JTextField();
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,50,500,20);
        enter = new JButton("enter");
        back = new JButton("back to menu");
        back.setSize(150,200);
        back.setLocation(150,200);
        GUI.frame.add(tf1);
        enter.setSize(150,200);
        enter.setLocation(150,200);
        GUI.contetpane.add(enter);
        enter.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = tf1.getText();
        String[] list = filename.split("\\s");
        for (int i = 0; i < list.length ; i++) {
            GUI.nodes.add(GUI.graphAlgo.getGraph().getNode(Integer.parseInt(list[i])));
        }
        if(e.getSource()==enter){
            List<NodeData> t;
            t = GUI.graphAlgo.tsp(GUI.nodes);
            String s="";
            for (int i = 0; i < t.size() ; i++) {
                s += " "+t.get(i);
            }
            tf2.setText(s);
            GUI.contetpane.removeAll();
            GUI.contetpane.repaint();
            GUI.frame.add(tf2);
            GUI.contetpane.add(back);
            back.addActionListener(this);
        }
        else if(e.getSource()==back){
            new GUI(GUI.graphAlgo);
        }
    }
}
