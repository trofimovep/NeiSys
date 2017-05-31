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

            //clicks = id
            int clicks = 0;


            public void mouseClicked(MouseEvent mouseEvent) {

                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                Graphics gr = drawPanel.getGraphics();
                clicks++;
                System.out.println(clicks + "  = clicks");

                boolean type = true;

                if(clicks == 1){
                    States.add(new State(type, clicks, x,y));
                    State.drawState(gr, x, y);
                }
                else{
                    for(int i = 0; i < clicks; i++){

                        int oldx = States.get(i).getX();
                        int oldy = States.get(i).getY();
                        double rx = (oldx - x) * (oldx - x);
                        double ry = (oldy - y) * (oldy - y);
                        double raz = Math.sqrt(rx + ry);
                        System.out.println("raz = " + raz);

                        if(Math.abs(x - oldx) < 20){
                            System.out.println("Меньше !");
                            int s = States.size();
                            System.out.println("s = " + s);
//                            State.drawState(gr, x, y);
                        }
                        else{
                            States.add(new State(type, clicks, x,y));
                            State.drawState(gr, x, y);
                        }

//                        if(raz < 20){
//                            System.out.println("Меньше !");
//                            int s = States.size();
//                            System.out.println("s = " + s);
////                            State.drawState(gr, x, y);
//                        }
//                        else{
//                            States.add(new State(type, clicks, x,y));
//                            State.drawState(gr, x, y);
//                        }
                    }
                }

                //States.add(new State(type, clicks, x, y));
//                State.drawState(gr, x, y);
//                int s = States.size();
//                System.out.println(s);


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



