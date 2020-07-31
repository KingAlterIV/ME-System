package tk.kingalteriv.mesystem.persistence;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public final class MEPersistentDataTypes {

    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new PersistentDataTypeBoolean();
    public static final PersistentDataType<String, ItemStack> ITEM_STACK = new PersistentDataTypeItemStack(true);
    public static final PersistentDataType<String[], ItemStack[]> ITEM_STACK_ARRAY = new PersistentDataTypeItemStackArray();

    private MEPersistentDataTypes() { }

}
