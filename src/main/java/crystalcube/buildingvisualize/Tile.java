package crystalcube.buildingvisualize;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Tile {
    public TileInfo TileInfo;
    public String[][] ModifiedEdges;
    public String[] ModifiedTextures;
    public ArrayList<TileModifiers> Modifiers;

    public Tile(JSONObject object){
        TileInfo = new TileInfo((JSONObject) object.get("TileInfo"));
        ModifiedEdges = JsonConverter.JSONArrayToString2DArray((JSONArray)object.get("ModifiedEdges"));
        Modifiers = JsonConverter.JSONArrayToTileModifiersList((JSONArray) object.get("Modifiers"));
        ModifiedTextures = object.get("ModifiedTextures") == null? null :
                JsonConverter.JSONArrayToStringArray((JSONArray) object.get("ModifiedTextures"));
    }
}
