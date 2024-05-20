package crystalcube.buildingvisualize.Json;

import crystalcube.buildingvisualize.Json.JsonConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
        Edges = JsonConverter.JSONArrayToString2DArray((JSONArray)object.get("Edges"));
        FlipX = (Boolean) object.get("FlipX");
        FlipY = (Boolean) object.get("FlipY");
        RotateZ = (Boolean) object.get("RotateZ");
        Color = object.get("Color") == null? null : object.get("Color").toString();
        Texture = object.get("Texture") == null? null :
                JsonConverter.JSONArrayToStringArray((JSONArray) object.get("Texture"));
    }
}
