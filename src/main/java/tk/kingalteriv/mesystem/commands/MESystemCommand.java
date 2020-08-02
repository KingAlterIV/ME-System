package tk.kingalteriv.mesystem.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import tk.kingalteriv.mesystem.MESystem;
import tk.kingalteriv.mesystem.utilities.Color;

@CommandAlias("MeSystem")
@CommandPermission("mesystem")
public class MESystemCommand extends BaseCommand {

    @Default
    @CommandPermission("mesystem.help")
    public void onDefault(Player sender) {
        sender.sendMessage(Color.ify("&f---------------- <#FBEE0F>ME-System &f----------------"));
        sender.sendMessage(Color.ify("<#FBEE0F>/mesystem give &f: Gives yourself a weapon/armor."));
    }

    @Subcommand("give")
    @CommandPermission("mesystem.give")
    public void onGive(Player sender) {
        sender.getInventory().addItem(MESystem.ITEM_ME_SYSTEM);
        sender.getInventory().addItem(MESystem.ITEM_ME_DRIVE);
    }

}
