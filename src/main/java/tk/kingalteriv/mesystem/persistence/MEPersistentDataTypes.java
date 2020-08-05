package tk.kingalteriv.mesystem.persistence;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

public final class MEPersistentDataTypes {

    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new PersistentDataTypeBoolean();
    public static final PersistentDataType<String, ItemStack> ITEM_STACK = new PersistentDataTypeItemStack(true);
    public static final PersistentDataType<String[], ItemStack[]> ITEM_STACK_ARRAY = new PersistentDataTypeItemStackArray();
    public static final PersistentDataType<String, MEItemType> ME_ITEM_TYPE_ENUM = new PersistentDataTypeMEItemTypeEnum();

    private MEPersistentDataTypes() { }

}
