package tk.kingalteriv.mesystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dropper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import tk.kingalteriv.mesystem.MEItem;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.block.BlockMEDrive;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

import java.util.Map;

public class MEDriveInventoryValidItemTypeListener implements Listener {

    private final MESystem plugin;

    public MEDriveInventoryValidItemTypeListener(MESystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void meDriveInventoryClickListener(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getLocation() == null) return;

        Block block = inventory.getLocation().getBlock();
        if (block.getType() != Material.DROPPER) return;

        ItemStack cursorItem = event.getCursor();
        if (cursorItem == null) return;
        if (cursorItem.getType() == Material.AIR) return;

        MEItem meCursor = new MEItem(cursorItem);

        BlockState blockState = block.getState();
        if (blockState instanceof Dropper) {
            BlockMEDrive drive = new BlockMEDrive(block, true);
            if (event.getRawSlot() <= 8) {
                if (drive.isDrive()) {
                    event.setCancelled(true);
                    ClickType clickType = event.getClick();
                    System.out.println(meCursor.getType());
                    if (clickType == ClickType.LEFT && meCursor.getType() == MEItemType.MECell) {
                        System.out.println("A");
                        event.setCancelled(false);
                    }
                }
            }
        }
    }

    @EventHandler
    public void meDriveInventoryDragListener(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getLocation() == null) return;

        Block block = inventory.getLocation().getBlock();
        if (block.getType() != Material.DROPPER) return;

        Map<Integer, ItemStack> newItems = event.getNewItems();

        BlockState blockState = block.getState();
        if (blockState instanceof Dropper) {
            BlockMEDrive drive = new BlockMEDrive(block, true);

            newItems.forEach((integer, itemStack) -> {
                if (integer <= 8) {
                    if (drive.isDrive()) {
                        MEItem meItem = new MEItem(itemStack);
                        if (!(meItem.getType() == MEItemType.MECell)) {
                            event.setCancelled(true);
                        }
                    }
                }
            });
        }
    }

}
