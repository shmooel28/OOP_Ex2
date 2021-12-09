import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIAddNode extends JFrame implements ActionListener {
    JTextField tf1,tf2,tf3,tf4;
    JButton enter,back;
    int key = 0;
    double x=0,y=0,z=0;
    public GUIAddNode(){
        GUI.contetpane.removeAll();
        GUI.contetpane.repaint();
        tf1 = new JTextField("enter key");
        tf2 = new JTextField("enter x");
        tf3 = new JTextField("enter y");
        tf4 = new JTextField("enter z");
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,90,150,20);
        tf3.setBounds(50,130,150,20);
        tf4.setBounds(50,170,150,20);
        enter = new JButton("enter");
        GUI.frame.add(tf1);
        GUI.frame.add(tf2);
        GUI.frame.add(tf3);
        GUI.frame.add(tf4);
        enter.setSize(150,200);
        enter.setLocation(150,200);
        GUI.contetpane.add(enter);
        enter.addActionListener(this);
        back = new JButton("back to menu");
        back.setSize(150,200);
        back.setLocation(150,200);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        String s3 = tf3.getText();
        String s4 = tf4.getText();
        try {
            key = Integer.parseInt(s1);
            x = Double.parseDouble(s2);
            y = Double.parseDouble(s3);
            z = Double.parseDouble(s4);
        } catch (NumberFormatException numberFormatException) {

        }
        NodeData temp = new Node(key);
        GeoLocation location = new Location(x,y,z);
        temp.setLocation(location);
        GUI.graphAlgo.getGraph().addNode(temp);
        if(e.getSource()==enter){
            tf2.setText("great");
            GUI.contetpane.removeAll();
            GUI.contetpane.repaint();
            GUI.frame.add(tf2);
            GUI.contetpane.add(back);
            back.addActionListener(this);
        }
        else if(e.getSource()==back){
            GUI.frame.setVisible(false);
            GUI.frame.dispose();
            new GUI(GUI.graphAlgo);
        }
    }
}
