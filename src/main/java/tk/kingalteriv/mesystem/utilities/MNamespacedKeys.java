package tk.kingalteriv.mesystem.utilities;

import org.bukkit.NamespacedKey;
import tk.kingalteriv.mesystem.MESystem;

public final class MNamespacedKeys {

    private static final MESystem PLUGIN = MESystem.getInstance();

    public static final NamespacedKey ME_SYSTEM_IS_A_ME_SYSTEM = create("me_system_is_a_me_system");
    public static final NamespacedKey ME_SYSTEM_SLOTS = create("me_system_slots");

    private MNamespacedKeys() { }

    private static NamespacedKey create(String path) {
        return new NamespacedKey(PLUGIN, "mesystem_" + path);
    }

}