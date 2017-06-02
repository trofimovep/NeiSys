import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class StartWindow extends JFrame  {

    ButtonGroup type_knot = new ButtonGroup();
    JRadioButton state_knot = new JRadioButton("Узел состояния");
    JRadioButton control_knot = new JRadioButton("Узел управления");
    public Ellipse2D view;


    Toolkit tk = Toolkit.getDefaultToolkit();
    int xsize = (int) tk.getScreenSize().getWidth();
    int ysize = (int) tk.getScreenSize().getHeight();

    public StartWindow(String s) {
        super(s);
        eHandler handler = new eHandler();

        /// draw Knots Panel

        final JPanel drawPanel = new JPanel();
        drawPanel.setLayout(null);
        setLayout(new GridLayout(2, 1));
        drawPanel.setBackground(Color.DARK_GRAY);
        add(drawPanel);

        // low panel with buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        add(buttonsPanel);
        pack();

        JLabel jj = new JLabel("label");
        jj.setBounds(20, 10, 200, 200);
        jj.setVisible(true);
        buttonsPanel.add(jj);

        control_knot.setBounds(20, 240, 200, 20);
        state_knot.setBounds(20, 220, 200, 20);
        type_knot.add(control_knot);
        type_knot.add(state_knot);
        buttonsPanel.add(state_knot);
        buttonsPanel.add(control_knot);
        state_knot.addActionListener(handler);
        control_knot.addActionListener(handler);

        boolean type; //тип узла: состояние(true) или управление (false)

        drawPanel.addMouseListener(new MouseListener() {

            int clicks = 0;
            public void mouseClicked(MouseEvent mouseEvent) {

                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                Graphics gr = drawPanel.getGraphics();
                Graphics2D gr2d = (Graphics2D) gr;
                clicks++;
//
//                States.add(new State(true, clicks, x, y));
//                int current = States.size() - 1;
//                for (int i = 0; i < States.size() - 1; i++) {
//
//                    if (Math.abs(States.get(i).view.getCenterX() - States.get(current).view.getCenterX()) < 30 &&
//                            Math.abs(States.get(i).view.getCenterY() - States.get(current).view.getCenterY()) < 30
//                            ) {
//                        States.remove(current);
//                        break;
//                    }
//                }
//                gr2d.setColor(Color.YELLOW);
//                for (int i = 0; i < States.size(); i++)
//                    gr2d.fill(States.get(i).getView());

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

    }//StartWindow

    class eHandler implements ActionListener{

        boolean type; // тип узла : состояние(true) или управление(false)

        public void actionPerformed(ActionEvent e) {
            try{
                if(e.getSource() ==  state_knot){
                type = true;
                }
                if(e.getSource() == control_knot){
                JOptionPane.showMessageDialog(null, "Control knot");
                }
            }
            catch (Exception ex){

            }

        }
    }


    public void paint(Graphics2D gr, int x, int y){
         view = new Ellipse2D.Double(x - 20 / 2 ,y - 20 / 2, 20, 20);
          gr.setBackground(Color.orange);
    }

}//class






