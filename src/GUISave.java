import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISave extends JFrame implements ActionListener {
    JTextField tf1;
    JButton enter;
    public GUISave(){
        GUI.contetpane.removeAll();
        GUI.contetpane.repaint();
        tf1 = new JTextField();
        tf1.setBounds(50,50,150,20);
        enter = new JButton("enter");
        GUI.frame.add(tf1);
        enter.setSize(150,200);
        enter.setLocation(150,200);
        GUI.contetpane.add(enter);
        enter.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String filename = tf1.getText();
        if(e.getSource()==enter){
            if(GUI.graphAlgo.save(filename)) {
                GUI.frame.setVisible(false);
                GUI.frame.dispose();
                new GUI(GUI.graphAlgo);
            }
        }
    }
}
