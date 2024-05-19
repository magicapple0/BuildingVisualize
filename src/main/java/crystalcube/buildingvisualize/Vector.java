package crystalcube.buildingvisualize;

import org.bukkit.Location;
import org.bukkit.World;
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

    public Vector(int x, int y, int z){
        X = x;
        Y = y;
        Z = z;
    }

    public Vector(Location location){
        X = location.getBlockX();
        Y = location.getBlockY();
        Z = location.getBlockZ();
    }

    public Location ToLocation(World world){
        return new Location(world, X, Y, Z);
    }

    public String ToString(){ return X + " " + Y + " " + Z; }

    public Vector setOffset(Vector offset){
        return new Vector(X + offset.X, Y + offset.Y, Z + offset.Z);
    }
}
