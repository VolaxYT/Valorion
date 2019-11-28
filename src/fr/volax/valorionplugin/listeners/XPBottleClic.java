package fr.volax.valorionplugin.listeners;

import fr.volax.valorionplugin.Main;
import net.minecraft.server.v1_7_R4.TileEntity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class XPBottleClic implements Listener {
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR && !(p.isSneaking())|| e.getAction() == Action.RIGHT_CLICK_BLOCK && !(p.isSneaking())){
            if(p.getItemInHand().getType() == Material.EXP_BOTTLE){
                if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"))){
                    int numberitem = p.getItemInHand().getAmount();
                    ItemStack xpbottle = new ItemStack(Material.EXP_BOTTLE, numberitem - 1);
                    ItemMeta xpbottleM = xpbottle.getItemMeta();
                    xpbottleM.setDisplayName(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"));
                    xpbottleM.setLore(Arrays.asList(Main.getMain().getConfig().getString("messages.xpbottle.lore").replaceAll("&","§")));
                    xpbottle.setItemMeta(xpbottleM);
                    e.setCancelled(true);
                    p.getInventory().removeItem(p.getItemInHand());
                    p.getInventory().addItem(xpbottle);
                    p.updateInventory();
                    p.giveExpLevels(1);
                }
            }
        }else if(e.getAction() == Action.RIGHT_CLICK_AIR && p.isSneaking() || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()){
            if(p.getItemInHand().getType() == Material.EXP_BOTTLE){
                if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.getMain().getConfig().getString("messages.xpbottle.displayname").replaceAll("&","§"))) {
                    int numberitem = p.getItemInHand().getAmount();
                    p.getInventory().removeItem(p.getItemInHand());
                    p.giveExpLevels(numberitem);
                    p.updateInventory();
                    e.setCancelled(true);
                }
            }
        }
    }
}
