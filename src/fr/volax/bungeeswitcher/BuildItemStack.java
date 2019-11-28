package fr.volax.bungeeswitcher;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class BuildItemStack {
    public static ItemStack buildItemstack(ItemStack is, String displayName, ArrayList<String> description) {

        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(description);
        is.setItemMeta(im);

        return is;
    }
}
