package crystalcube.buildingvisualize.test;


import crystalcube.buildingvisualize.JsonManager;
import crystalcube.buildingvisualize.Tile;
import crystalcube.buildingvisualize.Vector;

import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        var jsonManager = new JsonManager("src/main/resources/well.json");
        PrintTiles(jsonManager.TileSet);
    }

    private static void PrintTiles(HashMap<Vector, Tile> tileSet){
        System.out.println(tileSet.size());
        for (var e: tileSet.keySet() ) {
            System.out.printf("%d %d %d: %s \n", e.X, e.Y, e.Z, tileSet.get(e).TileInfo.Name);
        }
    }
}