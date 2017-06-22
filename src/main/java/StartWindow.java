import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class StartWindow extends JFrame {

    ButtonGroup type_knot = new ButtonGroup();
    JRadioButton state_knot = new JRadioButton("Узел состояния");
    JRadioButton control_knot = new JRadioButton("Узел управления");

//    Toolkit tk = Toolkit.getDefaultToolkit();
//    int xsize = (int) tk.getScreenSize().getWidth();
//    int ysize = (int) tk.getScreenSize().getHeight();

    public StartWindow(String s) {
        super(s);
        final eHandler handler = new eHandler();
        /*
        //         draw knots Panel
        */
        final ArrayList<Knot> knots = new ArrayList<Knot>();

        final DrawPanel drawPanel = new DrawPanel(knots);
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
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                boolean type = handler.getType(); //тип узла: состояние(true) или управление (false)
                clicks++;

                knots.add(new Knot(type, clicks, x, y));
                int current = knots.size() - 1;

                for (int i = 0; i < knots.size() - 1; i++) {
                    if (Math.abs(knots.get(i).getX() - knots.get(current).getX()) < Knot.WIDTH + 10 &&
                            Math.abs(knots.get(i).getY() - knots.get(current).getY()) < Knot.HEIGHT + 10) {
                        knots.remove(current);
                        break;
                    }
               }
                drawPanel.repaint();
            }
               public void mousePressed(MouseEvent mouseEvent) { }
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
                type = false;
                }
            }
            catch (Exception ex){

            }
        }
        public boolean getType(){ return type; }
    }
}//class






