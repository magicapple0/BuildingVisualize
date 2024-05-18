package crystalcube.buildingvisualize.test;


import crystalcube.buildingvisualize.JsonManager;

public class Main {
    public static void main(String[] args)
    {
        var jsonManager = new JsonManager("src/main/resources/well.json");
        var t = jsonManager.Parse();
        for (var e: t ) {
            System.out.println(e.TileInfo.Name);
        }
    }
}