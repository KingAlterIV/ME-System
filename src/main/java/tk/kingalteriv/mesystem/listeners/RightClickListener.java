package tk.kingalteriv.mesystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import tk.kingalteriv.mesystem.MEItem;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.block.BlockMECore;

public class RightClickListener implements Listener {

    private final MESystem plugin;

    public RightClickListener(MESystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void rightClickListener(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        if (!MESystem.ITEM_ME_SYSTEM.isSimilar(item)) {
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

            clickedBlock.setType(Material.DISPENSER);
            BlockState state = clickedBlock.getState();
            if (state instanceof Dispenser) {
                MEItem meItem = new MEItem(item);

                BlockMECore core = new BlockMECore(clickedBlock, false);
                core.setSlotAmount(meItem.getSlotAmount());
                core.writeToState();

                this.plugin.getStateHandler().registerCore(core);
            }
        }
    }

}
