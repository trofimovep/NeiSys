/**
 * Created by nash on 5/26/17.
 */
import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] Args){
        StartWindow window = new StartWindow("Heat equation");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        window.setSize(xsize , ysize);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
