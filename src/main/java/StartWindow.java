import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        drawPanel.addMouseListener(new MouseListener() {
                                       public void mouseClicked(MouseEvent mouseEvent) {
                                           int x = mouseEvent.getX();
                                           int y = mouseEvent.getY();
                                           Graphics gr = drawPanel.getGraphics();
                                           System.out.println(x);
                                           
                                           gr.setColor(Color.YELLOW);
                                           gr.fillOval(x, y, 20, 20);

//                                           drawPanel.add(paintComponents(g, x, y));
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



//        public void paintComponents(Graphics g){
//            super.paintComponents(g);
//
//            g.drawOval(x, y, 20, 20);
//            g.setColor(Color.green);
//        }



    }//StartWindow

}//class



















//class



