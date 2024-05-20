package crystalcube.buildingvisualize.Builderr;

import crystalcube.buildingvisualize.BuildingVisualize;
import crystalcube.buildingvisualize.Json.Tile;
import crystalcube.buildingvisualize.Vector;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Objects;

public class Builder {
    static public void Build(World world, HashMap<Vector, Tile> tileSet, Location offset, CommandSender sender, BuildingVisualize plugin){
        var tileSize = 5;
        for (var tile : tileSet.entrySet()) {
            if (Objects.equals(tile.getValue().TileInfo.Name, "air")){
                continue;
            }
            var newVector = new Vector(tile.getKey().X * tileSize, tile.getKey().Z * tileSize, tile.getKey().Y * tileSize);
            BuildTileModel(world, plugin.getModel(tile.getValue().TileInfo.Name), newVector.setOffset(new Vector(offset)).ToLocation(world) );
        }
    }

    static public void BuildTileModel(World world, TileModel model, Location offset){
        for (var block : model.getLocationToBlocks().entrySet()) {
            var newVector = new Vector(block.getKey().getBlockX(),block.getKey().getBlockY(), block.getKey().getBlockZ());
            var b = world.getBlockAt(newVector.setOffset(new Vector(offset)).ToLocation(world));
            b.setBlockData(block.getValue().getBlockData());
        }
    }
}
