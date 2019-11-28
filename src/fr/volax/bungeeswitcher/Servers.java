package fr.volax.bungeeswitcher;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public enum Servers {
    MODDE("modde", new ItemStack(Material.DIAMOND_SWORD), "§6Monde Moddé", new ArrayList<>(Collections.singletonList("§4Rejoindre le serveur §6Valorion - Moddé"))),
    MINAGE("minage", new ItemStack(Material.DIAMOND_PICKAXE), "§6Minage 1", new ArrayList<>(Collections.singletonList("§4Rejoindre le serveur §6Minage 1"))),
    MINAGE2("minage2", new ItemStack(Material.DIAMOND_PICKAXE), "§6Minage 2", new ArrayList<>(Collections.singletonList("§4Rejoindre le serveur §6Minage 2"))),
    MINAGE3("minage3", new ItemStack(Material.DIAMOND_PICKAXE), "§6Minage 3", new ArrayList<>(Collections.singletonList("§4Rejoindre le serveur §6Minage 3"))),
    BUILD("build", new ItemStack(Material.GRASS), "§6Build - Admin", new ArrayList<>(Collections.singletonList("§4Rejoindre le serveur §6Build")));


    private final String bungeeName;
    private final ItemStack is;
    private final String displayName;
    private final ArrayList<String> description;

    Servers(String bungeeName, ItemStack is, String displayName, ArrayList<String> description) {
        this.bungeeName = bungeeName;
        this.is = is;
        this.displayName = displayName;
        this.description = description;
    }

    public String getBungeeName() {
        return bungeeName;
    }

    public ItemStack getIs() {
        return is;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ArrayList<String> getDescription() {
        return description;
    }
}
