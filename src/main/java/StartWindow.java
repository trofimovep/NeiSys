import javax.swing.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class StartWindow extends JFrame {

    /* amount of parameteres which make influence on Knot (SIZE OF MULTIMATRIX OF KNOT)*/
    private static final int OPTION_SIZE = 2;

    ButtonGroup type_knot = new ButtonGroup();
    JRadioButton state_knot = new JRadioButton("Узел состояния");
    JRadioButton control_knot = new JRadioButton("Узел управления");


    private List<JTextField> textfields = new ArrayList<JTextField>();
    private JPanel panel;

    private int currentKnotId;
    private int currentRelationId;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int id1;
    private int id2;


    public StartWindow(String s) {
        super(s);
        final eHandler handler = new eHandler();
        final Deleter deleter = new Deleter();


        /*
        //         draw knots Panel
        */

        final ArrayList<Knot> knots = new ArrayList<Knot>();
        final ArrayList<Relation> relations = new ArrayList<Relation>();


        final DrawPanel drawPanel = new DrawPanel(knots, relations);


        drawPanel.setLayout(null);
//        drawPanel.setBackground(Color.DARK_GRAY);    DOESN'T WORK
        setLayout(new GridLayout(2, 1));
        add(drawPanel);

        JMenuBar menuBar = new JMenuBar();

        Font font = new Font("Verdana", Font.PLAIN, 11);

        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setFont(font);
        menuBar.add(fileMenu);

        JMenuItem saveMenu = new JMenuItem("Cохранить модель");
        saveMenu.setFont(font);
        fileMenu.add(saveMenu);

        JMenuItem saveMenuAs = new JMenuItem("Сохранить модель как");
        saveMenuAs.setFont(font);
        fileMenu.add(saveMenuAs);

        JMenuItem uploadMenu = new JMenuItem("Загрузить модель");
        uploadMenu.setFont(font);
        fileMenu.add(uploadMenu);

        JMenu about = new JMenu("О программе");
        about.setFont(font);
        fileMenu.add(about);


        JMenu pravka = new JMenu("Правка");
        pravka.setFont(font);
        menuBar.add(pravka);

        drawPanel.setJmenuBar();
        setJMenuBar(menuBar);


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


        /*
        Popup Menu for Knots
         */
        final JPopupMenu popupMenu = new JPopupMenu();

        final JMenuItem setParam = new JMenuItem("Задать параметры");
        setParam.setFont(font);
        popupMenu.add(setParam);
        setParam.addActionListener(new ActionListener() {

            public void actionPerformed (ActionEvent e) {

                        int sizeParameteres[] = new int[OPTION_SIZE];
                        ArrayList<JTextField> sizeFields = new ArrayList<JTextField>(OPTION_SIZE);

                        JPanel sizepanel = new JPanel();

                        for (int k = 0; k < OPTION_SIZE; k++) {
                            sizepanel.add(new JLabel("size" + String.valueOf(k + 1)));
                            sizeFields.add(new JTextField(5));
                            sizepanel.add(sizeFields.get(k));
                        }

                        sizepanel.setVisible(true);
                        int resultsize = JOptionPane.showConfirmDialog(null, sizepanel,
                                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
                        if (resultsize == JOptionPane.OK_OPTION) {

                            int cur = 0;
                            for (JTextField ss : sizeFields) {
                                sizeParameteres[cur] = Integer.valueOf(ss.getText());
                                cur++;
                            }

                            StartWindow example = new StartWindow(sizeParameteres);
                            int resultmatrix = JOptionPane.showConfirmDialog(null, example.panel,
                                    "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
                            if (resultmatrix == JOptionPane.OK_OPTION) {
                                for (JTextField textfield : example.textfields) {
                                    System.out.println(textfield.getText());
                            /* РЕАЛИЗОВАТЬ ПЕРЕДАЧУ ПАРАМЕТРОВ В УЗЕЛ ОТСЮДА И ЩЕЛЧОК ПРИ НАЖАТИИ ТОЛЬКО НА УЗЕЛ*/
                                }
                            }

                        }
            }
        });

        JMenuItem changeParam = new JMenuItem("Изменить параметры");
        changeParam.setFont(font);
//        changeParam.addActionListener(popupListener);
        popupMenu.add(changeParam);

        JMenuItem deleteElement = new JMenuItem("Удалить элемент");
        deleteElement.setFont(font);
        deleteElement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                knots.remove(currentKnotId);
                repaint();
            }
        });
        popupMenu.add(deleteElement);



        /*
            Popup Menu for Relations
        */

        final JPopupMenu relationPopup = new JPopupMenu();

        JMenuItem deleteRelation = new JMenuItem("Удалить связь");
        deleteRelation.setFont(font);
        deleteRelation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                relations.remove(currentRelationId);
                repaint();
            }
        });
        relationPopup.add(deleteRelation);



        /*
            * DRAW PANEL      *
         */

        drawPanel.addMouseListener(new MouseListener() {

            int clicks = 0;

            public void mouseClicked(MouseEvent mouseEvent) {

                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                String type = handler.getType(); //тип узла:

                if(mouseEvent.getButton() == mouseEvent.BUTTON1) {
                    if (type != null) {

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
                    }
                }

                if(mouseEvent.getButton() == mouseEvent.BUTTON3) {

                    for (int i = 0; i < knots.size(); i++) {
                        if (Math.abs(knots.get(i).getX() - x) < Knot.WIDTH + 3 && Math.abs(knots.get(i).getY() - y) < Knot.HEIGHT + 3) {
                        currentKnotId = i;
                        popupMenu.show(drawPanel, x, y);
                        }
                    }

                    for(int i = 0; i < relations.size(); i++){
                        if(RelationDistance(relations.get(i), x, y)) {
                            relationPopup.show(drawPanel, x, y);
                        }
                    }
                }

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

               public void mouseReleased(MouseEvent mouseEvent) {

                       endX = mouseEvent.getX();
                       endY = mouseEvent.getY();

                       for (Knot k : knots) {

                           if (Math.abs(endX - k.getX()) < Knot.WIDTH + 3 &&
                                   Math.abs(endY - k.getY()) < Knot.HEIGHT + 3) {

                               id2 = k.getId();
                               if (id1 == id2) {
                               }
                               else if (isCorrect(id1, id2, knots) == true)
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

        String type;
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
                JOptionPane.showMessageDialog(null, ex);

            }
        }
        public String getType(){ return type; }
    }

    class Deleter implements ActionListener{
    ArrayList<Object> list;
    Object object;
        public void actionPerformed(ActionEvent e) {
            list.remove(object);
        }
    }


    /*Checking for type: RELATIONS FORBIDDEN IN THIS CASES:
                              1. Control <--> Control
    *                         2. State -> Control
    * */

    private boolean isCorrect(int id1, int id2, ArrayList<Knot> knots) {

        String type_id1 = null;
        String type_id2 = null;

        for (Knot k : knots) {

            if (id1 == k.getId())
                type_id1 = k.getType();

            if (id2 == k.getId())
                type_id2 = k.getType();
        }
            if (type_id1 == "Control" && type_id2 == "Control")
                return false;

            else if (type_id1 == "State" && type_id2 == "Control")
                return false;
            else
                return true;

    }

    private StartWindow(int[] sizeParameteres) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(sizeParameteres[0], sizeParameteres[1]));

        for (int i = 0; i < sizeParameteres[0]; i++) {
            for (int j = 0; j < sizeParameteres[1]; j++) {
                JTextField textfield = new JTextField(5);
                textfields.add(textfield);
                panel.add(new JLabel("X_{" + Integer.toString(i + 1) + ", " + Integer.toString(j + 1) + "}: "));
                panel.add(textfield);
            }
        }
    }

    private boolean RelationDistance(Relation r, int x, int y){
        int x1 = r.getX1();
        int y1 = r.getY1();
        int x2 = r.getX2();
        int y2 = r.getY2();

        double dist = Math.abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) / Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
        if(dist < 3.2)
            return true;
        else
            return false;

    }



}//class






