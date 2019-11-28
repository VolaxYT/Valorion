package fr.volax.bungeeswitcher;


import fr.volax.bungeeswitcher.listeners.InventoryClickListener;
import fr.volax.bungeeswitcher.listeners.PlayerInteractListener;
import fr.volax.bungeeswitcher.listeners.PlayerJoinListener;
import fr.volax.bungeeswitcher.listeners.TabListener;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;

public class BungeeCordMain extends JavaPlugin {

    public static BungeeCordMain INSTANCE;
    public static final ItemStack COMPASS = BuildItemStack.buildItemstack(new ItemStack(Material.COMPASS, 1), "§6Rejoindre les serveurs",  new ArrayList<>(Collections.singletonList("§ePour rejoindre les serveurs")));
    public static final ItemStack MINAGE = BuildItemStack.buildItemstack(new ItemStack(Material.DIAMOND, 1), "§6Rejoindre les Minages",  new ArrayList<>(Collections.singletonList("§ePour rejoindre les Minages")));
    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(),this);
        getServer().getPluginManager().registerEvents((Listener) new TabListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
