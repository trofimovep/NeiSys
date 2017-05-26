import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class StartWindow extends JFrame {

    Toolkit tk = Toolkit.getDefaultToolkit();
    int xsize = (int) tk.getScreenSize().getWidth();
    int ysize = (int) tk.getScreenSize().getHeight();


    public StartWindow(String s) {
        super(s);


        /// draw Knots Panel
        final JPanel drawPanel = new JPanel();
        drawPanel.setLayout(null);
        setLayout(new GridLayout(2, 1));
        drawPanel.setBackground(Color.DARK_GRAY);
        add(drawPanel);
        final ArrayList<State> States = new ArrayList<State>();

        drawPanel.addMouseListener(new MouseListener() {
                                       public void mouseClicked(MouseEvent mouseEvent) {
                                           int x = mouseEvent.getX();
                                           int y = mouseEvent.getY();
                                           int  clicks = mouseEvent.getClickCount();

                                           Graphics gr = drawPanel.getGraphics();
                                           System.out.println(x);

                                           States.add(new State(clicks, x, y));
                                           State.drawState(gr, x, y);
                                           int s = States.size();
                                           System.out.println(s);

                                       }

                                       public void mousePressed(MouseEvent mouseEvent) {

                                       }

                                       public void mouseReleased(MouseEvent mouseEvent) {

                                       }

                                       public void mouseEntered(MouseEvent mouseEvent) {

                                       }

                                       public void mouseExited(MouseEvent mouseEvent) {

                                       }
                                   });


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        add(buttonsPanel);
        pack();

        JLabel jj = new JLabel("ты пидор");
        jj.setBounds(20, 10, 200, 200);
        jj.setVisible(true);
        buttonsPanel.add(jj);


    }//StartWindow

}//class



















//class



