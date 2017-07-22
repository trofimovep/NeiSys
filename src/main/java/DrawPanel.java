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
        Graphics2D g2d = (Graphics2D) g;

        for (Knot k: knots) {

            if(k.getType() == "State")
                g2d.setColor(Color.ORANGE);

            else
                g2d.setColor(Color.BLUE);
            g2d.fill(k.getView(k.getX(), k.getY()));
            g2d.drawString(Integer.toString(k.getId()), k.getX(), k.getY());

        }

       for(Relation r : relations) {

            r.drawArrow(g2d, r.getX1(), r.getY1(), r.getX2(), r.getY2());

       }

    }

}
