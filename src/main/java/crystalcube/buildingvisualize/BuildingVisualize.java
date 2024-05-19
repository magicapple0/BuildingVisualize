package crystalcube.buildingvisualize;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BuildingVisualize extends JavaPlugin {

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
}
