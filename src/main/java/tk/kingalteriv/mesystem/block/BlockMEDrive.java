package tk.kingalteriv.mesystem.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.persistence.PersistentDataTypeBoolean;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class BlockMEDrive {

    private boolean drive;

    private final Block block;

    public BlockMEDrive(Block block, boolean readFromState) {
        this.block = block;

        if (readFromState) {
            this.readFromState();
        }
    }

    public Block getBlock() {
        return block;
    }

    public void setDrive(boolean bool) {
        this.drive = bool;
    }

    public boolean isDrive() {
        return drive;
    }

    public void writeToState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update NBT from fields
        container.set(MNamespacedKeys.ME_SYSTEM_DRIVE, new PersistentDataTypeBoolean(), drive);

        state.update(false, false);
    }

    public void readFromState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update fields from NBT
        this.drive = container.getOrDefault(MNamespacedKeys.ME_SYSTEM_DRIVE, new PersistentDataTypeBoolean(), false);
    }
}
