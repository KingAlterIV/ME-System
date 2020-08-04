package tk.kingalteriv.mesystem.utilities;

import org.bukkit.NamespacedKey;
import tk.kingalteriv.mesystem.MESystem;

public final class MNamespacedKeys {

    private static final MESystem PLUGIN = MESystem.getInstance();

    // Terminal detection
    public static final NamespacedKey ME_SYSTEM_ITEM_TYPE = create("me_system_item_type");

    // This is the amount of different items it can have
    public static final NamespacedKey ME_SYSTEM_SLOTS = create("me_system_slots");

    // This is the total amount of items it can have
    public static final NamespacedKey ME_SYSTEM_ITEM_TOTAL_AMOUNT = create("me_system_item_total_amount");

    private MNamespacedKeys() { }

    private static NamespacedKey create(String path) {
        return new NamespacedKey(PLUGIN, "mesystem_" + path);
    }

}