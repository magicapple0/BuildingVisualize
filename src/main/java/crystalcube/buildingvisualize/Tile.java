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
        var q = new ArrayList<String[]>();
        for (var edge: (JSONArray)object.get("ModifiedEdges")) {
            var qq = new ArrayList<String>();
            for (var neighbor: (JSONArray)edge) {
                qq.add(neighbor.toString());
            }
            q.add(qq.toArray(new String[0]));
        }
        ModifiedEdges = q.toArray(new String[0][0]);
        if (object.get("ModifiedTextures") == null)
            ModifiedTextures = null;
        else{
            var qq = new ArrayList<String>();
            for (var tex: (JSONArray) object.get("ModifiedTextures")) {
                qq.add(tex.toString());
            }
            ModifiedTextures = qq.toArray(new String[0]);
        }

        Modifiers = new ArrayList<>();
        for (var tex: (JSONArray) object.get("Modifiers")) {
            Modifiers.add(TileModifiers.fromInteger(Integer.parseInt(tex.toString())));
        }

    }
}
