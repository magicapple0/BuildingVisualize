package crystalcube.buildingvisualize;

import crystalcube.buildingvisualize.Axe.OnAxeClickHandler;
import crystalcube.buildingvisualize.Axe.SelectorAxeManager;
import crystalcube.buildingvisualize.Builderr.TileModel;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public final class BuildingVisualize extends JavaPlugin {

    private final HashMap<String, TileModel> nameToModel = new HashMap<>();
    private final SelectorAxeManager selectorAxeManager = new SelectorAxeManager(this);
    private final MyConfig config = new MyConfig(this);

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new MyCommandExecutor(this));
        getServer().getPluginManager().registerEvents(new OnAxeClickHandler(this), this);
    }

    @Override
    public void onDisable() {
        config.saveConfig();
    }

    public MyConfig getMyConfig(){ return config; }
    public SelectorAxeManager getAxeManagerConfig(){ return selectorAxeManager; }
    public TileModel getModel(String name){ return nameToModel.get(name); }
    public void saveModel (String name, Location firstPoint, Location secondPoint){
        nameToModel.put(name, new TileModel(name, firstPoint, secondPoint));
    }
}
