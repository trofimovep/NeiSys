package trofimovep;

import java.util.ArrayList;


/*Singleton class*/

public class Model {

    private static Model model;

    private ArrayList<Knot> knots;
    private ArrayList<Relation> relations;
    String name;

    private Model(ArrayList<Knot> knots, ArrayList<Relation> relations, String name){
        this.knots = knots;
        this.relations = relations;
        this.name = name;

    }

    public static Model getModel(ArrayList<Knot> knots, ArrayList<Relation> relations, String name){
            if(model == null){
                model = new Model(knots, relations, name);
            }
        return model;
    }

    public static String getName(){
        return model.name;
    }

}
