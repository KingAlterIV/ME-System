package tk.kingalteriv.mesystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dropper;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.block.BlockMEDrive;

public class MEDrivePlacementListener implements Listener {

    private final MESystem plugin;

    public MEDrivePlacementListener(MESystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockPlacementListener(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if (!MESystem.ITEM_ME_DRIVE.isSimilar(item)) {
            return;
        }

        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR) {
            return;
        }

        event.setCancelled(true);

        if (action == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock().getRelative(event.getBlockFace());
            if (clickedBlock == null || clickedBlock.getType() != Material.AIR) {
                return;
            }

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
