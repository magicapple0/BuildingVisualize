package crystalcube.buildingvisualize;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SelectorAxeManager {
    private final BuildingVisualize plugin;
    private final NamespacedKey isSelectorAxeKey;
    private final NamespacedKey lastLeftMouseClick;
    private final NamespacedKey lastRightMouseClick;


    public SelectorAxeManager(BuildingVisualize plugin){
        this.plugin = plugin;
        isSelectorAxeKey = new NamespacedKey(plugin, "isSelectorAxeKey");
        lastLeftMouseClick = new NamespacedKey(plugin, "lastLeftMouseClick");
        lastRightMouseClick = new NamespacedKey(plugin, "lastRightMouseClick");

    }

    private ItemStack create(){
        ItemStack axe = new ItemStack(Material.WOODEN_AXE);
        ItemMeta meta = axe.getItemMeta();

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(isSelectorAxeKey, PersistentDataType.INTEGER, 1);
        pdc.set(lastLeftMouseClick, PersistentDataType.INTEGER_ARRAY, new int[] {});
        pdc.set(lastRightMouseClick, PersistentDataType.INTEGER_ARRAY, new int[] {});

        axe.setItemMeta(meta);
        return axe;
    }

    public NamespacedKey getIsSelectorAxeKey() {
        return isSelectorAxeKey;
    }

    public Vector getLastLeftMouseClick(ItemStack axe) {
        var lastClick = axe.getItemMeta().getPersistentDataContainer().get(lastLeftMouseClick,PersistentDataType.INTEGER_ARRAY);
        assert lastClick != null;
        if (lastClick.length != 3)
            return null;
        return new Vector(lastClick[0], lastClick[1], lastClick[2]);
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

    public void SetLastRightClick(ItemStack axe, Vector newVector){
        var meta = axe.getItemMeta();
        var pdc = meta.getPersistentDataContainer();
        pdc.set(lastRightMouseClick, PersistentDataType.INTEGER_ARRAY, new int[]{newVector.X, newVector.Y, newVector.Z});
        axe.setItemMeta(meta);
    }

    public void SetLastLeftClick(ItemStack axe, Vector newVector){
        var meta = axe.getItemMeta();
        var pdc = meta.getPersistentDataContainer();
        pdc.set(lastLeftMouseClick, PersistentDataType.INTEGER_ARRAY, new int[]{newVector.X, newVector.Y, newVector.Z});
        axe.setItemMeta(meta);
    }

    public Vector GetLastRightMouseClick(ItemStack axe) {
        var lastClick = axe.getItemMeta().getPersistentDataContainer().get(lastRightMouseClick,PersistentDataType.INTEGER_ARRAY);
        assert lastClick != null;
        if (lastClick.length != 3)
            return null;
        return new Vector(lastClick[0], lastClick[1], lastClick[2]);
    }

    public Vector GetLastLeftMouseClick(ItemStack axe) {
        var lastClick = axe.getItemMeta().getPersistentDataContainer().get(lastLeftMouseClick,PersistentDataType.INTEGER_ARRAY);
        assert lastClick != null;
        if (lastClick.length != 3)
            return null;
        return new Vector(lastClick[0], lastClick[1], lastClick[2]);
    }
}
