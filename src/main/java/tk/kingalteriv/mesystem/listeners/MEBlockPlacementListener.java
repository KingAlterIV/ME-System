package tk.kingalteriv.mesystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import tk.kingalteriv.mesystem.MEItem;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.block.BlockMEDrive;
import tk.kingalteriv.mesystem.block.BlockMETerminal;

public class MEBlockPlacementListener implements Listener {

    private final MESystem plugin;

    public MEBlockPlacementListener(MESystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockPlacementListener(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR) {
            return;
        }

        Block clickedBlock = event.getClickedBlock().getRelative(event.getBlockFace());
        if (clickedBlock == null || clickedBlock.getType() != Material.AIR) {
            return;
        }

        ItemStack item = event.getItem();
        if (MESystem.ITEM_ME_SYSTEM.isSimilar(item)) {
            event.setCancelled(true);

            if (action == Action.RIGHT_CLICK_BLOCK) {
                clickedBlock.setType(Material.DISPENSER);
                BlockState state = clickedBlock.getState();
                if (state instanceof Dispenser) {
                    MEItem meItem = new MEItem(item);

                    Directional blockData = (Directional) clickedBlock.getBlockData();
                    blockData.setFacing(event.getPlayer().getFacing().getOppositeFace());
                    clickedBlock.setBlockData(blockData);

                    BlockMETerminal core = new BlockMETerminal(clickedBlock, false);
                    core.setTerminal(true);
                    core.setSlotAmount(meItem.getSlotAmount());
                    core.writeToState();

                    this.plugin.getStateHandler().registerCore(core);
                }
            }
        }
        else if (MESystem.ITEM_ME_DRIVE.isSimilar(item)) {
            event.setCancelled(true);

            if (action == Action.RIGHT_CLICK_BLOCK) {
                clickedBlock.setType(Material.DROPPER);
                BlockState state = clickedBlock.getState();
                if (state instanceof Dropper) {
                    Directional blockData = (Directional) clickedBlock.getBlockData();
                    blockData.setFacing(event.getPlayer().getFacing().getOppositeFace());
                    clickedBlock.setBlockData(blockData);

                    BlockMEDrive drive = new BlockMEDrive(clickedBlock, false);
                    drive.setDrive(true);
                    drive.writeToState();
                }
            }
        }
    }

}
