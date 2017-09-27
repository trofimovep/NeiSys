import javafx.scene.input.KeyCode;

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
    public static final int OPTION_SIZE = 2;

    final ArrayList<Knot> knots = new ArrayList<Knot>();
    final ArrayList<Relation> relations = new ArrayList<Relation>();

    private boolean RepaiPanel = false;

    private List<JTextField> textfields = new ArrayList<JTextField>();
    private JPanel panel;

    private Object current;

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int id1;
    private int id2;


    ButtonGroup typeKnot = new ButtonGroup();
    JRadioButton stateKnot = new JRadioButton("Узел состояния");
    JRadioButton controlKnot = new JRadioButton("Узел управления");

    ButtonGroup typeRelation = new ButtonGroup();
    JRadioButton RelationbyAdd = new JRadioButton("Аддитивная (R = C + S)");
    JRadioButton RelationbyMult = new JRadioButton("Мультипликативная (R = C * S)");


    final RelationTypeHandler relationTypeHandler = new RelationTypeHandler();



    public StartWindow(String s) {
        super(s);
        final eHandler handler = new eHandler();
        final inputParam inparam = new inputParam();
        final inputVector invector = new inputVector();
        final ClearAll clearall = new ClearAll();
        final Counter counter = new Counter();

        /*
        //         draw knots Panel
        */


        final DrawPanel drawPanel = new DrawPanel(knots, relations);
//        drawPanel.setBackground(Color.BLACK);


        drawPanel.setLayout(null);
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

        JMenuItem clearAll = new JMenuItem("Очистить все");
        clearAll.setFont(font);
        clearAll.addActionListener(clearall);
        pravka.add(clearAll);



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

        stateKnot.setBounds(20, 40, 200, 20);
        controlKnot.setBounds(20, 60, 200, 20);
        typeKnot.add(controlKnot);
        typeKnot.add(stateKnot);
        buttonsPanel.add(stateKnot);
        buttonsPanel.add(controlKnot);
        stateKnot.addActionListener(handler);
        controlKnot.addActionListener(handler);

//        ButtonGroup

        JLabel DesignLine0 = new JLabel("Тип связи:");
        DesignLine0.setBounds(20, 80, 200,20);
        buttonsPanel.add(DesignLine0);

        RelationbyAdd.setBounds(20,100,250,20);
        RelationbyMult.setBounds(20,120,250,20);
        typeRelation.add(RelationbyAdd);
        typeRelation.add(RelationbyMult);
        buttonsPanel.add(RelationbyAdd);
        buttonsPanel.add(RelationbyMult);
        RelationbyAdd.addActionListener(relationTypeHandler);
        RelationbyMult.addActionListener(relationTypeHandler);


  JButton CountButton = new JButton("Расчитать");
        CountButton.setBounds(20, 200, 200, 20);
        buttonsPanel.add(CountButton);
        CountButton.addActionListener(counter);

        /*
        Popup Menu for Knots
         */
        final JPopupMenu popupMenu = new JPopupMenu();

        final JMenuItem setParam = new JMenuItem("Задать параметры");
        setParam.setFont(font);
        popupMenu.add(setParam);
        setParam.addActionListener(inparam);

        JMenuItem changeParam = new JMenuItem("Изменить параметры");
        changeParam.setFont(font);
//        changeParam.addActionListener(popupListener);
        popupMenu.add(changeParam);



//        popupMenu.add(setInputVector);
        final JMenuItem setInputVector = new JMenuItem("Задать входящий вектор");
        setInputVector.setFont(font);
        setInputVector.addActionListener(invector);
//        for(Knot k : knots) {
//            if (k.getInnerRealations().size() == 0)
//                popupMenu.add(setInputVector);
//        }

        JMenuItem deleteElement = new JMenuItem("Удалить элемент");
        deleteElement.setFont(font);
        deleteElement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                knots.remove(current);
                repaint();
            }
        });
        popupMenu.add(deleteElement);




        /*
            Popup Menu for Relations
        */

        final JPopupMenu relationPopup = new JPopupMenu();

        JMenuItem setRelation = new JMenuItem("Параметры связи");
        setRelation.setFont(font);
        setRelation.addActionListener(inparam);
        relationPopup.add(setRelation);

        JMenuItem deleteRelation = new JMenuItem("Удалить связь");
        deleteRelation.setFont(font);
        deleteRelation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                relations.remove(current);
                repaint();
            }
        });
        relationPopup.add(deleteRelation);



        /*
            * DRAW PANEL      *
         */



        /*                       Listeners                          */




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
                        for(Knot k : knots) {
                           System.out.println(k.getId() + " + " + k.getInnerRealations().size());
                            if (k.getInnerRealations().size() == 0) {
                                popupMenu.add(setInputVector);
                            }
                        }


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

                    for (Knot k : knots) {
                        if (Math.abs(k.getX() - x) < Knot.WIDTH + 3 && Math.abs(k.getY() - y) < Knot.HEIGHT + 3) {
                        current = k;
                        popupMenu.show(drawPanel, x, y);
                        }
                    }

                    for(Relation r : relations){
                        if(RelationDistance(r, x, y)) {
                            current = r;
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
                               else if (isCorrect(id1, id2, knots)) {
                                   Relation r = new Relation(id1, id2, knots);
                                   for(Knot k2 : knots) {
                                       if (k2.getId() == id1) {
                                           k2.addOutputRelations(r);
                                       }
                                   }
                                   k.addInnerRelations(r);
                                   relations.add(r);
                               }
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
                if(RepaiPanel == true){
                    drawPanel.repaint();
                    RepaiPanel = false;
                }
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
                if(e.getSource() ==  stateKnot){
                type = "State";
                }
                if(e.getSource() == controlKnot){
                type = "Control";
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);

            }
        }
        public String getType(){ return type; }
    }




    class inputParam implements ActionListener{

        public void actionPerformed(ActionEvent e) {

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
                for (JTextField jt : sizeFields) {
                    sizeParameteres[cur] = Integer.valueOf(jt.getText());
                    cur++;
                }

                if(isSizesCorrect(sizeParameteres, current)) {

                    StartWindow example = new StartWindow(sizeParameteres);
                    int resultmatrix = JOptionPane.showConfirmDialog(null, example.panel,
                            "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);

                    if (resultmatrix == JOptionPane.OK_OPTION) {
                        double[][] M = new double[sizeParameteres[0]][sizeParameteres[1]];
                                            /*
                    * Realize for any dimension (OPTION_SIZE)*  ???
                    */
                        int t = 0;
                        for (int i = 0; i < sizeParameteres[0]; i++) {
                            for (int j = 0; j < sizeParameteres[1]; j++) {
                                M[i][j] = Double.parseDouble(example.textfields.get(t).getText());
                                t++;
                            }
                        }

                        SaverToParam(sizeParameteres, M, current);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Ошибка размерностей");
                }

                RepaiPanel = true;
            }
        }

        public void SaverToParam(int[] sizeParameteres, double[][] M, Object current){
            if (current instanceof Knot) {
                ((Knot) current).setSizeParameteres(sizeParameteres);
                ((Knot) current).setM(M);

//                            SimpleMatrix m = new SimpleMatrix(M);
//                            SimpleMatrix pinvM = m.pseudoInverse();
//                            pinvM.print();

            } else if (current instanceof Relation) {
                ((Relation) current).setSizeParameteres(sizeParameteres);
                ((Relation) current).setM(M);
            }


        }
    }





private class inputVector extends inputParam{

    @Override
    public void SaverToParam(int[] sizeParameteres, double[][] M, Object current) {
            if(current instanceof  State) {
                ((State) current).setSizeParameteres(sizeParameteres);
                ((State) current).setInputVector(M);
            }
    }
}




/*
* Clear All
* */
private class ClearAll implements ActionListener{

    public void actionPerformed(ActionEvent e) {
        knots.removeAll(knots);
        relations.removeAll(relations);
        repaint();
    }
}



private class RelationTypeHandler implements ActionListener{
/*
* Relation Type = 1, if Relation is Additive
* Relation Type = 2, if Relation is Multiplicative
* */
    private int typerel;

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == RelationbyAdd){
            typerel = 1;
        }
        else if(e.getSource() == RelationbyMult){
            typerel = 2;
        }
    }

    public int getTyperel() {
        return typerel;
    }
}

