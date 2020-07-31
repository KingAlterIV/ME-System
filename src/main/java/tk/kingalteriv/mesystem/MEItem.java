package tk.kingalteriv.mesystem;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class MEItem {

    ItemStack itemStack;
    ItemMeta meta;
    PersistentDataContainer persistentDataContainer;

    public MEItem(ItemStack itemStack){
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
        if (meta != null) {
            this.persistentDataContainer = meta.getPersistentDataContainer();
        }
    }

    public MEItem setSlotAmount(int integer){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, integer);
        return this;
    }

    public int getSlotAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

    public ItemStack build(){
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }

}
