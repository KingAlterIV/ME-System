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

    public int getSlotAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

    public int getTotalAmount(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TOTAL_AMOUNT, PersistentDataType.INTEGER, 0);
    }

    public boolean isTerminal(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, "null").equals("METerminal");
    }

    public boolean isDrive(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, "null").equals("MEDrive");
    }

    public boolean isCell(){
        return this.persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, "null").equals("MECell");
    }

    public MEItem setSlotAmount(int integer){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, integer);
        return this;
    }

    public MEItem setTerminal(boolean terminal){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, terminal ? "METerminal" : "null");
        return this;
    }

    public MEItem setDrive(boolean drive){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, drive ? "MEDrive" : "null");
        return this;
    }

    public MEItem setCell(boolean cell){
        this.persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, PersistentDataType.STRING, cell ? "MECell" : "null");
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
