package tk.kingalteriv.mesystem;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import tk.kingalteriv.mesystem.commands.MESystemCommand;
import tk.kingalteriv.mesystem.listeners.RightClickListener;
import tk.kingalteriv.mesystem.utilities.ItemBuilder;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class MESystem extends JavaPlugin {

    private static MESystem instance;

    public MESystem() {
        instance = this;
    }

    public static MESystem getInstance() {
        return instance;
    }

    private ItemStack defaultMESystemItemStack;

    PaperCommandManager manager;


    public void onEnable(){
        this.manager = new PaperCommandManager(this);
        this.manager.registerCommand(new MESystemCommand());
        this.defaultMESystemItemStack = ItemBuilder.of(Material.SNOWBALL, 1).modelData(69).applyPersistentData(persistentDataContainer ->
                persistentDataContainer.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0)).build();
        Bukkit.getPluginManager().registerEvents(new RightClickListener(), this);
    }

    private PaperCommandManager getManager() {
        return this.manager;
    }

    public ItemStack getDefaultMESystemItemStack() {
        return this.defaultMESystemItemStack;
    }
}
