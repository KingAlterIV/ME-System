package tk.kingalteriv.mesystem.utilities;

import org.bukkit.ChatColor;

public final class Color {

    private Color() { }

    public static String ify(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
