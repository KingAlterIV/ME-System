package tk.kingalteriv.mesystem;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.persistence.MEPersistentDataTypes;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

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

    public int getSlotAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

    public int getTotalAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TOTAL_AMOUNT,
                PersistentDataType.INTEGER, 0);
    }

    public MEItem setSlotAmount(int integer){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, integer);
        return this;
    }

    public MEItem setType(MEItemType type){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, type);
        return this;
    }

    public MEItemType getType(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, MEItemType.fromString("null"));
    }


    public ItemStack build(){
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }

}
