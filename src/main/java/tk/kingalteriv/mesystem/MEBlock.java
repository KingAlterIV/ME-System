package tk.kingalteriv.mesystem;

import org.bukkit.block.Dispenser;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class MEBlock {

    Dispenser dispenser;
    PersistentDataContainer persistentDataContainer;

    public MEBlock(Dispenser dispenser){
        this.dispenser = dispenser;
        this.persistentDataContainer = dispenser.getPersistentDataContainer();
    }

    public void setSlotAmount(int integer){
        persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, integer);
    }

    public int getSlotAmount(){
        return persistentDataContainer.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

    public void build(){
        dispenser.update();
    }

}
