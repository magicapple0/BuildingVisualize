package crystalcube.buildingvisualize;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonManager {
    public HashMap<Vector, Tile> TileSet;
    public JsonManager(String path){
        try {
            TileSet = Parse(new String(Files.readAllBytes(Paths.get(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonManager(InputStream stream){
        try {
            TileSet = Parse(new String(stream.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Vector, Tile> Parse(String tileSetString){
        try{
            JSONParser parser = new JSONParser();
            var json = (JSONArray)parser.parse(tileSetString);
            var result = new HashMap<Vector, Tile>();
            for (var pair: json)
                result.put(new Vector((JSONObject)((JSONObject)pair).get("Key")),
                        new Tile((JSONObject)((JSONObject)pair).get("Value")) );
            return result;
        }
        catch (ParseException e){
            throw new RuntimeException(e);
        }

    }
}
