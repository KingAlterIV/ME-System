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
import tk.kingalteriv.mesystem.utilities.ReflectionUtils;

public class MESystem extends JavaPlugin {

    public static final ItemStack ITEM_ME_SYSTEM = ItemBuilder.of(Material.SNOWBALL)
            .modelData(69)
            .applyPersistentData(container -> {
                container.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
            }).build();

    private static MESystem instance;

    private PaperCommandManager commandManager;

    public MESystem() {
        instance = this;
    }

    @Override
    public void onEnable() {
        ReflectionUtils.init(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        this.commandManager = new PaperCommandManager(this);
        this.commandManager.registerCommand(new MESystemCommand());

        Bukkit.getPluginManager().registerEvents(new RightClickListener(), this);
    }

    public PaperCommandManager getManager() {
        return commandManager;
    }

    public static MESystem getInstance() {
        return instance;
    }

}
