package trofimovep;

import com.google.gson.*;
import org.ejml.simple.SimpleMatrix;

import javax.json.Json;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.sun.javafx.fxml.expression.Expression.add;

//import static sun.plugin2.util.PojoUtil.toJson;


public class Saver implements JsonSerializer {


    protected static void Save(ArrayList<Knot> knots, ArrayList<Relation> relations) {

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String name = df.format(Calendar.getInstance().getTime());
        System.out.println("name = " + name);





//        String  model = new Gson().toJson(knots);

//        JsonObjectBuilder relationObject = Json.createObjectBuilder();
//        JsonArrayBuilder modArrBuilder = Json.createArrayBuilder();
//        for(Relation r : relations){
//
//                    relationObject
//                    .add("x1", r.getX1())
//                    .add("x2", r.getX2())
//                    .add("y1", r.getY1())
//                    .add("y2", r.getY2())
//                    .add("sizeParameteres[0]", r.getSizeParameteres()[0])
//                    .add("sizeParameteres[1]", r.getSizeParameteres()[1])
//                    .add("M", (JsonArrayBuilder) new SimpleMatrix(r.getM()))
//                    .add("Knot 1", (JsonArrayBuilder) r.getKnot1())
//                    .add("Knot 2", (JsonArrayBuilder) r.getKnot2())
//                    .add("Type", r.getType());
//
//            modArrBuilder.add(relationObject);
////        }
//
//        try (FileWriter file = new FileWriter("fuck u.txt")) {
////            file.write(String.valueOf(relationObject));
//            System.out.println("Successfully Copied JSON Object to File...");
//            System.out.println("\nJSON Object: " + relationObject);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }

}//class
