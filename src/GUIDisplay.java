import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;

public class GUIDisplay extends JPanel{
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem back;
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1, dy = y2 - y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double startX = distance-15, endX = startX, startY = 5, endY = -5, temp;
        double sin = dy / distance, cos = dx / distance;
        temp = startX * cos - startY * sin + x1;
        startY = startX * sin + startY * cos + y1;
        startX = temp;
        temp = endX * cos - endY * sin + x1;
        endY = endX * sin + endY * cos + y1;
        endX = temp;
        int[] _x = {x2, (int) startX, (int) endX};
        int[] _y = {y2, (int) startY, (int) endY};
        g.fillPolygon(_x, _y, 3);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double scaleY,scaleX;
        double minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        Iterator<NodeData> nodeDataIterator = GUI.graphAlgo.getGraph().nodeIter();
        while (nodeDataIterator.hasNext()){
            Node node = (Node) nodeDataIterator.next();
            GeoLocation location = node.getLocation();
            double x = location.x();
            double y = location.y();
            if (x > maxX) maxX = x;
            if (x< minX) minX = x;
            if (y>maxY) maxY = y;
            if(y<minY) minY = y;
        }
        scaleX = this.getWidth() / Math.abs(maxX - minX);
        scaleY = this.getHeight() / Math.abs(maxY - minY);

        nodeDataIterator = GUI.graphAlgo.getGraph().nodeIter();
        while (nodeDataIterator.hasNext()) {
            Node node = (Node) nodeDataIterator.next();
            GeoLocation location = node.getLocation();
            int x = (int) ((node.getLocation().x() - minX) * scaleX);
            int y = (int) ((node.getLocation().y() - minY) * scaleY);
            g.fillOval(x, y, 5, 5);
            g.drawString(String.valueOf(node.getKey()),x+5,y+2);
        }
        g.setColor(Color.RED);
        Iterator<EdgeData>edgeDataIterator = GUI.graphAlgo.getGraph().edgeIter();
        while (edgeDataIterator.hasNext()){
            Edge edge = (Edge) edgeDataIterator.next();
            Node src = (Node) GUI.graphAlgo.getGraph().getNode(edge.getSrc());
            Node dest = (Node) GUI.graphAlgo.getGraph().getNode(edge.getDest());
            int x1 = (int) ((src.getLocation().x() - minX) * scaleX);
            int y1 = (int) ((src.getLocation().y() - minY) * scaleY);
            int x2 = (int) ((dest.getLocation().x() - minX) * scaleX);
            int y2 = (int) ((dest.getLocation().y() - minY) * scaleY);
            g.drawLine(x1, y1, x2, y2);
            drawArrow(g,x1,y1,x2,y2);


        }
    }
    public GUIDisplay(){}
    public GUIDisplay(Boolean flag){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuBar = new JMenuBar();
                menu = new JMenu("menu");
                back = new JMenuItem("back to main screen");
                menu.add(back);
                menuBar.add(menu);
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource()==back){
                            frame.setVisible(false);
                            frame.dispose();
                            new GUI(GUI.graphAlgo);
                        }
                    }
                });
                frame.add(menuBar);
                frame.setJMenuBar(menuBar);
                frame.add(new GUIDisplay());
                frame.setSize(500,500);
                frame.setVisible(true);

            }
        });

    }
}