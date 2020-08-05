package tk.kingalteriv.mesystem.block;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import tk.kingalteriv.mesystem.persistence.MEPersistentDataTypes;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

public class BlockMETerminal {

    private int slotAmount;
    private MEItemType terminal;

    private final Block block;
    private final Location location;

    public BlockMETerminal(Block block, boolean readFromState) {
        this.block = block;
        this.location = block.getLocation();

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

    public void setTerminal(boolean terminal) {
        this.terminal = terminal ? MEItemType.METerminal : MEItemType.valueOf("Null");
    }

    public boolean isTerminal() {
        return terminal.equals(MEItemType.METerminal);
    }

    public void writeToState() {
        BlockState state = block.getState();
        if (!(state instanceof PersistentDataHolder)) {
            throw new IllegalStateException("Uh oh! Expected tile entity, got " + block.getType().getKey() + " instead?");
        }

        PersistentDataContainer container = ((PersistentDataHolder) state).getPersistentDataContainer();

        // Update NBT from fields
        container.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, slotAmount);
        container.set(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, terminal);

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
        this.terminal = container.getOrDefault(MNamespacedKeys.ME_SYSTEM_ITEM_TYPE, MEPersistentDataTypes.ME_ITEM_TYPE_ENUM, MEItemType.fromString("null"));
    }

}
