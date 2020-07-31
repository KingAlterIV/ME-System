package tk.kingalteriv.mesystem.persistence;

import java.util.Arrays;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import org.jetbrains.annotations.NotNull;

public final class PersistentDataTypeItemStackArray implements PersistentDataType<String[], ItemStack[]> {

    PersistentDataTypeItemStackArray() { }

    @Override
    public @NotNull String[] toPrimitive(@NotNull ItemStack[] blocks, @NotNull PersistentDataAdapterContext context) {
        return Arrays.stream(blocks).map(b -> MEPersistentDataTypes.ITEM_STACK.toPrimitive(b, context)).toArray(String[]::new);
    }

    @Override
    public @NotNull ItemStack[] fromPrimitive(@NotNull String[] containers, @NotNull PersistentDataAdapterContext context) {
        return Arrays.stream(containers).map(b -> MEPersistentDataTypes.ITEM_STACK.fromPrimitive(b, context)).toArray(ItemStack[]::new);
    }

    @Override
    public @NotNull Class<String[]> getPrimitiveType() {
        return String[].class;
    }

    @Override
    public @NotNull Class<ItemStack[]> getComplexType() {
        return ItemStack[].class;
    }

}
