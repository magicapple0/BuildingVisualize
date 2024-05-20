package crystalcube.buildingvisualize.Builderr;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashMap;

public class TileModel {
    private final String name;
    private final int size;
    private final HashMap<Location, Block> LocationToBlocks = new HashMap<>();
    public TileModel(String name, Location firstPoint, Location secondPoint){
        this.name = name;
        var world = firstPoint.getWorld();
        size = Math.abs(firstPoint.getBlockX() - secondPoint.getBlockX()) + 1;
        var min = new Location(world,
                Math.min(firstPoint.getBlockX(), secondPoint.getBlockX()),
                Math.min(firstPoint.getBlockY(), secondPoint.getBlockY()),
                Math.min(firstPoint.getBlockZ(), secondPoint.getBlockZ()));
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                for (int k = 0; k < size; k++){
                    var relativeLocation = new Location(world, i, j, k);
                    var absoluteLocation = new Location(world,
                            i + min.getBlockX(), j  + min.getBlockY(), k + min.getBlockZ());
                    LocationToBlocks.put(relativeLocation, world.getBlockAt(absoluteLocation));
                }
            }
        }
    }

    public HashMap<Location, Block> getLocationToBlocks() { return LocationToBlocks; }
    public int getSize(){ return size; }
    public String getName(){ return name; }
}
