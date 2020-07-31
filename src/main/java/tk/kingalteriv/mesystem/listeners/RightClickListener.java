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

import tk.kingalteriv.mesystem.MEBlock;
import tk.kingalteriv.mesystem.MEItem;
import tk.kingalteriv.mesystem.MESystem;

public class RightClickListener implements Listener {

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
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock == null) {
                return;
            }

            clickedBlock.setType(Material.DISPENSER);
            BlockState state = clickedBlock.getState();
            if (state instanceof Dispenser) {
                MEBlock meBlock = new MEBlock((Dispenser) state);

                MEItem meItem = new MEItem(item);
                meBlock.setSlotAmount(meItem.getSlotAmount());
                meBlock.build();
            }
        }
    }
}
