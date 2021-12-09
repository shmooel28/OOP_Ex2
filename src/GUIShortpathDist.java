import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIShortpathDist extends JFrame implements ActionListener {
    JTextField tf1,tf2;
    JButton enter,back;
    int src = 0, dest = 0;
    public GUIShortpathDist(){
        GUI.contetpane.removeAll();
        GUI.contetpane.repaint();
        tf1 = new JTextField("enter src");
        tf2 = new JTextField("enter dest");
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,90,150,20);
        enter = new JButton("enter");
        GUI.frame.add(tf1);
        GUI.frame.add(tf2);
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
        try {
            src = Integer.parseInt(s1);
            dest = Integer.parseInt(s2);
        } catch (NumberFormatException numberFormatException) {
        }
        double dist = GUI.graphAlgo.shortestPathDist(src,dest);
        if(e.getSource()==enter){
            tf2.setText(Double.toString(dist));
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
