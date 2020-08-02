package tk.kingalteriv.mesystem.utilities.loader;

import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;

import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.persistence.MEPersistentDataTypes;
import tk.kingalteriv.mesystem.utilities.Color;
import tk.kingalteriv.mesystem.utilities.ItemBuilder;
import tk.kingalteriv.mesystem.utilities.MNamespacedKeys;

public class ItemLoader {

    public ItemLoader(){
    }

    public static void loadAllItems(){
        MESystem.ITEM_ME_SYSTEM = ItemBuilder.of(Material.SNOWBALL)
                .name(Color.ify("<#326ba8>ME Terminal"))
                .lore(Color.ify("&fThe ME Terminal for the ME System||&fRequires an ME Drive to operate").split("\\|\\|"))
                .modelData(69)
                .applyPersistentData(container -> {
                    container.set(MNamespacedKeys.ME_SYSTEM_TERMINAL, MEPersistentDataTypes.BOOLEAN, true);
                    container.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 0);
                }).build();

        MESystem.ITEM_ME_DRIVE = ItemBuilder.of(Material.SNOWBALL)
                .name(Color.ify("<#326ba8>ME Drive"))
                .lore(Color.ify("&fThe ME Drive can store your storage cells in here||&fCan hold up to 9 items").split("\\|\\|"))
                .modelData(70)
                .applyPersistentData(container -> {
                    container.set(MNamespacedKeys.ME_SYSTEM_DRIVE, MEPersistentDataTypes.BOOLEAN, true);
                    container.set(MNamespacedKeys.ME_SYSTEM_SLOTS, PersistentDataType.INTEGER, 9);
                }).build();
    }

}
