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

            int clicks = 0;
            public void mouseClicked(MouseEvent mouseEvent) {

                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                Graphics gr = drawPanel.getGraphics();
                Graphics2D gr2d = (Graphics2D) gr;
                clicks++;

                boolean type = true;
                // во не помню, добавлял ли я эту строчку, или из-за нее
                States.add(new State(type, clicks, x, y));
                int current = States.size() - 1;
                for (int i = 0; i < States.size() - 1; i++) {

                    if (Math.abs(States.get(i).view.getCenterX() - States.get(current).view.getCenterX()) < 20 &&
                            Math.abs(States.get(i).view.getCenterY() - States.get(current).view.getCenterY()) < 20
                            ) {
                        States.remove(current);
                        break;
                    }
                }
                gr2d.setColor(Color.YELLOW);
                for (int i = 0; i < States.size(); i++)
                    gr2d.fill(States.get(i).getView());

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



        // low panel with buttons


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        add(buttonsPanel);
        pack();

        JLabel jj = new JLabel("label");
        jj.setBounds(20, 10, 200, 200);
        jj.setVisible(true);
        buttonsPanel.add(jj);

        ButtonGroup type_knot = new ButtonGroup();
        JRadioButton state_knot = new JRadioButton("Узел состояния");
        state_knot.setBounds(20, 220, 100, 20);
        type_knot.add(state_knot);
        buttonsPanel.add(state_knot);



    }//StartWindow

}//class



















//class



