package trofimovep;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawPanel extends JComponent {


    ArrayList<Knot> knots;
    ArrayList<Relation> relations;


    public DrawPanel(ArrayList<Knot> knots, ArrayList<Relation> relations) {
        this.knots = knots;
        this.relations = relations;
    }


    @Override
    public void paintComponent(Graphics g) {
//        g.setColor(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setBackground(Color.DARK_GRAY);


        for (Knot k: knots) {

            if(k instanceof State)
                g2d.setColor(Color.ORANGE);

            else if(k instanceof Control)
                g2d.setColor(Color.BLUE);

            g2d.fill(k.getView(k.getX(), k.getY()));
            g2d.drawString((k.getType() + Integer.toString(k.getId())), k.getX(), k.getY());
            g2d.setColor(Color.BLACK);
            g2d.drawString(k.getSizeParameteres()[0] + "x" +k.getSizeParameteres()[1], k.getX()+Knot.WIDTH, k.getY()+Knot.HEIGHT );

        }

       for(Relation r : relations) {

            r.drawArrow(g2d, r.getX1(), r.getY1(), r.getX2(), r.getY2());
            g2d.drawString(r.getType()+","+r.getSizeParameteres()[0] + "x" + r.getSizeParameteres()[1], (r.getX1()+r.getX2()) / 2, (r.getY1() + r.getY2()) / 2);
       }



    }


    public void setJmenuBar() {
    }

}
