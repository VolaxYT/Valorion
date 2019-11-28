package fr.volax.bungeeswitcher.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.volax.bungeeswitcher.BuildItemStack;
import fr.volax.bungeeswitcher.BungeeCordMain;
import fr.volax.bungeeswitcher.Servers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        final Player player = (Player)event.getWhoClicked();
        final Inventory inventory = event.getClickedInventory();
        if(inventory.getName().equals("§cNavigateur des serveurs")){
            final int clickedSlot = event.getSlot();
            final ItemStack itemStack = inventory.getItem(clickedSlot);
            if(itemStack != null){
                if(itemStack.hasItemMeta()){
                    final ItemMeta meta = itemStack.getItemMeta();
                    if(meta.getDisplayName() == "§6Discord Valorion"){
                        player.sendMessage("§6https://discordapp.com/invite/valorion");
                        player.closeInventory();
                    }
                    if(meta.getDisplayName() == "§6Twitter Valorion"){
                        player.sendMessage("§6https://twitter.com/ServeurValorion");
                        player.closeInventory();
                    }
                    if(meta.getDisplayName() == "§6Boutique Valorion"){
                        player.sendMessage("§6https://valorion-mc.fr/shop");
                        player.closeInventory();
                    }
                    if(meta.getDisplayName() == "§6Site Valorion"){
                        player.sendMessage("§6https://valorion-mc.fr");
                        player.closeInventory();
                    }
                    if(meta.getDisplayName() == "§6Minages"){
                        player.closeInventory();
                        final Inventory menu = Bukkit.createInventory(player, InventoryType.CHEST, "§cNavigateur des Minages");
                        for (Servers servers : Servers.values()) {
                            ItemStack iss = new ItemStack(Material.ENDER_PORTAL, 1);
                            ItemMeta issM = iss.getItemMeta();
                            issM.setDisplayName("§5§kValorion");
                            iss.setItemMeta(issM);
                            final ItemStack men = BuildItemStack.buildItemstack(servers.getIs(), servers.getDisplayName(), servers.getDescription());
                            menu.setItem(0, iss);
                            menu.setItem(1, iss);
                            menu.setItem(2, iss);
                            menu.setItem(3, iss);
                            menu.setItem(4, iss);
                            menu.setItem(5, iss);
                            menu.setItem(6, iss);
                            menu.setItem(7, iss);
                            menu.setItem(8, iss);
                            menu.setItem(9, iss);
                            menu.setItem(10, iss);
                            menu.setItem(11, BuildItemStack.buildItemstack(servers.MINAGE.getIs(), servers.MINAGE.getDisplayName(), servers.MINAGE.getDescription()));
                            menu.setItem(12, iss);
                            menu.setItem(13, BuildItemStack.buildItemstack(servers.MINAGE2.getIs(), servers.MINAGE2.getDisplayName(), servers.MINAGE2.getDescription()));
                            menu.setItem(14, iss);
                            menu.setItem(15, BuildItemStack.buildItemstack(servers.MINAGE3.getIs(), servers.MINAGE3.getDisplayName(), servers.MINAGE3.getDescription()));
                            menu.setItem(16, iss);
                            menu.setItem(17, iss);
                            menu.setItem(18, iss);
                            menu.setItem(19, iss);
                            menu.setItem(20, iss);
                            menu.setItem(21, iss);
                            menu.setItem(22, iss);
                            menu.setItem(23, iss);
                            menu.setItem(24, iss);
                            menu.setItem(25, iss);
                            menu.setItem(26, iss);
                            player.openInventory(menu);
                        }
                    }
                    if(meta.hasDisplayName()){
                        final String displayName = meta.getDisplayName();
                        for(Servers servers : Servers.values()){
                            if(servers.getDisplayName().equals(displayName)){
                                final ByteArrayDataOutput out = ByteStreams.newDataOutput();
                                out.writeUTF("Connect");
                                out.writeUTF(servers.getBungeeName());
                                player.sendPluginMessage(BungeeCordMain.INSTANCE, "BungeeCord", out.toByteArray());
            }
                        }
                    }
                }
            }
            event.setCancelled(true);
        }else if(inventory.getName().equals("§cNavigateur des Minages")){
            final int clickedSlot = event.getSlot();
            final ItemStack itemStack = inventory.getItem(clickedSlot);
            if(itemStack != null) {
                if (itemStack.hasItemMeta()) {
                    final ItemMeta meta = itemStack.getItemMeta();
                    if (meta.getDisplayName() == "§6Discord Valorion") {
                        player.sendMessage("§6https://discordapp.com/invite/valorion");
                        player.closeInventory();
                    }
                    if (meta.getDisplayName() == "§6Twitter Valorion") {
                        player.sendMessage("§6https://twitter.com/ServeurValorion");
                        player.closeInventory();
                    }
                    if (meta.getDisplayName() == "§6Boutique Valorion") {
                        player.sendMessage("§6https://valorion-mc.fr/shop");
                        player.closeInventory();
                    }
                    if (meta.getDisplayName() == "§6Site Valorion") {
                        player.sendMessage("§6https://valorion-mc.fr");
                        player.closeInventory();
                    }
                    if(meta.hasDisplayName()) {
                        final String displayName = meta.getDisplayName();
                        for (Servers servers : Servers.values()) {
                            if (servers.getDisplayName().equals(displayName)) {
                                final ByteArrayDataOutput out = ByteStreams.newDataOutput();
                                out.writeUTF("Connect");
                                out.writeUTF(servers.getBungeeName());
                                player.sendPluginMessage(BungeeCordMain.INSTANCE, "BungeeCord", out.toByteArray());
                            }
                        }
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
