import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class StartWindow extends JFrame {


    ButtonGroup type_knot = new ButtonGroup();
    JRadioButton state_knot = new JRadioButton("Узел состояния");
    JRadioButton control_knot = new JRadioButton("Узел управления");


    int startX;
    int startY;
    int endX;
    int endY;
    int id1;
    int id2;


    public StartWindow(String s) {
        super(s);
        final eHandler handler = new eHandler();


        /*
        //         draw knots Panel
        */


        final ArrayList<Knot> knots = new ArrayList<Knot>();
        final ArrayList<Relation> relations = new ArrayList<Relation>();


        final DrawPanel drawPanel = new DrawPanel(knots, relations);

        drawPanel.setBackground(Color.DARK_GRAY);                // НЕ РАБОТАЕТ!!

        final Graphics g = drawPanel.getGraphics();
        drawPanel.setLayout(null);
        setLayout(new GridLayout(2, 1));
        drawPanel.setBackground(Color.DARK_GRAY);
        add(drawPanel);


//        JScrollPane Scroll = new JScrollPane(drawPanel);
        /*
         low panel with buttons
         */


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        add(buttonsPanel);
        pack();
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Параметры");
        buttonsPanel.setBorder(titled);


        JLabel jj = new JLabel("Тип узла: ");
        jj.setBounds(20, 20, 200, 20);
        jj.setVisible(true);
        buttonsPanel.add(jj);


        state_knot.setBounds(20, 40, 200, 20);
        control_knot.setBounds(20, 60, 200, 20);
        type_knot.add(control_knot);
        type_knot.add(state_knot);
        buttonsPanel.add(state_knot);
        buttonsPanel.add(control_knot);
        state_knot.addActionListener(handler);
        control_knot.addActionListener(handler);


        drawPanel.addMouseListener(new MouseListener() {


                 int clicks = 0;


            public void mouseClicked(MouseEvent mouseEvent) {

                String type = handler.getType(); //тип узла:

                if (type != null) {

                    int x = mouseEvent.getX();
                    int y = mouseEvent.getY();
                    clicks++;

                    knots.add(new Knot(type, clicks, x, y));
                    int current = knots.size() - 1;


                    for (int i = 0; i < knots.size() - 1; i++) {

                        if (Math.abs(knots.get(i).getX() - knots.get(current).getX()) < Knot.WIDTH + 20 &&
                                Math.abs(knots.get(i).getY() - knots.get(current).getY()) < Knot.HEIGHT + 20) {

                            knots.remove(current);
                            break;

                        }
                    }
                    drawPanel.repaint();

                }//if type != null

            }




            public void mousePressed(MouseEvent e) {

                startX = e.getX();
                startY = e.getY();

                for (Knot k: knots) {

                    if (Math.abs(startX - k.getX()) < Knot.WIDTH + 3 &&
                            Math.abs(startY - k.getY()) < Knot.HEIGHT + 3) {

                        id1 = k.getId();

                    }
                }
            }


               public void mouseReleased(MouseEvent e) {


                endX = e.getX();
                endY = e.getY();

                   for (Knot k: knots) {

                           if (Math.abs(endX - k.getX()) < Knot.WIDTH + 3 &&
                                      Math.abs(endY - k.getY()) < Knot.HEIGHT + 3){

                                   id2 = k.getId();
                                    System.out.println(isCorrect(id1, id2, knots));
                                   if(isCorrect(id1, id2, knots) == true)
                                        relations.add(new Relation(id1, id2, knots));
                                   else
                                       JOptionPane.showMessageDialog(null, "The operation is forbidden!");
                              }

                      }
                             drawPanel.repaint();

               }

               public void mouseEntered(MouseEvent mouseEvent) {
               }
               public void mouseExited(MouseEvent mouseEvent) {
               }

           });


        drawPanel.addMouseMotionListener(new MouseMotionListener() {

            public void mouseMoved(MouseEvent e){

            }
            public void mouseDragged(MouseEvent e) {

            }

        });

    }//StartWindow



    /* cлушатель на узлы (их тип) */
    class eHandler implements ActionListener{

        String type; // тип узла : состояние(true) или управление(false)

        public void actionPerformed(ActionEvent e) {

            try{
                if(e.getSource() == null){
                    type = null;
                }
                if(e.getSource() ==  state_knot){
                type = "State";
                }
                if(e.getSource() == control_knot){
                type = "Control";
                }
            }
            catch (Exception ex){

            }
        }
        public String getType(){ return type; }
    }


    /*Checking for type: RELATIONS FORBIDDEN IN THIS CASES:
                              1. Control <--> Control
    *                         2. State -> Control
    * */

    public boolean isCorrect(int id1, int id2, ArrayList<Knot> knots){

        boolean correct;

       String type_id1 = null;
       String type_id2 = null;

        for(Knot k : knots){
            if(id1 == k.getId())
                type_id1 = k.getType();

                if(id2 == k.getId())
                    type_id2 = k.getType();
                }

                if(type_id1 == "Control" && type_id2 == "Control")
                    return false;

                else if(type_id1 == "State" && type_id2 == "Control")
                    return false;
                else
                    return true;
    }



}//class






