package tk.kingalteriv.mesystem.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

public class PersistentDataTypeMEItemTypeEnum implements PersistentDataType<String, MEItemType> {

    PersistentDataTypeMEItemTypeEnum() { }

    @Override
    public @NotNull Class<String> getPrimitiveType() {
        return String.class;
    }

    @Override
    public @NotNull Class<MEItemType> getComplexType() {
        return MEItemType.class;
    }

    @NotNull
    @Override
    public String toPrimitive(@NotNull MEItemType complex, @NotNull PersistentDataAdapterContext context) {
        return complex.toString();
    }

    @NotNull
    @Override
    public MEItemType fromPrimitive(@NotNull String primitive, @NotNull PersistentDataAdapterContext context) {
        return MEItemType.valueOf(primitive);
    }

}