package crystalcube.buildingvisualize;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonManager {
    //private final JSONObject json;
    public String content = "";
    HashMap<Vector, Tile> TileSet;
    public JsonManager(String path){
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Tile> Parse(){
        try{
            JSONParser parser = new JSONParser();
            var json = (JSONArray)parser.parse(content);
            var res = new ArrayList<Tile>();
            for (var pair: json) {
                //TileSet.put((Vector)((JSONObject)json.get(0)).get("Key"), (Tile)((JSONObject)json.get(1)).get("Value"));
                res.add(new Tile((JSONObject)((JSONObject)pair).get("Value")));
            }
            return res;
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }

    }
}