/*                                     secondary functions                          */




    /*Checking for type: RELATIONS FORBIDDEN IN THIS CASES:
                              1. Control <--> Control
    *                         2. State -> Control
    *                         3. Itself relation
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


    /**
     *
     */
    private boolean isSizesCorrect(int[] sizeParams, Object object){

        boolean decision = true;

        if(object instanceof Relation) {
            Relation r = (Relation) object;

            int sizeRel1 = sizeParams[0];
            int sizeRel2 = sizeParams[1];

            Knot knot1 = r.getKnot1();
            Knot knot2 = r.getKnot2();

            int sizeKnot1 = knot1.getSizeParameteres()[1];
            int sizeKnot2 = knot2.getSizeParameteres()[0];

            if (sizeKnot1 == sizeRel1 & sizeKnot2 == sizeRel2 || sizeKnot1 == 0 & sizeKnot2 == sizeRel2
                    || sizeKnot1 == sizeRel1 & sizeKnot2 == 0 ||sizeKnot1 == 0 & sizeKnot2 == 0 || sizeKnot1 == 1 & knot1.getSizeParameteres()[0] == 1
                    || sizeKnot2 == 1 & knot2.getSizeParameteres()[1] == 1) {
                decision = true;
            } else {
                decision = false;
            }
        }


       else if(object instanceof Knot){

            Knot k = (Knot) object;

            if(sizeParams[0] == 1 & sizeParams[1] == 1){
                System.out.println(decision);

            }
            else {
                int sInnerRelation = 0;
                for (Relation r : k.getInnerRealations()) {
                    if (r.getSizeParameteres()[1] == 0 || r.getSizeParameteres()[1] == sizeParams[0] || r.getSizeParameteres()[0] == 1 & r.getSizeParameteres()[1] == 1) {
                        sInnerRelation++;
                    }
                }

                int sOutRelations = 0;
                for (Relation r : k.getOutputRealations()) {
                    if (r.getSizeParameteres()[0] == 0 || r.getSizeParameteres()[0] == sizeParams[1] || r.getSizeParameteres()[0] == 1 & r.getSizeParameteres()[1] == 1) {
                        sOutRelations++;
                    }
                }

                if (sInnerRelation < k.getInnerRealations().size() | sOutRelations < k.getOutputRealations().size()) {
                    decision = false;
                }
            }

        }

        return decision;
    }



    /*                      Counter Listener               */




    private class Counter implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            int relType = relationTypeHandler.getTyperel();

            /* Добавить условие на различные системы */
            if(relType == 1){

            }
            else if(relType == 2){
                Identificater countIdentifier = new Identificater();
                countIdentifier.getOutMatrixesSize();
                System.out.println("FUck upuy" + countIdentifier.getOutMatrixesSize());
            }


        }

    }



}//class






