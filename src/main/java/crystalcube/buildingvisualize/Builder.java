package crystalcube.buildingvisualize;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Objects;

public class Builder {
    static public void Build(World world, HashMap<Vector, Tile> tileSet, Vector offset, CommandSender sender){
        for (var tile : tileSet.entrySet()) {
            var newVector = new Vector(tile.getKey().X, tile.getKey().Z, tile.getKey().Y);
            var block = world.getBlockAt(newVector.setOffset(offset).ToLocation(world));
            if (Objects.equals(tile.getValue().TileInfo.Name, "air")){
                block.setType(Material.AIR);
            }
            else{
                block.setType(Material.AMETHYST_BLOCK);
            }
        }
    }
}
