package tk.kingalteriv.mesystem.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        MEItem meItem = new MEItem(itemStack);

        if (MESystem.getInstance().getDefaultMESystemItemStack().isSimilar(itemStack)){
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                event.setCancelled(true);

                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Block clickedBlock = event.getClickedBlock();
                    if (clickedBlock != null) {
                        clickedBlock.setType(Material.DISPENSER);
                        if (clickedBlock.getState() instanceof Dispenser) {
                            MEBlock meBlock = new MEBlock((Dispenser) clickedBlock.getState());
                            meBlock.setSlotAmount(meItem.getSlotAmount());
                            meBlock.build();
                        }
                    }
                }
            }
        }
    }
}
