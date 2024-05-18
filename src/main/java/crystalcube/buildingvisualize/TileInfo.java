package crystalcube.buildingvisualize;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class TileInfo {

    public String Name;
    public String[][] Edges;
    public Boolean FlipX;
    public Boolean FlipY;
    public Boolean RotateZ;
    public String Color;
    public String[] Texture;

    public TileInfo(JSONObject object) {
        Name = object.get("Name").toString();
        var q = new ArrayList<String[]>();
        for (var edge: (JSONArray)object.get("Edges")) {
            var qq = new ArrayList<String>();
            for (var neighbor: (JSONArray)edge) {
                qq.add(neighbor.toString());
            }
            q.add(qq.toArray(new String[0]));
        }
        Edges = q.toArray(new String[0][0]);
        FlipX = (Boolean) object.get("FlipX");
        FlipY = (Boolean) object.get("FlipY");
        RotateZ = (Boolean) object.get("RotateZ");
        Color = object.get("Color") == null? null : object.get("Color").toString();
        if (object.get("Texture") == null)
            Texture = null;
        else{
            var qq = new ArrayList<String>();
            for (var tex: (JSONArray) object.get("Texture")) {
                qq.add(tex.toString());
            }
            Texture = qq.toArray(new String[0]);
        }
    }
}
