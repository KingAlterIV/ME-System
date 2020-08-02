package tk.kingalteriv.mesystem;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.persistence.PersistentDataTypeBoolean;
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

    public int getSlotAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

    public int getTotalAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TOTAL_AMOUNT, PersistentDataType.INTEGER, 0);
    }

    public boolean isTerminal(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_TERMINAL, new PersistentDataTypeBoolean(), false);
    }

    public boolean isDrive(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_DRIVE, new PersistentDataTypeBoolean(), false);
    }

    public MEItem setSlotAmount(int integer){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, integer);
        return this;
    }

    public MEItem setTerminal(boolean terminal){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_TERMINAL, new PersistentDataTypeBoolean(), terminal);
        return this;
    }

    public MEItem setDrive(boolean drive){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_DRIVE, new PersistentDataTypeBoolean(), drive);
        return this;
    }

    public MEItem setTotalAmount(int totalAmount) {
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_ITEM_TOTAL_AMOUNT, PersistentDataType.INTEGER, totalAmount);
        return this;
    }

    public ItemStack build(){
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }

}
