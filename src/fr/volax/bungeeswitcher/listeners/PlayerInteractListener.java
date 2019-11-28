package fr.volax.bungeeswitcher.listeners;


import fr.volax.bungeeswitcher.BuildItemStack;
import fr.volax.bungeeswitcher.BungeeCordMain;
import fr.volax.bungeeswitcher.Servers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final ItemStack is = player.getInventory().getItemInHand();
        if(is.getType().equals(BungeeCordMain.COMPASS.getType())) {
            if (is.hasItemMeta()) {
                if (is.getItemMeta().getDisplayName().equals(BungeeCordMain.COMPASS.getItemMeta().getDisplayName())) {
                    final Inventory menu = Bukkit.createInventory(player, InventoryType.CHEST, "§cNavigateur des serveurs");
                    for (Servers servers : Servers.values()) {
                        ItemStack iss = new ItemStack(Material.ENDER_PORTAL, 1);
                        ItemMeta issM = iss.getItemMeta();
                        issM.setDisplayName("§5§kValorion");
                        iss.setItemMeta(issM);
                        ItemStack isass = new ItemStack(Material.FIREBALL, 1);
                        ItemMeta isassM = iss.getItemMeta();
                        isassM.setDisplayName("§6Discord Valorion");
                        isassM.setLore(new ArrayList<>(Collections.singletonList("§4https://discordapp.com/invite/valorion")));
                        isass.setItemMeta(isassM);
                        ItemStack isassa = new ItemStack(Material.FIREBALL, 1);
                        ItemMeta isassaM = iss.getItemMeta();
                        isassaM.setDisplayName("§6Twitter Valorion");
                        isassaM.setLore(new ArrayList<>(Collections.singletonList("§4https://twitter.com/ServeurValorion")));
                        isassa.setItemMeta(isassaM);
                        ItemStack bisassa = new ItemStack(Material.FIREBALL, 1);
                        ItemMeta bisassaM = iss.getItemMeta();
                        bisassaM.setDisplayName("§6Boutique Valorion");
                        bisassaM.setLore(new ArrayList<>(Collections.singletonList("§4https://valorion-mc.fr/shop")));
                        bisassa.setItemMeta(bisassaM);
                        ItemStack bisassaa = new ItemStack(Material.FIREBALL, 1);
                        ItemMeta bisassaaM = iss.getItemMeta();
                        bisassaaM.setDisplayName("§6Site Valorion");
                        bisassaaM.setLore(new ArrayList<>(Collections.singletonList("§4https://valorion-mc.fr")));
                        bisassaa.setItemMeta(bisassaaM);
                        ItemStack bisassaaa = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                        ItemMeta bisassaaaM = iss.getItemMeta();
                        bisassaaaM.setDisplayName("§6Minages");
                        bisassaaaM.setLore(new ArrayList<>(Collections.singletonList("§4Rejoindre les serveurs Minages")));
                        bisassaaa.setItemMeta(bisassaaaM);
                        final ItemStack men = BuildItemStack.buildItemstack(servers.getIs(), servers.getDisplayName(), servers.getDescription());
                        if(player.hasPermission("op")){
                            menu.setItem(0, isass);
                            menu.setItem(1, iss);
                            menu.setItem(2, iss);
                            menu.setItem(3, iss);
                            menu.setItem(4, iss);
                            menu.setItem(5, iss);
                            menu.setItem(6, iss);
                            menu.setItem(7, iss);
                            menu.setItem(8, isassa);
                            menu.setItem(9, iss);
                            menu.setItem(10, iss);
                            menu.setItem(11, BuildItemStack.buildItemstack(servers.MODDE.getIs(), servers.MODDE.getDisplayName(), servers.MODDE.getDescription()));
                            menu.setItem(12, iss);
                            menu.setItem(13, BuildItemStack.buildItemstack(servers.BUILD.getIs(), servers.BUILD.getDisplayName(), servers.BUILD.getDescription()));
                            menu.setItem(14, iss);
                            menu.setItem(15, bisassaaa);
                            menu.setItem(16, iss);
                            menu.setItem(17, iss);
                            menu.setItem(18, bisassa);
                            menu.setItem(19, iss);
                            menu.setItem(20, iss);
                            menu.setItem(21, iss);
                            menu.setItem(22, iss);
                            menu.setItem(23, iss);
                            menu.setItem(24, iss);
                            menu.setItem(25, iss);
                            menu.setItem(26, bisassaa);
                            break;
                        }else{
                            menu.setItem(0, isass);
                            menu.setItem(1, iss);
                            menu.setItem(2, iss);
                            menu.setItem(3, iss);
                            menu.setItem(4, iss);
                            menu.setItem(5, iss);
                            menu.setItem(6, iss);
                            menu.setItem(7, iss);
                            menu.setItem(8, isassa);
                            menu.setItem(9, iss);
                            menu.setItem(10, iss);
                            menu.setItem(11, BuildItemStack.buildItemstack(servers.MODDE.getIs(), servers.MODDE.getDisplayName(), servers.MODDE.getDescription()));
                            menu.setItem(12, iss);
                            menu.setItem(13, iss);
                            menu.setItem(14, iss);
                            menu.setItem(15, bisassaaa);
                            menu.setItem(16, iss);
                            menu.setItem(17, iss);
                            menu.setItem(18, bisassa);
                            menu.setItem(19, iss);
                            menu.setItem(20, iss);
                            menu.setItem(21, iss);
                            menu.setItem(22, iss);
                            menu.setItem(23, iss);
                            menu.setItem(24, iss);
                            menu.setItem(25, iss);
                            menu.setItem(26, bisassaa);
                        }
                    }
                    player.openInventory(menu);
                }
            }
        }
    }
}
