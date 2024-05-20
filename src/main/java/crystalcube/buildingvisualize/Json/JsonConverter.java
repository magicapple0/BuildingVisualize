package crystalcube.buildingvisualize.Json;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class JsonConverter {
    public static String[][] JSONArrayToString2DArray(JSONArray object){
        var list = new ArrayList<String[]>();
        for (var element: object) {
            list.add(JSONArrayToStringArray((JSONArray) element));
        }
        return list.toArray(new String[0][0]);
    }

    public static String[] JSONArrayToStringArray(JSONArray object){
        var list = new ArrayList<String>();
        for (var element: object)
            list.add(element.toString());
        return list.toArray(new String[0]);
    }

    public static ArrayList<TileModifiers> JSONArrayToTileModifiersList(JSONArray object){
        var list = new ArrayList<TileModifiers>();
        for (var element: object)
            list.add(TileModifiers.fromInteger(Integer.parseInt(element.toString())));
        return list;
    }
}
