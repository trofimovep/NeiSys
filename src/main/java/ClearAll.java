import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ClearAll extends StartWindow implements ActionListener {

    public ClearAll(String s) {
        super(s);
    }

    public void actionPerformed(ActionEvent e) {
    knots.removeAll(knots);
    relations.removeAll(relations);
    }

}
