import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartWindow extends JFrame{

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

        drawPanel.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = getX();
                int y = getY();
                Graphics g = null;
                g.drawOval(x, y, 20, 20);


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








//public void drawKnots(Graphics g, int x, int y){
//        super.drawKnots(g);
//g.drawOval(x, y, 20, 20);
//g.setColor(Color.green);
//
//}//drawKnots





}
