package tk.kingalteriv.mesystem.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import tk.kingalteriv.mesystem.MEItem;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.utilities.enums.MEItemType;

public class MEBlockThrowListener implements Listener {

    private final MESystem plugin;

    public MEBlockThrowListener(MESystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void meBlockThrowListener(ProjectileLaunchEvent event){
        Projectile projectile = event.getEntity();
        if (!(projectile.getType() == EntityType.SNOWBALL)) return;

        if (!(event.getEntity().getShooter() instanceof Player)) return;

        Player player = (Player) event.getEntity().getShooter();

        ItemStack item = player.getInventory().getItemInMainHand();

        MEItem meItem = new MEItem(item);

        if (!meItem.getType().equals(MEItemType.Null)) event.setCancelled(true);

    }

}
