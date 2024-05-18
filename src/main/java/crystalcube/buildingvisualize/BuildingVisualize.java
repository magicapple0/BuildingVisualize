package crystalcube.buildingvisualize;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BuildingVisualize extends JavaPlugin {

    private final MyConfig config = new MyConfig(this);

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("build")).setExecutor(new MyCommandExecutor(this));
        var t = new JsonManager("src/main/resources/well.json");
        this.getLogger().info("1");
        this.getLogger().info(t.content);
    }

    @Override
    public void onDisable() {
        config.saveConfig();
    }

    public MyConfig getMyConfig(){ return config; }
}
