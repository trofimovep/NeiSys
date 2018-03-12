package trofimovep;

import javax.swing.*;
import java.awt.*;


public class main {

    public static void main(String[] Args){
//        JFrame.setDefaultLookAndFeelDecorated(true);
        StartWindow window = new StartWindow("Neighborhood Systems");
//        window.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("logo.png")));


        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        window.setSize(xsize , ysize);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.GRAY);
        window.setVisible(true);
    }

}
