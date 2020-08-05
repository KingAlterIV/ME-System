package tk.kingalteriv.mesystem.block;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import tk.kingalteriv.mesystem.persistence.MEPersistentDataTypes;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

public class BlockMEDrive {

    private @NotNull MEItemType drive;

    private final Block block;
    private final Location location;

    public BlockMEDrive(Block block, boolean readFromState) {
        this.block = block;
        this.location = block.getLocation();

        if (readFromState) {
            this.readFromState();
        }
    }

    public Block getBlock() {
        return block;
    }

    public void setDrive(boolean bool) {
        this.drive = bool ? MEItemType.MEDrive : MEItemType.valueOf("Null");
    }

    public boolean isDrive() {
        return drive.equals(MEItemType.MEDrive);
    }

    public void writeToState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update NBT from fields
        container.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, this.drive);

        state.update(false, false);
    }

    public void readFromState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update fields from NBT
        this.drive = container.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, MEItemType.fromString("null"));
    }

}
