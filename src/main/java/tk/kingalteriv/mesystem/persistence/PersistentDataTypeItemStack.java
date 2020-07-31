package tk.kingalteriv.mesystem.persistence;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import org.jetbrains.annotations.NotNull;

import tk.kingalteriv.mesystem.utilities.ReflectionUtils;

public final class PersistentDataTypeItemStack implements PersistentDataType<String, ItemStack> {

    // TODO: Record data version into NBT to avoid data corruption between versions

    private final boolean strict;

    public PersistentDataTypeItemStack(boolean strict) {
        this.strict = strict;
    }

    @Override
    public @NotNull Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public @NotNull Class<ItemStack> getComplexType() {
        return ItemStack.class;
    }

    @Override
    public @NotNull String toPrimitive(@NotNull ItemStack item, @NotNull PersistentDataAdapterContext context) {
        Preconditions.checkArgument(item != null, "Cannot serialize null item");
        Preconditions.checkArgument(!strict || item.getType() != Material.AIR, "Air cannot be serialized (strict = true)");

        return ReflectionUtils.itemStackToNBT(item);
    }

    @Override
    public @NotNull ItemStack fromPrimitive(@NotNull String nbtString, @NotNull PersistentDataAdapterContext context) {
        return ReflectionUtils.itemStackFromNBT(nbtString);
    }

}
