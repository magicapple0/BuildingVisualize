package crystalcube.buildingvisualize;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnAxeClickHandler implements Listener {
    private final SelectorAxeManager manager;
    private final BuildingVisualize plugin;
    public OnAxeClickHandler(BuildingVisualize plugin){
        this.plugin = plugin;
        manager = plugin.getAxeManagerConfig();
    }

    @EventHandler
    public void OnClick(PlayerInteractEvent event) {
        var itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        if (plugin.getAxeManagerConfig().IsItemStackAxe(itemInMainHand) && event.getClickedBlock() != null) {
            var targetBlock = event.getClickedBlock().getLocation();
            if (event.getAction().isRightClick()){
                manager.SetLastRightClick(event.getPlayer().getUniqueId(), targetBlock);
                event.getPlayer().sendActionBar("Second point: " + targetBlock.toVector());
            }
            else {
                manager.SetLastLeftClick(event.getPlayer().getUniqueId(), targetBlock);
                event.getPlayer().sendActionBar("First point: " + targetBlock.toVector());
            }
        }
    }
}
