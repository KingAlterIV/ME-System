package tk.kingalteriv.mesystem.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class BlockMECore {

    private int slotAmount;

    private final Block block;

    public BlockMECore(Block block, boolean readFromState) {
        this.block = block;

        if (readFromState) {
            this.readFromState();
        }
    }

    public Block getBlock() {
        return block;
    }

    public void setSlotAmount(int slotAmount) {
        this.slotAmount = slotAmount;
    }

    public int getSlotAmount() {
        return slotAmount;
    }

    public void writeToState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update NBT from fields
        container.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, slotAmount);

        state.update(false, false);
    }

    public void readFromState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update fields from NBT
        this.slotAmount = container.getOrDefault(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
    }

}
