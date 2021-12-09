import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIRemoveNode extends JFrame implements ActionListener {
    JTextField tf1,tf2;
    JButton enter,back;
    int key = 0;
    public GUIRemoveNode(){
        GUI.contetpane.removeAll();
        GUI.contetpane.repaint();
        tf1 = new JTextField("enter key");
        tf2 = new JTextField();
        tf1.setBounds(50,50,150,20);
        tf2.setBounds(50,90,150,20);
        enter = new JButton("enter");
        GUI.frame.add(tf1);
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
        try {
            key = Integer.parseInt(s1);
        } catch (NumberFormatException numberFormatException) {

        }
        GraphAlgo temp = new GraphAlgo();
        temp = GUI.graphAlgo;
        temp.getGraph().removeNode(key);
        GUI.graphAlgo = temp;
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
