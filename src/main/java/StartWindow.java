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

    JLabel tt;
    JRadioButton ctrl_knot, state_knot;
    eHandler eh = new eHandler();

    public StartWindow(String s) {
        super(s);
        /// draw Knots Panel
        final JPanel drawPanel = new JPanel();
        drawPanel.setLayout(null);
        setLayout(new GridLayout(2, 1));
        drawPanel.setBackground(Color.DARK_GRAY);
        add(drawPanel);
        //low panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        add(buttonsPanel);
        pack();

        JLabel jj = new JLabel("Тип узла");
        jj.setBounds(5, 20, 200, 20);
        jj.setVisible(true);
        buttonsPanel.add(jj);

        ButtonGroup type_knot = new ButtonGroup();
        state_knot = new JRadioButton("Узел состояния");
        state_knot.setBounds(5, 40, 200, 20);
        type_knot.add(state_knot);
        buttonsPanel.add(state_knot);
        state_knot.addActionListener(eh);

        ctrl_knot = new JRadioButton("Узел управления");
        ctrl_knot.setBounds(5, 60, 200, 20);
        type_knot.add(ctrl_knot);
        buttonsPanel.add(ctrl_knot);
        ctrl_knot.addActionListener(eh);



        final ArrayList<State> States = new ArrayList<State>();
        final ArrayList<Control> Controls = new ArrayList<Control>();

        drawPanel.addMouseListener(new MouseListener() {

            int clicks = 0;
            public void mouseClicked(MouseEvent mouseEvent) {

                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                Graphics gr = drawPanel.getGraphics();
                Graphics2D gr2d = (Graphics2D) gr;
                clicks++;

                boolean type = eh.getType();
                System.out.println("type = " + type);

                if (type == true) {
                    States.add(new State(type, clicks, x, y));
                    int current = States.size() - 1;
                    for (int i = 0; i < States.size() - 1; i++) {

                        if (Math.abs(States.get(i).view.getCenterX() - States.get(current).view.getCenterX()) < 50 &&
                                Math.abs(States.get(i).view.getCenterY() - States.get(current).view.getCenterY()) < 50
                                ) {
                            States.remove(current);
                            break;
                        }
                    }
                    gr2d.setColor(Color.ORANGE);
                    for (int i = 0; i < States.size(); i++)
                        gr2d.fill(States.get(i).getView());

                } else if (type == false) {
                    Controls.add(new Control(type, clicks, x, y));
                    int current = Controls.size() - 1;
                    for (int i = 0; i < Controls.size() - 1; i++) {

                        if (Math.abs(Controls.get(i).view.getCenterX() - Controls.get(current).view.getCenterX()) < 50 &&
                                Math.abs(Controls.get(i).view.getCenterY() - Controls.get(current).view.getCenterY()) < 50
                                ) {
                            Controls.remove(current);
                            break;
                        }
                    }
                    gr2d.setColor(Color.BLUE);
                    for (int i = 0; i < Controls.size(); i++)
                        gr2d.fill(Controls.get(i).getView());
                }
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


    }//StartWindow

    class eHandler implements ActionListener{
        boolean t;
        public void actionPerformed(ActionEvent e) {
             try{

                if(e.getSource() == state_knot){
                    t = true;
                System.out.println("t = " + t);}
                else if(e.getSource() == ctrl_knot)
                    t = false;


            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        boolean getType(){
            return t;
        }
    }
}//class



















//class



