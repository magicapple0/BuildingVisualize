package crystalcube.buildingvisualize;

import org.json.simple.JSONObject;

public class Vector {
    public int X;
    public int Y;
    public int Z;
    public Vector(JSONObject object){
        X = Integer.parseInt(object.get("X").toString());
        Y = Integer.parseInt(object.get("Y").toString());
        Z = Integer.parseInt(object.get("Z").toString());
    }
}
