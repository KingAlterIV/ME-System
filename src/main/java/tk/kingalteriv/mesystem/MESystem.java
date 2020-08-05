package tk.kingalteriv.mesystem;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tk.kingalteriv.mesystem.block.MEStateHandler;
import tk.kingalteriv.mesystem.commands.MESystemCommand;
import tk.kingalteriv.mesystem.listeners.MEBlockPlacementListener;
import tk.kingalteriv.mesystem.listeners.MEBlockThrowListener;
import tk.kingalteriv.mesystem.utilities.ReflectionUtils;
import tk.kingalteriv.mesystem.utilities.loader.ItemLoader;

public class MESystem extends JavaPlugin {

    public static ItemStack ITEM_ME_TERMINAL;
    public static ItemStack ITEM_ME_DRIVE;
    public static ItemStack ITEM_ME_CELL_1;

    private static MESystem instance;

    private PaperCommandManager commandManager;
    private MEStateHandler stateHandler;

    public MESystem() {
        instance = this;
    }

    @Override
    public void onEnable() {
        ReflectionUtils.init(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]);

        this.commandManager = new PaperCommandManager(this);
        this.commandManager.registerCommand(new MESystemCommand());
        this.stateHandler = new MEStateHandler();

        ItemLoader.loadAllItems();

        Bukkit.getPluginManager().registerEvents(new MEBlockPlacementListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MEBlockThrowListener(this), this);
    }

    public PaperCommandManager getManager() {
        return commandManager;
    }

    public MEStateHandler getStateHandler() {
        return stateHandler;
    }

    public static MESystem getInstance() {
        return instance;
    }

}