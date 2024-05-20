package crystalcube.buildingvisualize;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import java.util.UUID;

import java.util.HashMap;

public class SelectorAxeManager {
    private final NamespacedKey isSelectorAxeKey;
    private final HashMap<UUID, Location> UUIDToLastLeftMouseClick = new HashMap<>();
    private final HashMap<UUID, Location> UUIDToLastRightMouseClick = new HashMap<>();


    public SelectorAxeManager(BuildingVisualize plugin){
        isSelectorAxeKey = new NamespacedKey(plugin, "isSelectorAxeKey");

    }

    private ItemStack create(){
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = axe.getItemMeta();

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(isSelectorAxeKey, PersistentDataType.INTEGER, 1);

        axe.setItemMeta(meta);
        return axe;
    }

    public Boolean IsItemStackAxe(ItemStack item){
        return item.getItemMeta().getPersistentDataContainer()
                .has(isSelectorAxeKey);
    }

    public boolean give(Player player){
        var axe = create();
        var meta = axe.getItemMeta();
        meta.setDisplayName("Selector axe");
        axe.setItemMeta(meta);
        var inventory = player.getInventory();
        if (inventory.getItemInMainHand().getType() == Material.AIR){
            axe.setAmount(1);
            inventory.setItemInMainHand(axe);
            return true;
        }
        return false;
    }

    public void SetLastRightClick(UUID playerId, Location newLocation){
        UUIDToLastRightMouseClick.put(playerId, newLocation);
    }

    public void SetLastLeftClick(UUID playerId, Location newLocation){
        UUIDToLastLeftMouseClick.put(playerId, newLocation);
    }

    public Location GetLastLeftMouseClick(UUID playerId) {
        return UUIDToLastLeftMouseClick.getOrDefault(playerId, null);
    }

    public Location GetLastRightMouseClick(UUID playerId) {
        return UUIDToLastRightMouseClick.getOrDefault(playerId, null);
    }
}
