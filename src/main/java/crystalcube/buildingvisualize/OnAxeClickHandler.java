package crystalcube.buildingvisualize;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

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
        var mainHandItemMeta = itemInMainHand.getItemMeta();
        var isAxeInMainHand = mainHandItemMeta != null && mainHandItemMeta.getPersistentDataContainer()
                .has(manager.getIsSelectorAxeKey());
        if (isAxeInMainHand && event.getClickedBlock() != null) {
            if (event.getAction().isRightClick()){
                OnRightClick(event);
            }
            else {
                OnLeftClick(event);
            }

        }
    }

    private void OnRightClick(PlayerInteractEvent event){
        var inventory = event.getPlayer().getInventory();
        var locationVector = new Vector(Objects.requireNonNull(event.getClickedBlock()).getLocation());
        var axe = inventory.getItemInMainHand();
        manager.SetLastRightClick(axe, locationVector);
        inventory.setItemInMainHand(axe);
        event.getPlayer().sendActionBar("selected \"" + locationVector.ToString() + "\"");
    }

    private void OnLeftClick(PlayerInteractEvent event){
        var inventory = event.getPlayer().getInventory();
        var locationVector = new Vector(Objects.requireNonNull(event.getClickedBlock()).getLocation());
        var axe = inventory.getItemInMainHand();
        manager.SetLastLeftClick(axe, locationVector);
        inventory.setItemInMainHand(axe);
        event.getPlayer().sendActionBar("selected \"" + locationVector.ToString() + "\"");
    }
}
